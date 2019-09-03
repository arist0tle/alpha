package util;

import org.junit.Test;

import java.io.*;
import java.util.Objects;

/**
 * Created by tanghaiyang on 2019/2/22.
 */
public class FileUtil {

    private void listAllFiles(File dir) {
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

    @Test
    public void listAllFilesTest(){
        File file = new File("F:\\tmp");
        listAllFiles(file);
    }

    public static void main(String[] args) throws Exception {
        FileUtil fileUtil = new FileUtil();
        fileUtil.readFile();
    }

    @Test
    public void readFile() throws Exception {
        String resourceDir = "data/edge/part-000";
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    @Test
    public void readFileCharset() throws Exception{
        String resourceDir = "data/edge/part-000";
        String charset = "GBK";
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, charset));

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\|@\\|");
//            log.info("arr[4]: " + arr[4]);
            break;
        }
    }

    @Test
    public void writeFile() throws Exception{
        String resourceDir = "data/edge/part-000";
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), true));
        String line = "this is the content!";
        bw.write(line);
        bw.flush();
        bw.close();
    }





}
