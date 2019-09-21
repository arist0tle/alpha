package alpha.common.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tanghaiyang on 2018/1/26.
 */
@Slf4j
public class FileUtils {
    public static String CHARSET = "utf-8";

    public static <T> T readJSONObject(String fileName, Class<T> cls) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return JSON.parseObject(readTxt(path), cls);
    }

    public static List<Map<String, Object>> readListMap(String fileName) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return JSON.parseObject(readTxt(path), new TypeReference<List<Map<String, Object>>>() {
        });
    }

    public static String readTxt(String filePath) {
        StringBuilder result = new StringBuilder();
        try {
            File file = new File(filePath);
            if (!file.isFile() || !file.exists()) {
                return "";
            }
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), CHARSET);
            BufferedReader bufferedReader = new BufferedReader(read);

            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                result.append(lineTxt);
                result.append("\n");
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String readTxtFile(String fileName) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return readTxt(path);
    }

    public static <T> T readObject(String fileName, Class<T> objectType) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return JSON.parseObject(readTxt(path), objectType);
    }

    /**
     * Recursive access folder all the files below.
     *
     * @param filePath  file path
     */
    public static List<File> getFiles(String filePath) {
        List<File> listFile = new ArrayList<>();
        try {
            File file = new File(filePath);
            log.info("Start scanning folder: {} {}", file.getPath(), file.isDirectory());
            if (file.isDirectory()) {
                if (!filePath.endsWith(File.separator)) {
                    filePath += File.separator;
                }
                String[] files = file.list();
                for(String fileStr:files){
                    String strPath = filePath + fileStr;
                    File tmp = new File(strPath);
                    if (tmp.isDirectory()) {
                        getFileList(listFile, tmp);
                    } else {
                        listFile.add(tmp);
                    }
                }
            } else {
                listFile.add(file);
            }
        } catch (Exception e) {
            log.info("Read files has error due to: " + e.getMessage());
        }
        log.info("File list size: {}", listFile.size());
        return listFile;
    }

    private static void getFileList(List<File> list, File dir) {
        File[] files = dir.listFiles();
        if (Objects.isNull(files)) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                getFileList(list, file);
            } else {
                list.add(file);
            }
        }

    }

    public static double getDirSize(File file) {
        double size = 0.0;
        if (!file.exists()) {
            log.warn("File does not exists,{}", file.getPath());
            return size;
        }
        if (!file.isDirectory()) {
            return (double) file.length() / 1024 / 1024;
        }
        File[] children = file.listFiles();
        if (Objects.isNull(children)) {
            return size;
        }
        for (File f : children) {
            size += getDirSize(f);
        }
        return size;
    }

    /**
     * @return get resource absolute path
     */
    public static String getResourcePath() {
        return FileUtils.class.getResource("/").getPath();
    }

    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
        File[] files = dir.listFiles();
        if (Objects.isNull(files)) {
            return;
        }
        for (File file : files) {
            listAllFiles(file);
        }
    }

    public static void copyFile(String src, String dist) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);

        byte[] buffer = new byte[20 * 1024];
        int cnt;
        /* read() 最多读取 buffer.length 个字节
          返回的是实际读取的个数
          返回 -1 的时候表示读到 eof，即文件尾
        */
        while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, cnt);
        }
        in.close();
        out.close();
    }

    public static void fastCopy(String src, String dist) throws IOException {
        /* 获得源文件的输入字节流 */
        FileInputStream fin = new FileInputStream(src);

        /* 获取输入字节流的文件通道 */
        FileChannel fcin = fin.getChannel();

        /* 获取目标文件的输出字节流 */
        FileOutputStream fout = new FileOutputStream(dist);

        /* 获取输出字节流的文件通道 */
        FileChannel fcout = fout.getChannel();

        /* 为缓冲区分配 1024 个字节 */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            /* 从输入通道中读取数据到缓冲区中 */
            int r = fcin.read(buffer);

            /* read() 返回 -1 表示 EOF */
            if (r == -1) {
                break;
            }

            /* 切换读写 */
            buffer.flip();

            /* 把缓冲区的内容写入输出文件中 */
            fcout.write(buffer);

            /* 清空缓冲区 */
            buffer.clear();
        }
    }
}
