package time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeMain {
    public static void main(String[] args) {
        //①通过Date类来获取当前时间并转换成指定的格式
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));
        //或者
        Date date = new Date();
        String year = String.format("%tY", date);
        String month = String.format("%tm", date);
        String day = String.format("%td", date);
        String h = String.format("%tH", date);
        String m = String.format("%tM", date);
        String s = String.format("%tS", date);
        System.out.println(year+"-"+month+"-"+day+" "+h+":"+m+":"+s);

        //②获取System类中的currentTimeMillis方法来获取当前时间
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df2.format(System.currentTimeMillis()));

        //③通过Calendar类来获取当前时间
        Calendar  c  =  Calendar.getInstance();//可以对每个时间域单独修改
        int  year3  =  c.get(Calendar.YEAR);
        int  month3  =  c.get(Calendar.MONTH);
        int  day3  =  c.get(Calendar.DATE);
        int  hour  =  c.get(Calendar.HOUR_OF_DAY);
        int  minute  =  c.get(Calendar.MINUTE);
        int  second  =  c.get(Calendar.SECOND);
        System.out.println(year3+"-"+month3+"-"+day3+" "+hour+":"+minute+":"+second);
        //或者
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(calendar.getTime()));
    }
}
