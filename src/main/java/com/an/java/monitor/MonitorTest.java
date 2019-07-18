package com.an.java.monitor;

import java.io.File;
import java.util.ArrayList;

public class MonitorTest {

    public static void main(String[] args) {
        ArrayList<String> fileList = getFileList();
    }


    public static ArrayList<String> getFileList(){
        File file = new File("D:\\桌面文件");
        File[] files = file.listFiles();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            if(files[i].isFile()&&name.substring(name.length()-4,name.length()).equals(".txt")){
                list.add(name);
                System.out.println(name);
            }
        }
        return list;
    }


}
