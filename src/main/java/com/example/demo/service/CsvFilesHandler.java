package com.example.demo.service;

import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFilesHandler {

    public List<List<String>> csvReader(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        File targetFile = new File("temp");
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
        BufferedReader csvReader = new BufferedReader(new FileReader(targetFile));
        List<String> dataList = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            for(String temporaryString : data){
                dataList.add(temporaryString);
            }
        }
        List<List<String>> list = Lists.partition(dataList , 11);
        csvReader.close();
        return list;
    }
}
