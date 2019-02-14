package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by HaiyangWork on 2018/12/19.
 */
public class FileParser {
    private static String[] fields = null;


    public static JSONObject listPath(String path){
        JSONObject ret = new JSONObject();
        File rootDir = new File(path);
        File[] subDirs = rootDir.listFiles();

        if(Objects.isNull(subDirs)) return null;
        for(File subDir: subDirs){
            File[] subDirsBetas = subDir.listFiles();
            String type = subDir.getName();

            if(!ret.containsKey(type)) {
                ret.put(type, new JSONObject());
            }
            JSONObject typeObject = ret.getJSONObject(type);

            if(Objects.isNull(subDirsBetas)) continue;
            for(File subDirsBeta: subDirsBetas) {
                String table = subDirsBeta.getName();
                if(!typeObject.containsKey(table)) {
                    typeObject.put(table, new JSONArray());
                }
                JSONArray tableObject = typeObject.getJSONArray(table);

                File[] files = subDirsBeta.listFiles();
                if (Objects.isNull(files)) continue;
                for (File file : files) {
//                    System.out.println(type + "|" + table + "|" + file.getAbsolutePath());
                    tableObject.add(file.getAbsolutePath());
                }
            }

        }

        return ret;
    }


    public static Map<String, Object> readLines(File file, String encoding, long pos, int num) {
        Map<String, Object> result = Maps.newHashMap();
        List<String> lines = Lists.newArrayList();
        FileType fileType = FileType.JSON;
        try (BufferedRandomAccessFile reader = new BufferedRandomAccessFile(file, "r")) {
            reader.seek(pos);
            if(pos == 0 ) {
                String firstLine = reader.readLine();
                try{
                    JSONObject.parseObject(firstLine);
                    fileType = FileType.JSON;
                    lines.add(firstLine);
                } catch (Exception e){
                    fields = firstLine.split(",");
                    fileType = FileType.CSV;
                }
            }

            for (int i = 0; i < num; i++) {
                String line = reader.readLine();
                if (StringUtils.isBlank(line)) { break; }
                lines.add(new String(line.getBytes("8859_1"), encoding));
            }

            if(fileType.equals(FileType.CSV)){
                lines = transfer(lines);
            }

            result.put("lines", lines);
            result.put("pos", reader.getFilePointer());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // used when csv
    private static List<String> transfer(List<String> lines){
        List<String> ret = new ArrayList<>();
        for(String line:lines){
            String[] valuesArr = line.split(",");
            JSONObject lineJson = new JSONObject(true);
            for(int j=0;j<valuesArr.length;j++) {
                lineJson.put(fields[j], valuesArr[j]);
            }
            ret.add(lineJson.toJSONString());
        }
        lines.clear();

        return ret;
    }

}
