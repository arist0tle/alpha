

import alpha.common.base.util.FileUtils;
import org.junit.Test;

import java.io.*;

/**
 * @author tanghaiyang on 2019/9/3.
 */
public class FileUtilTest {

    @Test
    public void listAllFilesTest(){
        File file = new File("F:\\tmp");
        FileUtils.listAllFiles(file);
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
