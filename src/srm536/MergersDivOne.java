package srm536;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/8/8.
 * Class:
 MergersDivOne
 Method:
 findMaximum
 Parameters:
 int[]
 Returns:
 double
 Method signature:
 double findMaximum(int[] revenues)
 (be sure your method is public)
 */
public class MergersDivOne {
    public  double findMaximum(int[] revenues){
        Arrays.sort(revenues);
        double ret = (revenues[0] + revenues[1]) * 1.0 / 2;
        for (int i = 2; i < revenues.length; ++i){
            ret = (ret + revenues[i]) * 1.0 / 2;
        }
        return  ret;
    }
}
