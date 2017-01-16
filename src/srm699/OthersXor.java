package srm699;

/**
 * Created by Administrator on 2016/9/26.
 */
public class OthersXor {
    public long minSum(int[] x){
        int ret = 0;
        for (int each : x){
            ret |= each;
        }
        return ret;
    }

    public static void main(String args[]){
        //int x[] = {1287325,424244444,92759185,812358213,1000000000,825833522,749092703};
        int x[] = {0,536870912,0,536870912,0,536870912,0,536870912,0,536870912,
                0,536870912,0,536870912,0,536870912,0,536870912,0,536870912,
                1073741823,1073741823,1073741823,123456789,987654321,804289383};
        System.out.println(new OthersXor().minSum(x));
    }
}
