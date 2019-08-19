import org.junit.Test;

import java.util.ArrayDeque;

/**
 * Created by TangHaiyang on 2019/8/19.
 */
public class CollectionTest {

    @Test
    public void ArrayDequeTest(){
        ArrayDeque<String> arrayDeque = new ArrayDeque();
        arrayDeque.add("aaaaa");
        arrayDeque.push("bbbbb");

        System.out.println(arrayDeque);
    }

}
