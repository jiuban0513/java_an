package com.an.java.publicspace;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1.读取磁盘使用率
 * 2.
 */
public class PublicSpace {
    public static void main(String[] args) {

        try {
            //1.JDBC连接数据库

            //2.跨节点读取目录所在磁盘的使用率
            Process p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "ssh hadoop012 df -m /data/data08"});
            BufferedInputStream bins = new BufferedInputStream(p.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bins));
            String s;
            while ((s = br.readLine())!=null){
                System.out.println(s);
                String[] split = s.split("\\s+");//以空格分隔
                for (int i = 0; i < split.length; i++) {
                    System.out.println(split[i]);
                }
            }
            //3.

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
