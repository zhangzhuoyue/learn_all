package com.zzy.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author zhangyue
 * @create 2019/11/20
 */

/**
 * *marshal:直译为“编排”，在计算机中特指将数据按某种描述格式编排出来，通常来说一般是从非文本格式到文本格式的数据转化
 *          *
 *          * xstream 的toXNL()方法，
 *          * 1.将对象转化为一个 outputstream  ,经过封装->HierarchicalStreamReader.
 *          * 2.在字节输出流，可以设置字节流的编码集。
 *          * 3.
 *          canConvert()  方法会首先判断当前对象的类型，返回true，调用marshal()  。向输出流中设置处理后的值。
 *
 *   xstream 的 fromXML()方法
 *
 *   HierarchicalStreamReader  输入流，    将字符串转换为字节输入流。字节输入流可以设置
 *
 */

public class DateConverter implements Converter {

    private Locale locale;

    public DateConverter(Locale locale) {
        this.locale = locale;
    }

    public DateConverter(){

    }

    //编写java对象到xml的逻辑  将对象转换为文本数据。
    //source  The object to be marshalled. 要编组的对象。
    // writer  A stream to write to.   要写入的流。
    // context A context that allows nested objects to be processed by XStream.  一个允许XStream处理嵌套对象的上下文

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        writer.setValue(dateFormat.format(source));
        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKK");

        //System.out.println(writer.toString());


    }
    //编写xml到java对象的逻辑  将文本数据转换回一个对象。
    /**
     * Convert textual data back into an object.   将文本数据转换回一个对象。
     *
     * @param reader  The stream to read the text from.  从中读取文本的流。    HierarchicalStreamReader:分层流阅读器
     * @param context
     * @return The resulting object.            他成为对象。
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
//        GregorianCalendar calendar = new GregorianCalendar();
//        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL,this.locale);
//        try {
//            calendar.setGregorianChange(dateFormat.parse(reader.getValue()));
//        }catch (ParseException e){
//            e.printStackTrace();
//        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(reader.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

        return date;
    }
    //判断需要转换的类型
    @Override
    public boolean canConvert(Class type) {
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

        return  Date.class.isAssignableFrom(type);

    }
}
