package srm546;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class KleofasTail {

    private List<Integer> parse2Bit(long x){
        List<Integer> ret = new ArrayList<>();
        while (x > 0){
            ret.add((int)(x % 2));
            x /= 2;
        }
        return ret;
    }

    private long calc(long x, List<Integer> preBit){
        List<Integer> xBit = parse2Bit(x);
        if (xBit.size() < preBit.size()){
            return 0L;
        }
        long ret = 0;
        for (int i = 0;preBit.size() + i < xBit.size(); ++i){
           ret += (1L << i);
        }
        int j = xBit.size() - 1;
        int i = preBit.size() - 1;
        boolean ok = false;
        while ( i >= 0){ //计算位数跟x一样多有多少种情况。。
            if (preBit.get(i) == 1 && xBit.get(j) == 0){
                return ret;
            }
            if (preBit.get(i) == 0 && xBit.get(j) == 1){
                ok = true;
            }
            i--;
            j--;
        }
        if (ok && j >= 0){
            ret += (1L << (j + 1));
        }else {
            while (j >= 0) {
                if (xBit.get(j) == 1) {//如果这一位为1，那么这一位为0，后面可以为1
                    ret += (1L << j);
                }
                j--;
            }
            ret += 1;
        }
        return ret;
    }
    public long countGoodSequences(long K, long A, long B){
        if (K == 0){
            return (B - A + 1);
        }
        List<Integer> preBit;
        preBit = parse2Bit(K);
        System.out.println(calc(B, preBit));
        System.out.println(calc(A - 1, preBit));

        if(K %  2 == 1){
            return calc(B, preBit) - calc(A - 1, preBit);
        }else{
            return calc(B, preBit) - calc(A - 1, preBit) + countGoodSequences(K + 1, A, B);
        }
    }

    public static void main(String args[]){
        System.out.println(new KleofasTail().countGoodSequences(649, 5376633372L, 479537200249959803L));
    }
}
