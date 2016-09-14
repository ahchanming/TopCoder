package srm333;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by opq.chen on 2016/9/13.
 */
public class BirthNumbersValidator {

    private int getYear(long id){
        return (int)(id / 100000000 > 6 ? 1900 + id / 100000000 : 2000 + id / 100000000);
    }

    private int getMonth(long id){
        return (int)(id / 1000000 % 100 - 1 >= 50 ? id / 1000000 % 100 - 51 : id / 1000000 % 100 - 1);
    }

    private int getDate(long id){
        return (int)(id / 10000 % 100);
    }

    private boolean checkVaild(String str){
        long id = Long.valueOf(str);
        int year = getYear(id);
        int month = getMonth(id);
        int date = getDate(id);
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        try {
            calendar.set(year, month, date);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String[] validate(String[] test){
        List<String> list = new ArrayList<>();
        for (String each : test){
            list.add(checkVaild(each) ? "YES" : "NO");
        }
        String[] tmp = list.toArray(new String[test.length]);
        return tmp;
    }

    public void test(){
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(2016, 11, 5);
        System.out.println(calendar.getTime());
        calendar.set(2016, 10, 31);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DATE));
    }

    public static void main(String args[]){
        new BirthNumbersValidator().test();
        //String tmp[] = {"8102310007","8104121235"};
       // System.out.println(new BirthNumbersValidator().validate(tmp)[0]);
    }
}
