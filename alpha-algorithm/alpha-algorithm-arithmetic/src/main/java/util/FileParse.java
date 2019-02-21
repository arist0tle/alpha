package util;

import alpha.common.base.util.FileUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by tanghaiyang on 2019/2/21.
 */
public class FileParse {
    public static JSONObject buildFilter(String dataFile){
        return JSONObject.parseObject(FileUtils.readTxtFile(dataFile));
    }


    public static JSONArray buildResult(String dataFile){
        return JSONArray.parseArray(FileUtils.readTxtFile(dataFile));
    }
}
