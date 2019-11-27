import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyue
 * @create 2019/11/27
 */

public class GenericTest {

    private  Generic1 generic1;
    private Generic2<Integer> stringGeneric2;
    @Before
    public void before(){
        generic1 = new Generic1();
        stringGeneric2 = new Generic2<Integer>();
    }

    @Test
    public void generic(){
        //向一个泛型方法中传递泛型参数
        ArrayList<String> list = new ArrayList<String>();
        list.add("1242");
        list.add("1242");
        list.add("1242");
        String geberic1 = generic1.geberic(list);
        System.out.println(geberic1);

        //向一个泛型类传递泛型参数
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(2123);
        list1.add(2123);
        list1.add(2123);
        Integer generic = stringGeneric2.generic(list1);
        System.out.println(generic);

    }
}
