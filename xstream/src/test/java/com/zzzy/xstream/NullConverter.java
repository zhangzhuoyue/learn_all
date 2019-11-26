package com.zzzy.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author zhangyue
 * @create 2019/11/21
 */

/**
 * 将成员变量为null的值，在bean -> xml 该成员变量仍然存在
 */
public class NullConverter implements Converter {

    //存放当前对象
    private Class currentType;
    private String[] classNameArray = {"PersionList", "Persion", "Phone"};
    private List<String> classNamesList;


    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        matchType(source,writer,context,currentType);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }

    @Override
    public boolean canConvert(Class type) {
        currentType = type;
        classNamesList = Arrays.asList(classNameArray);
        if (classNamesList.contains(type.getSimpleName())) {
            return true;
        } else {
            return false;
        }

    }


    //1.获取需要转换的对象，并选择它对应的类型解析
    @SuppressWarnings({ "rawtypes" })
    private void matchType(Object source, HierarchicalStreamWriter writer, MarshallingContext context, Class clazz) {
        Field[] nodeFields = clazz.getDeclaredFields();
        for (Field file : nodeFields) {
            String nodename = file.getName();
            Class nodeType = file.getType();
            if (classNamesList.contains(nodeType.getSimpleName())) {
                objectConverter(source, writer, context, clazz, nodename, nodeType);
            } else if (Arrays.asList(nodeType.getInterfaces()).contains(Collection.class)) {
                collextionConverter(source, writer, context, clazz, nodename, file);
            } else {
                basicTypeConverter(source,writer,context,clazz,nodename,nodeType);
            }
        }
    }

    @SuppressWarnings({ "rawtypes" })
    private void collextionConverter(Object source, HierarchicalStreamWriter writer, MarshallingContext context, Class clazz, String nodename, Field field) {
        Type[] types = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
        Object obj = getValue(clazz, nodename, source);
        Collection collection = null;
        if (field.getType().equals(List.class)) {
            collection = (List) obj;
        } else if (field.getType().equals(Set.class)) {
            collection = (Set) obj;
        }
        writer.startNode(nodename);
        for (Object object : collection) {
            String clazzName = ((Class) types[0]).getSimpleName();
            writer.startNode(clazzName.substring(0, 1).toUpperCase() + clazzName.substring(1));
            matchType(object, writer, context, (Class)types[0]);
            writer.endNode();
        }
        writer.endNode();

    }

    @SuppressWarnings({ "rawtypes" })
    private void basicTypeConverter(Object source, HierarchicalStreamWriter writer, MarshallingContext context, Class clazz, String nodename, Class fieldCazz) {

        Object obj = getValue(clazz, nodename, source);
        writer.startNode(nodename);
        writer.setValue(obj == null ? "" : obj.toString());
        writer.endNode();
    }

    //1.处理对象
    @SuppressWarnings({ "rawtypes" })
    private void objectConverter(Object source, HierarchicalStreamWriter writer, MarshallingContext context, Class clazz, String fieldName, Class fieldclazz) {
        Object obj = getValue(clazz, fieldName, source);
        writer.startNode(fieldName);
        matchType(obj, writer, context, fieldclazz);
        writer.endNode();

    }

    //2.获取成员的get方法
    @SuppressWarnings({ "rawtypes" })
    private void matchMethod(Object source, HierarchicalStreamWriter writer, MarshallingContext context, Class clazz, Field field, String nodeName) {
        try {
            Method method = clazz.getMethod("get" + nodeName.substring(0, 1).toUpperCase() + nodeName.substring(1));
            writer.startNode(nodeName);
            method.invoke(clazz.cast(source), new Object[0]);
            writer.endNode();
        } catch (NoSuchMethodException n) {
            n.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //获取成员变量值
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object getValue(Class clazz, String nodename, Object source) {
        Object obj = null;
        try {
            Method method = clazz.getMethod("get" + nodename.substring(0, 1).toUpperCase() + nodename.substring(1));
            //clazz.cast(source);
            obj = method.invoke(clazz.cast(source),new Object[] {});
            System.out.println("121212121212");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return obj;
    }

    }

