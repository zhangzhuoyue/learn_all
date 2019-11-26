package reflect;

import entity.Persion;
import sun.misc.ClassLoaderUtil;
import utils.classUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author zhangyue
 * @create 2019/11/26
 */

/**
 * invoke(Object obj, Object... args)	传递object对象及参数调用该对象对应的方法
 *
 */
public class MethodTest {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
            //invokeMethod();
        methods();
    }
    //使用反射，调用方法
    public static void methods() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Persion persion = new Persion();
        persion.setName("实验");

        Class<?> clazz = classUtils.getClazz(persion);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods){

            //调用getName()获取name属性的成员值
            if (method.getName().equals("getName")){                 //method.geName()  获取方法名
                method.setAccessible(true);                         //私有方法在调用前需要授予权限
                System.out.println(method.invoke(persion));         //在调用方法时，invoke() 这里调用的时无参构造方法。
            }

            //调用setDate()方法设置值
            if (method.getName().equals("setDate")){

                method.invoke(persion,new Object[]{new Date()});
                Method getDate = clazz.getMethod("getDate");   //获取指定的方法getMethod(String methodName ,  Class<?>... parameterTypes)
                System.out.println(getDate.invoke(persion));
            }

        }
    }

}
