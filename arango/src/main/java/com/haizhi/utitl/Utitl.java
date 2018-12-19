package com.haizhi.utitl;

/* Created by Haiyang on 2017/5/31. */

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Utitl {
    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static String randomDate() {
        Calendar calendar = Calendar.getInstance();

        //注意月份要减去1
        calendar.set(1990, 11, 31);
        calendar.getTime().getTime();

        //根据需求，这里要将时分秒设置为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        long min = calendar.getTime().getTime();
        ;
        calendar.set(2008, 2, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.getTime().getTime();
        long max = calendar.getTime().getTime();

        //得到大于等于min小于max的double值
        double randomDate = Math.random() * (max - min) + min;

        //将double值舍入为整数，转化成long类型
        calendar.setTimeInMillis(Math.round(randomDate));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(calendar.getTime());

//        return calendar.getTime().toString();
        return dateStr;
    }

    public static ArrayList<String> listPath(String path){
        ArrayList<String> arrayList = new ArrayList<>();

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File subfile : files) {
                    if (subfile.isDirectory()) {
//                        System.out.println("文件夹:" + subfile.getAbsolutePath());
//                        listPath(subfile.getAbsolutePath());
                    } else {
                        System.out.println("文件名: " + subfile.getName());
                        arrayList.add(subfile.getName());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }

        return arrayList;
    }

    public static ArrayList<HashMap> parseFileName(ArrayList<String> arrayList) throws Exception {
        ArrayList<HashMap> arrHashMap = new ArrayList<>();
        String[] arr = null;
        String[] tableName = null;

        for(int i=0;i<arrayList.size();i++){
            HashMap<String,String> map = new HashMap<>();
            arr = arrayList.get(i).split("#");
            if(arr[0].indexOf(":")!=-1){
                tableName = arr[0].split(":");
            }else if(arr[0].indexOf(";")!=-1){
                tableName = arr[0].split(";");
            }

            map.put("namespace", tableName[0]);
            map.put("tablename", tableName[1]);
            map.put("time_start", arr[1]);
            map.put("time_end", arr[2]);

            arrHashMap.add(map);
        }

        return arrHashMap;
    }


    public static String getMD5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2 将消息变成byte数组
            byte[] input = message.getBytes();

            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
    static public boolean isCompanyName(String name){
        if(name.length() > 4)
            return true;
        else return false;
    }
}
