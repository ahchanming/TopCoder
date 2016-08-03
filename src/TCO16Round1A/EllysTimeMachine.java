package TCO16Round1A;

/**
 * Created by chanming on 16/6/18.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class:
 EllysTimeMachine
 Method:
 getTime
 Parameters:
 String
 Returns:
 String
 Method signature:
 String getTime(String time)
 */

public class EllysTimeMachine {
    public String getTime(String time){
        String[] hour = {"12", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        String[] min = {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
        List lHour = Arrays.asList(hour);
        List lMin = Arrays.asList(min);
        String[] element = time.split(":");
        int indexHour = lHour.indexOf(element[0]);
        int indexMin = lMin.indexOf(element[1]);
        return lHour.get(indexMin) + ":" + lMin.get(indexHour);
    }
}
