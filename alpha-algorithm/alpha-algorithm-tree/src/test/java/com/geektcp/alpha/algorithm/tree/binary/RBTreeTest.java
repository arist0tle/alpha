package com.geektcp.alpha.algorithm.tree.binary;

import alpha.common.base.util.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by TangHaiyang on 2019/9/16.
 */
public class RBTreeTest {

    public static void main(String[] args) {
        System.out.println("傲慢与偏见");
        ArrayList<String> words = new ArrayList<>();
        String path = FileUtils.getResourcePath();
        if (readFile(path + "/" + "pride-and-prejudice.txt", words)) {
            System.out.println("共有单词数：" + words.size());
            RBTree<String, Integer> rbTree = new RBTree<>();
            for (String word : words) {
                if (rbTree.contains(word)) {
                    rbTree.set(word, rbTree.get(word) + 1);
                } else {
                    rbTree.add(word, 1);
                }
            }

            System.out.println("共有不同单词数：" + rbTree.getSize());
            System.out.println("出现pride的次数: " + rbTree.get("pride"));
            System.out.println("出现prejudice的次数: " + rbTree.get("prejudice"));
        }
    }

    /**
     * 读取一个文本文件所有单词，存入List
     * @param filename 文件的绝对路径
     * @param words  结果集合
     * @return 是否读取成功
     */
    private static boolean readFile(String filename, ArrayList<String> words) {
        if (filename == null || words == null) {
            System.out.println("文件名或words不能为空");
            return false;
        }

        //文件读取
        Scanner scanner;
        try {
            File file = new File(filename);
            System.out.println(file.getAbsoluteFile());
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                return false;
            }
        } catch (IOException ioe) {
            System.out.println("不能打开" + filename);
            return false;
        }

        /*
         * 简单分词
         * 这个分词方式相对简陋，没有考虑很多文本处理中的特殊问题
         * 这里只做demo展示用
         */
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();
            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
            }
        }

        return true;
    }

    /**
     * 寻找字符串s中，从start的位置开始的第一个字母字符的位置
     *
     * @param s  目标字符串
     * @param start 寻找的起始位置
     * @return 从起始位置开始的第一个字母的位置
     */
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }
}
