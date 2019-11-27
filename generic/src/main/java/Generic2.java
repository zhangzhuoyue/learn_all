import java.util.List;

/**
 * @author zhangyue
 * @create 2019/11/27
 */

/**
 *
 * @param <T>
 * T 用法
 * 返回值，直接写T表示限制参数的类型
 * 这种方法一般多用于共同操作一个类对象
 * 然后获取里面的集合信息
 */
public class Generic2<T> {

    public T generic(List<T> data){
        if (data == null || data.size() ==0){
            return null;
        }
        return data.get(0);
    }
}
