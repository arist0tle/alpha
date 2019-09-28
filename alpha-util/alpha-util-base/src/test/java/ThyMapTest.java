import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.util.base.map.EntryMap;
import com.geektcp.alpha.util.base.map.ThyMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author tanghaiyang on 2019/8/13.
 */
@Slf4j
public class ThyMapTest {

    @Test
    public void ThyMaptest(){
        ThyMap<String,Object> thyMap = new ThyMap<>();
        thyMap.put("Aa", "ssss1");
        thyMap.put("BB", "ssss2");
        thyMap.put("a2", 13);
        thyMap.put("a3", 14);
        thyMap.put("a4", 15);
        thyMap.put("a5", 16);
        thyMap.put("a6", 17);
        thyMap.put("a7", 11);
        thyMap.put("a8", 11);
        thyMap.put("a9", 11);
        System.out.println(JSON.toJSONString(thyMap,true));

    }

    @Test
    public void HashcodeTest(){
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
    }

    @Test
    public void EntryMapTest(){
        EntryMap<String, Object> entryMap = new EntryMap<>();
        entryMap.setValue("aaa");
        log.info(JSON.toJSONString(entryMap,true));
    }

    @Test
    public void ConcurrentMapTest(){
        ConcurrentMap<String,Object> map = new ConcurrentHashMap<>();
        Object o = new Object();
    }

    /**
     *  System.gc垃圾回收比较慢，所以有时候会打印1或者2，如果使用断点，几乎每次都是0
     *  说明WeakHashMap里面的元素如果不用确实会被垃圾回收。
     * @throws Exception
     */
    @Test
    public void WeakHashMapTest()throws Exception{
        Map<Object, Object> objectMap = new WeakHashMap<>();
        for (int i = 0; i < 1000; i++) {
            String key = String.valueOf(i);
            objectMap.put(key, new Object());
            Thread.sleep(1000);
            key = null;
            System.gc();
            System.out.println("Map size :" + objectMap.size());
        }

        System.gc();
        System.out.println("Map size :" + objectMap.size());
    }


}
