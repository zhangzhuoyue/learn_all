package utils;

/**
 * @author zhangyue
 * @create 2019/11/26
 */
public class classUtils {

    /**
     * 获取class文件
     * @param t
     * @return
     */
    public static  Class<?> getClazz(Object t){
        Class<?> aClass = t.getClass();
        return aClass;
    }
}
