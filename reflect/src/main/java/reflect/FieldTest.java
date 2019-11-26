package reflect;

import entity.Man;
import entity.Persion;

import java.io.FileReader;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangyue
 * @create 2019/11/26
 */


//反射套路
    //1.获取class
    //2.通过class获取他的field
    //3.获取属性用field.get(Object obj)  从对象中获取值
    //4.设置属性值field.set(Object obj ,参数)  向指定的对象设置属性值。
public class FieldTest {

    public static void main(String[] args) {
        //getField();
        //setField();
        methods();
    }

    //通过反射获取类的成员变量值
    public static void getField()  {
        try {
            Class<Man> manClass = Man.class;
            Man man = manClass.newInstance();
            man.setAge(12);
            man.setDate(new Date());
            man.setName("name");


            Field[] declaredFields = manClass.getDeclaredFields();
            for (Field field : declaredFields){
                field.setAccessible(true);
                //参数的field对象get方法，获取对象的属性值
                System.out.println(field.get(man));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //通过反射设置类的成员变量值
    public static void setField(){
        try {
            Class<?> persionClass = Class.forName("entity.Persion");
            Persion persion = (Persion) persionClass.newInstance();

            Field[] declaredFields = persionClass.getDeclaredFields();
            for (Field field : declaredFields){
                //给私有属性设置赋予权限
                field.setAccessible(true);
                //field.getName() 获取属性名 ,只是一个简单的属性名。
                //
                if ("name".equals(field.getName())){
                    //field通过set方法，给对象设置属性值

                    field.set(persion,"jack");
                }
                if("date".equals(field.getName())){
                    field.set(persion,new Date());
                }
                if ("age".equals(field.getName())){
                    field.set(persion,123);
                }

                if ("mans".equals(field.getName())){
                    Man m1 = new Man();m1.setName("zhong1");
                    Man m2 = new Man();m1.setName("zhong2");
                    Man m3 = new Man();m1.setName("zhong3");
                    List<Man> mans = new ArrayList<Man>();
                    mans.add(m1);
                    mans.add(m2);
                    mans.add(m3);
                    field.set(persion,mans);
                }

            }
            System.out.println(persion.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void methods(){
        try {
            Class<?> persionClass = Class.forName("entity.Persion");
            Persion persion = (Persion) persionClass.newInstance();
            persion.setName("shi");
            persion.setName("shi");
            persion.setName("shi");
            Field fieldName = persionClass.getDeclaredField("name");
            Field fieldMans = persionClass.getDeclaredField("mans");
            Field fieldMan = persionClass.getDeclaredField("man");

            //属性是私有的，需要赋予权限
            fieldName.setAccessible(true);
            fieldMans.setAccessible(true);
            fieldMan.setAccessible(true);

            System.out.println(fieldName.get(persion)); //获取字段的属性值
            System.out.println(fieldName.getModifiers());//获取属性的权限修饰符 1 ：public    2：private   4:protected

            //获取参数化类型
            Type genericType = fieldMan.getGenericType();    //获取成员属性的参数化类型

            if (genericType instanceof  ParameterizedType){  // 判断成员属性的参数是不是参数化类型

                Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments(); //在参数化类型中，ParameterizedTyp包含多个参数类型。获取所有的参数类型。List<String> 有一个参数  Map<String,String> 有两个参数
                for (Type type : actualTypeArguments)                                                    //从参数化类型对象中获取参数的Type对象
                System.out.println("------"+type+"---------"+type.getTypeName()+"-----"+((Class)type).toString()+"--------"+((Class)type).getSimpleName());//type是参数的Type类型，它是Class对象的父类；type.getTypeName 参数类型的全限定名；(Class)type 将type向下转型，为Class对象，它可以使用Class对象的方法

            }else{
                //成员属性不是参数化类型，调用getType方法获取成员属性的Class 。如果成员属性是参数化类型，也可以调用getType()获取它本身的Class。
                Class<?> type = fieldMan.getType();
                System.out.println(type.getSimpleName());
                System.out.println(type.getAnnotatedSuperclass());
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
