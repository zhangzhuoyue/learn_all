package GenericType;



import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyue
 * @create 2019/11/26
 */
public class TypeBean<T> {

    public List<String> list;
    private Set<String>[] set;
    private List noGeneric;
    Map.Entry<String,Integer> entry;
    Person1.Animal<String> animal;
    String str = new String();
    T t;
    int ttt;
    Integer integer;
}
