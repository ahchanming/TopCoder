package srm370;

import java.math.BigDecimal;

/**
 * Created by opq.chen on 2016/12/13.
 */
public class DrawingMarbles {

    private BigDecimal getCNM(int c, int n){
        BigDecimal cSumN = new BigDecimal(1);
        for (int i = 1; i <= c; ++i){
            cSumN = cSumN.multiply(new BigDecimal(i));
        }
        for (int i = 1; i <= n; ++i){
            cSumN = cSumN.divide(new BigDecimal(i));
        }
        for (int i = 1; i <= (c - n); ++i){
            cSumN = cSumN.divide(new BigDecimal(i));
        }
        return cSumN;
    }

    public double sameColor(int[] colors, int n){
        int sum = 0;
        for (int color : colors){
            sum += color;
        }
        BigDecimal cSumN = getCNM(sum, n);
        BigDecimal total = new BigDecimal(0);
        for (int color : colors){
            if (color >= n){
                total = total.add(getCNM(color, n));
            }
        }
        //System.out.println(cSumN.toString());
       // System.out.println(total.toString());
        return total.divide(cSumN, 12, BigDecimal.ROUND_DOWN).doubleValue();
    }
}
