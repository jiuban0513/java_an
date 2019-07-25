package com.an.java.monitortask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.跨级群节点（hadoop）读取某目录路径下的文件名称导入到指定文件中
 * 2.读取指定文件中的名称与数据库的做比对
 * 3.(本地没有搭建hadoop集群，没办法测试)，此代码的伪代码
 */
public class MonitorTask {
    private static final String DIRVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aeb123456";

    public static void main(String[] args) {
        try {
            //1.注册驱动
            Class.forName(DIRVER);
            //2.建立链接
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //3.获取执行sql语句
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("");
            //4.跨节点将指定目录路径下的文件名导入到指定的文件中
            Process p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "ssh hadoop010 \"ls /data/data00\" > /home/hadoop/monitor/monitortask.log"});
            p.waitFor();//同步操作，当文件导入完成之后在进行下面的操作
            //5.下面读取指定文件（）里面的数据
            List<String> list = new ArrayList<>();
            File file = new File("/home/hadoop/monitor/monitortask.log");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String s;
            while ((s=br.readLine())!=null){
                list.add(s);
            }
            //5.把指定文件读取到的数据与数据库进行对比，在进行其他操作

            //6.关闭连接
            ps.execute();
            ps.close();
            connection.close();
            System.out.println("本次操作成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
