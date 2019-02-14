import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import util.FileParser;


/**
 * Created by tanghaiyang on 2019/2/13.
 */
public class Main {

    private static final int MAX_LINE_SIZE = 200;


    public static void main(String[] args) {
        String filePath = "D:\\CodeGraph\\lab\\graph-lab-tiger\\src\\main\\resources\\data";
        JSONObject ret = FileParser.listPath(filePath);

        System.out.println(JSON.toJSONString(ret, SerializerFeature.PrettyFormat));

    }

}
