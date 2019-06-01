package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * Created by tanghaiyang on 2019/2/22.
 */
@Slf4j
public class FileUtil {

    public static void main(String[] args) throws Exception {
        String path = "data/edge/part-000";
        FileUtil fileUtil = new FileUtil();
        fileUtil.readFile(path);

    }

    @Test
    public void readFile(String resourceDir) throws Exception {
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    @Test
    public void readFile(String resourceDir, String charset) throws Exception{
//        String charset = "GBK";
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, charset));

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\|@\\|");
            log.info("arr[4]: " + arr[4]);

            break;
        }

    }


    @Test
    public void writeFile(String resourceDir) throws Exception{
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), true));
        String line = "this is the content!";
        bw.write(line);
        bw.flush();
        bw.close();
    }







}
