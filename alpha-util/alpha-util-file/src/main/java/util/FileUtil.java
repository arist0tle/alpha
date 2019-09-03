package util;

import org.junit.Test;

import java.io.*;
import java.util.Objects;

/**
 * Created by tanghaiyang on 2019/2/22.
 */
public class FileUtil {

    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
        File[] files = dir.listFiles();
        if(Objects.isNull(files)){
            return;
        }
        for (File file : files) {
            listAllFiles(file);
        }
    }



}
