package srm535;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chanming on 16/8/3.
 * Class:
        FoxAndGCDLCM
   Method:
        get
   Parameters:
        long, long
   Returns:
        long
   Method signature:
        long get(long G, long L)
   (be sure your method is public)
 */

public class FoxAndGCDLCM {
    private Map<Long, Integer> factors = new HashMap<Long, Integer>();

    private static int maxFactor = 1000010;
    private static List<Integer> primes = new ArrayList<Integer>();
    private long ret;
    static{
        //ËØÊýÉ¸Ñ¡
        boolean number[] = new boolean[maxFactor];
        for (int i = 2; i < maxFactor; ++i){
            if (!number[i]){
                primes.add(i);
                for (int j = i + i; j < maxFactor; j += i){
                    number[j] = true;
                }
            }
        }
    }

    private void doFactor(long num){
        for (long each : primes){
            while (num > 1 && num % each == 0){
                if (factors.containsKey(each)){
                    factors.put(each, factors.get(each) + 1);
                }else{
                    factors.put(each, 1);
                }
                num /= each;
            }
        }
        if (num > 1){
            factors.put(num, 1);
        }
    }

    private void find(long x, long y, int dep, List<Long> mulList){
        if (dep == mulList.size()){
            ret = x + y < ret ? x + y : ret;
            return;
        }
        find(x * mulList.get(dep), y, dep + 1, mulList);
        find(x, y * mulList.get(dep), dep + 1, mulList);
    }

    public long get(long G, long L){
        if (L % G != 0){
            return -1;
        }
        factors.clear();
        doFactor(L / G);
        ret = Long.MAX_VALUE;
        List<Long> mulList = new ArrayList<Long>();
        for (Map.Entry<Long, Integer> each : factors.entrySet()){
            long tmp = 1;
            for (int i = 0; i < each.getValue(); ++i){
                tmp *= each.getKey();
            }
            mulList.add(tmp);
        }
        find(1L, 1L, 0, mulList);
        return ret * G;
    }

    public static void main(String[] args){
        System.out.println(new FoxAndGCDLCM().get(1L, 999999999989L));
    }
}
