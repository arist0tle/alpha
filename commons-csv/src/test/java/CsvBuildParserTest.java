import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by HaiyangWork on 2018/12/19.
 */
public class CsvBuildParserTest {

    private final static String NEW_LINE_SEPARATOR = "\n";

    public static void main(String[] args) throws Exception {
        String[] header = new String[]{"id", "age", "address", "color"};
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1", "dddd", "shenzhen", "red"});
        list.add(new String[]{"2", "dddd", "shenzhen", "yellow"});
        list.add(new String[]{"3", "dddd", "shenzhen", "blue"});

        String userdir = System.getProperty("user.dir");
        String rootDir = userdir + "/commons-csv/src/test/resources/data1/";
//        String path = rootDir + "test.csv";
//        writeCsv(header, list, path);
//        readCSV(header, path);

        String path = rootDir + "tv_address/part-0.csv";
        readCSV(header, path);
    }


    /**
     * 写入csv文件
     *
     * @param headers  列头
     * @param data     数据内容
     * @param filePath 创建的csv文件路径
     */
    @SuppressWarnings("all")
    private static void writeCsv(String[] headers, List<String[]> data, String filePath) throws IOException {
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        File file = new File(filePath);
        if (file.exists()) file.delete();
        FileWriter fileWriter = new FileWriter(new File(filePath), true);
        CSVPrinter printer = new CSVPrinter(fileWriter, formator);
        printer.printRecord(headers);
        if (null != data) {
            for (String[] lineData : data) {
                printer.printRecord(lineData);
                System.out.println(Arrays.toString(lineData));
            }
        }

        printer.flush();
        printer.close();
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }


    /**
     * 读取csv文件
     *
     * @param headers  csv列头
     * @param filePath 文件路径
     * @return CSVRecord 列表
     * @throws IOException
     **/
    public static List<Map<String,String>> readCSV(String[] headers, String filePath) throws IOException {
        CSVFormat formator = CSVFormat.DEFAULT.withHeader(headers);
        FileReader fileReader = new FileReader(filePath);
        CSVParser parser = new CSVParser(fileReader, formator);
        List<CSVRecord> records = parser.getRecords();
        parser.close();
        fileReader.close();

        List<Map<String,String>> list = new ArrayList<>();
        for(CSVRecord record:records){
            System.out.println(record.toMap());
            list.add(record.toMap());
        }

        return list;
    }
}
