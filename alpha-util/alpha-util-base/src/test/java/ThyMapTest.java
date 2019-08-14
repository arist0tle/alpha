import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.util.base.EntryMap;
import com.geektcp.alpha.util.base.ThyMap;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by TangHaiyang on 2019/8/13.
 */
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



    }

}
