import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test2 {
    public static void main(String[] args) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH,-1);
        Date time = c.getTime();
        System.out.println(time);
//        System.out.println(getCurrentMonthDay());

    }
    public static int getCurrentMonthDay() {
    Calendar a = Calendar.getInstance();
a.set(Calendar.DATE, 1);
 a.roll(Calendar.DATE, -1);
 int maxDate = a.get(Calendar.DATE);
 return maxDate;
 }
}
