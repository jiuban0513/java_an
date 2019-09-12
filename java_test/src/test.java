import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    public static void main(String[] args) throws IOException {

//        List<Map<String, Object>> maps = new ArrayList<>();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name","zhangsan");
//        map.put("pass","123456");
//        maps.add(map);
//        Map<String, Object> map2 = new HashMap<String, Object>();
//        map2.put("name","lisi");
//        map2.put("pass","1234567");
//        maps.add(map2);
//        for (int i = 0; i < maps.size(); i++) {
//            System.out.println(maps.get(i).get("name")+"  "+maps.get(i).get("pass"));
//        }


//        ArrayList<String> list = new ArrayList<>();
//        list.add("aa");
//        list.add("ss");
//        list.add("dd");
//        list.add("ff");
//        ArrayList<String> list2 = new ArrayList<>();
//        list2.add("ff");
//        list2.add("dd");
//        list2.add("ss");
//        list2.add("aa");
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = 0; j < list2.size(); j++) {
//                System.out.println(i+"  "+j);
//                if (list.get(i).equals(list2.get(j))){
//                    list2.remove(j);
//                }
//            }
//        }
//        File file = new File("D:\\桌面文件");
//        FileReader fr = new FileReader(file);
//        LineNumberReader lineNumberReader = new LineNumberReader(fr);
//        String s = lineNumberReader.readLine();
//        while (s!=null){
//            System.out.println(s);
//            s = lineNumberReader.readLine();
//        }
//        File[] files = file.listFiles();
//        for (int i = 0; i < files.length; i++) {
//            System.out.println(files[i].getName());
//        }

        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8";
            Connection connection = DriverManager.getConnection(url, "root", "root");
            connection.setAutoCommit(true);//true-自动提交  false-手动提交
            //3.获取执行SQL方式
            //3.1connection.createStatement();//执行静态SQL语句的对象
            Statement statement = connection.createStatement();
            //3.1.4.查找（select）
            ResultSet resultSet = statement.executeQuery("select * from test_user");
            if(resultSet.next()){
                System.out.println("有");
            }else{
                System.out.println("meiy");
            }
//            List<Integer> integers = new ArrayList<>();
//            while (resultSet.next()){
//                integers.add(resultSet.getInt("id"));
//            }
//            PreparedStatement preparedStatement2 = connection.prepareStatement("update user set name=? where id = ?");
//            for (int i = 0; i < integers.size(); i++) {
//                preparedStatement2.setString(1,"name"+integers.get(i));
//                preparedStatement2.setInt(2,integers.get(i));
//                preparedStatement2.addBatch();
//            }
//            int[] ints = preparedStatement2.executeBatch();
//            for (int i = 0; i < ints.length; i++) {
//                System.out.println(ints[i]);
//            }
            //4.关闭资源
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
