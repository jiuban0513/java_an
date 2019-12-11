package com.an.java;

import java.sql.*;

/**
 * 原始JDBC连接Mysql数据库步骤模板
 */
public class JDBC_Mysql {
    public static void main(String[] args) {
        try {
            //1.加载驱动
            //Mysql: com.mysql.jdbc.Driver
            //Oracle: oracle.jdbc.driver.OracleDirver
            //Hive: org.apache.hive.jdbc.HiveDirver
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            //Mysql-url: jdbc:mysql://127.0.0.1:3306/数据库名
            //Oracle-url: jdbc:oracle:thin:@//127.0.0.1:1521/orcl
            //Hive: jdbc:hive2://127.0.0.1:21050/数据库名;auth=noSasl
            String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8";
            Connection connection = DriverManager.getConnection(url, "root", "password");
            connection.setAutoCommit(true);//true-自动提交  false-手动提交
            //3.获取执行SQL方式
                //3.1connection.createStatement();//执行静态SQL语句的对象
                Statement statement = connection.createStatement();
                    //3.1.1.添加（insert）
                    int i = statement.executeUpdate("insert into table_name values('username','password')");
                    //3.1.2.删除（delete）
                    int i1 = statement.executeUpdate("delete from table_name where id = 1");
                    //3.1.3.更改（update）
                    int i2 = statement.executeUpdate("update table_name set username = 'username' ,password = 'password' where id = 1");
                    //3.1.4.查找（select）
                    ResultSet resultSet = statement.executeQuery("select * from table_name where id = 1");
                //3.2connection.prepareStatement("sql");//执行预编译SQL语句对象
                    //3.2.1.添加（insert）
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into table_name values(?,?)");
                    preparedStatement.setString(1,"username");
                    preparedStatement.setString(2,"password");
                    int i3 = preparedStatement.executeUpdate();
                    //3.2.2.删除（delete）
                    PreparedStatement preparedStatement1 = connection.prepareStatement("delete from table_name where id = ?");
                    preparedStatement1.setInt(1,6);
                    int i4 = preparedStatement1.executeUpdate();
                    //3.2.3.更改（update）
                    PreparedStatement preparedStatement2 = connection.prepareStatement("update table_name set username = ?,password = ? where id = ?");
                    preparedStatement2.setString(1,"username");
                    preparedStatement2.setString(2,"password");
                    preparedStatement2.setInt(3,1);
                    int i5 = preparedStatement2.executeUpdate();
                    //3.2.4.查找（select）
                    PreparedStatement preparedStatement3 = connection.prepareStatement("select * from table_name where id = ?");
                    preparedStatement3.setInt(1,1);
                    ResultSet resultSet1 = preparedStatement3.executeQuery();
                //3.3connection.prepareCall("");//执行数据库存储过程
                CallableStatement callableStatement = connection.prepareCall("call procedure('insert/delete/update/select')");
                callableStatement.execute();
                ResultSet resultSet2 = callableStatement.getResultSet();
            //4.关闭资源
            resultSet.close();
            resultSet1.close();
            statement.close();
            callableStatement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
