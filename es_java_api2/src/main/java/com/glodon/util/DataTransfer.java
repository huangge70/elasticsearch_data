package com.glodon.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glodon.model.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataTransfer {
    public static void main(String[] args) {
        List<Position> list=readFile();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public static List<Position> readFile() {
        String pathname = "D:/data.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        List<Position> list=new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            int id=1;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                //System.out.println(line);
                Position position=mapper.readValue(line, Position.class);
                position.setId(id+"");
                list.add(position);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
