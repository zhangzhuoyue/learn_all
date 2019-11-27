import java.util.List;

/**
 * @author zhangyue
 * @create 2019/11/27
 */

/**
 * <T> T 用法
 * 这个<T> T 表示返回的值T是泛型，T是一个占位符。
 * 用来告诉编译器，这个东西留下来，等待编译的时候传递
 * 这个<T> T 可以传入任何类型的List
 * 参数T
 *      第一个 表示是泛型
 *      第二个 表示返回的是T类型的数据
 *      第三个 限制参数类型为 T
 *
 * 这种泛型适用于方法上，可以将参数类型的作用范围限制在一个方法中。
 */
public class Generic1 {

    public <T> T geberic(List<T> data){
        if (data == null || data.size() ==0){
            return null;
        }
        return data.get(0);
    }
}
