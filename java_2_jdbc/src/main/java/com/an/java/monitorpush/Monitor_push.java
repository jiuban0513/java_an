package com.an.java.monitorpush;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取某路径下的文件名称和数据库中的数据做比对。
 */
public class Monitor_push {
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from monitor_push where status = ?");
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            List<String> listData = new ArrayList<>();
            List<Integer> listId = new ArrayList<>();
            while (resultSet.next()){
                listData.add(resultSet.getString("fileUrl")+","+resultSet.getString("fileName"));
                listId.add(resultSet.getInt("id"));
            }
            System.out.println("数据库共有"+listId.size()+"条数据库未推送！");
            resultSet.close();
            preparedStatement.close();
            //4.读取指定目录下的文件，获取文件名
            PreparedStatement preparedStatement1 = connection.prepareStatement("update monitor_push set status = ? where id = ?");
            int num = 0;
            for (int i = 0; i < listData.size(); i++) {
                String[] split = listData.get(i).split(",");
                File file = new File(split[0]);
                File[] files = file.listFiles();
                if (files!=null&&!files.equals("")){
                    for (int j = 0; j < files.length; j++) {
                        String name = files[j].getName();
                        if(files[j].isFile()&&
                                name.substring(name.length()-4,name.length()).equals(".txt")&&
                                name.equals(split[1])){
                                preparedStatement1.setInt(1,2);
                                preparedStatement1.setInt(2,listId.get(i));
                                preparedStatement1.addBatch();
                                num ++;
                        }
                    }
                }else{
                    continue;
                }
            }
            int[] ints = preparedStatement1.executeBatch();
            connection.commit();
            preparedStatement1.close();
            connection.commit();
            if(ints.length>0&&ints[0]==1&&ints.length==num){
                System.out.println("成功推送"+ints.length+"条数据!");
            }else{
                System.out.println("推送失败!");
            }
            System.out.println("本次操作成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
