import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.util.base.ThyMap;
import org.junit.Test;

/**
 * Created by TangHaiyang on 2019/8/13.
 */
public class ThyMapTest {

    @Test
    public void testHashMap(){
        ThyMap<String,Object> thyMap = new ThyMap<>();
        thyMap.put("aaa", "ssss");
        thyMap.put("ddddd", 11);

        System.out.println(JSON.toJSONString(thyMap,true));
    }
}
