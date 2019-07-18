package com.an.java;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )    {
//        String str = "123456789";
//        String substring = str.substring(str.length() - 3, str.length());
//        System.out.println(substring);
//        String[] split = str.split(",");
//        Date date = Date.valueOf("2019-07-16");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }
}
