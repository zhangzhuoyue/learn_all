package GenericType;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyue
 * @create 2019/11/26
 */

/**
 * Type是一个空接口，所有类型的公共接口(父类)。因此getGenericType() 获取参数化类型，该类型是ParamterizedType的子类。使用 type instanceof ParamterizedType
 * 得到的结果是true。
 *
 * 常见的泛型化参数除了是数组和本类上定义的泛型以外，其他参数基本都是参数化类型，形如：
 * 1.List<T>
 * 2.Set<T>
 * 3.Map<K,V>
 * 4.class<?>(自定义类或接口的泛型)
 *
 *
 *
 */
public class GenericType {

    public static void main(String[] args) {
        genericType();
    }


    public static  void genericType(){
        try {
            Class<TypeBean> typeBean = (Class<TypeBean>) Class.forName("GenericType.TypeBean");
            Field[] declaredFields = typeBean.getDeclaredFields();

            int i = 1;
            for (Field field : declaredFields){
                field.setAccessible(true);
                System.out.println("----------------------------------"+i);
                System.out.println("field name is  :"+ field.getName());
                System.out.println("field.getGenericType()+++++"+field.getGenericType());
                System.out.println("field.getGenericType() instanceof ParameterizedType");
                System.out.println("-----------"+(field.getGenericType() instanceof ParameterizedType));

                if ("animal".equals(field.getName())){
                    ParameterizedType type = (ParameterizedType) field.getGenericType();

                    Type ownerType = type.getOwnerType();
                    System.out.println("Person.Animal<String>的ownerType是。。。"+type);

                    Type rawType = type.getRawType();
                    System.out.println("Person.Animal<String>的RawType是。。。"+ rawType);

                    Type[] actualTypeArguments = type.getActualTypeArguments();        //获取泛型中的实际参数。
                    for (Type type1 : actualTypeArguments){
                        System.out.println(type1.getTypeName()+"获取到的actualTypeArguments分别为："+type1.getTypeName());
                    }
                    System.out.println("Person.Animal<String> instanceof ParameterizedType -----"+(type instanceof ParameterizedType));
                }
                System.out.println("--------------"+i);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
/**
 *
 * ----------------------------------1
 * field name is  :list
 * field.getGenericType()+++++java.util.List<java.lang.String>
 * field.getGenericType() instanceof ParameterizedType
 * -----------true
 * --------------1
 * ----------------------------------2
 * field name is  :set
 * field.getGenericType()+++++java.util.Set<java.lang.String>[]                   数组不是参数化类型 paramterizedType
 * field.getGenericType() instanceof ParameterizedType
 * -----------false
 * --------------2
 * ----------------------------------3
 * field name is  :noGeneric
 * field.getGenericType()+++++interface java.util.List                              List 不是基本数据没有带参数
 * field.getGenericType() instanceof ParameterizedType
 * -----------false
 * --------------3
 * ----------------------------------4
 * field name is  :entry
 * field.getGenericType()+++++java.util.Map$Entry<java.lang.String, java.lang.Integer>
 * field.getGenericType() instanceof ParameterizedType
 * -----------true
 * --------------4
 * ----------------------------------5
 * field name is  :animal
 * field.getGenericType()+++++GenericType.Person1$Animal<java.lang.String>
 * field.getGenericType() instanceof ParameterizedType
 * -----------true
 * Person.Animal<String>的ownerType是。。。GenericType.Person1$Animal<java.lang.String>
 * Person.Animal<String>的RawType是。。。class GenericType.Person1$Animal
 * java.lang.String获取到的actualTypeArguments分别为：java.lang.String
 * Person.Animal<String> instanceof ParameterizedType -----true
 * --------------5
 * ----------------------------------6
 * field name is  :str
 * field.getGenericType()+++++class java.lang.String                            String 的通过getGenericType() 获取到的是它的class xx
 * field.getGenericType() instanceof ParameterizedType                          基本数据类型包装类也都是这种情况。
 * -----------false
 * --------------6
 * ----------------------------------7
 * field name is  :t
 * field.getGenericType()+++++T
 * field.getGenericType() instanceof ParameterizedType
 * -----------false
 * --------------7
 * ----------------------------------8
 * field name is  :ttt
 * field.getGenericType()+++++int
 * field.getGenericType() instanceof ParameterizedType
 * -----------false
 * --------------8
 * ----------------------------------9
 * field name is  :integer
 * field.getGenericType()+++++class java.lang.Integer
 * field.getGenericType() instanceof ParameterizedType
 * -----------false
 * --------------9
 *
 * Process finished with exit code 0
 *
 *
 *
 *
 *
 *
 *
 * */