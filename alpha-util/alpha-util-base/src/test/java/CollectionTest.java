import org.junit.Test;

import java.util.*;

/**
 * Created by TangHaiyang on 2019/8/19.
 */
public class CollectionTest {

    /**
     * ArrayDeque是一个复合的队列，看起来像是两个方向相反的队列合并在一起，实际是个循环数组
     * 数组是必须先指定长度才能用的，仔细看源码，发现new ArrayDeque()内部也会指定一个长度为16的数组,
     * ArrayDeque提供了4个核心方法，在头部插入和删除，在尾部插入和删除，其他所有数据更新操作的方法都来自这4个
     * ArrayDeque不提供重建插入和删除，获取的方法,不能根据序号进行指定操作。
     */
    @Test
    public void ArrayDequeTest(){
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.add("aaaaa");
        arrayDeque.push("bbbbb");
        System.out.println(arrayDeque);
    }

    @Test
    public void LinkedHashMapTest(){
        List<String> list = new ArrayList<>();
        List<String> synList = Collections.synchronizedList(list);
        LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>(10,0.74f,true);
    }

}
