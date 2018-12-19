package com.haizhi.utitl;
// Created by haiyang on 2016/12/28 10:23 and PROJECT_NAME: BdpTool

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtitl {
    public static void appendFile(String filename, ArrayList<String[]> al){
        Calendar calendar = Calendar.getInstance();

        // 如果当天不是周日，就立即返回，不做备份，用于控制备份频率，减少磁盘使用率
        if( calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY ){
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(calendar.getTime());
        String path = System.getProperty("user.dir") + "/data/" + filename + "." + dateStr;
        System.out.println(path);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), true));

            for(int i=0; i < al.size(); i++) {
                bw.write(Arrays.asList(al.get(i)) + "\n");
            }

            bw.flush();
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String extract(String line) {
        Pattern p = Pattern.compile("\\[.*?\\]");
        Matcher m = p.matcher(line);
        String ret = "";
        while(m.find()){ ret = m.group().replaceAll("\\[|\\]", ""); }
        return ret;
    }

}
