package com.geektcp.alpha.util.office.service;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author haiyang on 3/26/20 3:05 PM.
 */
public class ExportWordServiceTest {

    @Test
    public void generateTextWord() {
        ExportWordService ew = new ExportWordService();
        XWPFDocument document = ew.createXWPFDocument();
        List<List<Object>> list = new ArrayList<>();
        List<Object> tempList = new ArrayList<>();
        tempList.add("姓名");
        tempList.add("黄xx");
        tempList.add("性别");
        tempList.add("男");
        tempList.add("出生日期");
        tempList.add("2018-10-10");
        list.add(tempList);
        tempList = new ArrayList<>();
        tempList.add("身份证号");
        tempList.add("36073xxxxxxxxxxx");
        list.add(tempList);
        tempList = new ArrayList<>();
        tempList.add("出生地");
        tempList.add("江西");
        tempList.add("名族");
        tempList.add("汉");
        tempList.add("婚否");
        tempList.add("否");
        list.add(tempList);
        tempList = new ArrayList<>();
        tempList.add("既往病史");
        tempList.add("无");
        list.add(tempList);

        Map<String, Object> dataList = new HashMap<>();
        dataList.put("TITLE", "个人体检表");
        dataList.put("TABLEDATA", list);
        ew.exportTextWord(dataList, document, "expWordTest.docx");
        System.out.println("文档生成成功");
    }

    @Test
    public void generatePictureWord() {
        ExportWordService ew = new ExportWordService();
        byte[] bytes = BarcodeService.generateByte("12345-22341-0000-0");
        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(bytes);
        ew.exportPictureWord(fileInputStream,  "/share/down/test.docx");
    }

}