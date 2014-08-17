package org.myGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author baihui.lbh
 * @version $Id: CsvParser.java, v 0.1 2014年8月16日 下午10:06:15 baihui.lbh Exp $
 */
public class CsvParser {
    private static final String DEFAULT_PATH = "/src/main/resource/";

    public File getCsvFile(String csvPath) {

        String filePath = System.getProperty("user.dir") + DEFAULT_PATH + csvPath;
        File file = new File(filePath);
        return file;
    }

    public List<String[]> readFromCsv(File file) throws Exception {
        //初始化读入文件
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            System.err.println("读入文件【" + file.getName() + "】初始化失败");
            throw e;
        }

        //读取文件内容
        List<String[]> tableList = null;
        try {
            InputStreamReader isr = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(isr);
            tableList = csvReader.readAll();
        } catch (Exception e) {
            System.err.println("通过CSV文件流读入数据失败");
            throw e;
        }
        return tableList;
    }
}
