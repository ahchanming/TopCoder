package hackcup2017.qualification;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by opq.chen on 2017/1/9.
 */
public class Zombie {
    static int H, S;
    static String spell;
    static int X, Y, Z;


    private static void readXYZ(String spell){
        Z = 0;
        String tmp[] = new String[3];
        if (spell.contains("-")){
            tmp = spell.split("-");
            Z = Integer.valueOf(tmp[1]) * -1;
        }else if (spell.contains("+")){
            tmp = spell.split("\\+");
            Z = Integer.valueOf(tmp[1]);
        }else{
            tmp[0] = spell;
        }
        String tmq[] = tmp[0].split("d");
        X = Integer.valueOf(tmq[0]);
        Y = Integer.valueOf(tmq[1]);
    }

    private static double calc(String spell){
        readXYZ(spell);
        double ans = 0;
        BigInteger f[][] = new BigInteger[X + 1][Y * X + 1];
        BigInteger BZero = new BigInteger("0");
        f[0][0] = new BigInteger("1");
        for (int i = 0; i < X; ++i){
            for (int j = 0 ; j<= i * Y; ++j){
                if (f[i][j] != null) {
                    for (int k = 1; k <= Y; ++k) {
                        if (f[i + 1][j + k] == null) {
                            f[i + 1][j + k] = new BigInteger("0");
                        }
                        f[i + 1][j + k] = f[i + 1][j + k].add(f[i][j]);
                    }
                }
            }
        }
        BigInteger sub = new BigInteger("0");
        BigInteger total = new BigInteger("1");
        for (int i = 0; i < X; ++i){
            total = total.multiply(new BigInteger(String.valueOf(Y)));
        }
        BigInteger need = new BigInteger(String.valueOf(Math.max(0,H - Z)));
        for (int i = Math.max(0, H - Z); i <= X * Y; ++i){
            if (f[X][i] != null) {
                sub = sub.add(f[X][i]);
            }
        }
        BigDecimal dSub = new BigDecimal(sub);
        BigDecimal dTotal = new BigDecimal(total);
        return dSub.divide(dTotal, 7, BigDecimal.ROUND_DOWN).doubleValue();
       // System.out.println("sub " + sub.toString() + " total" + total.toString());
       // return ans;
    }

    public static void main(String[] args){
        try{
            Scanner scanner = new Scanner(new FileInputStream("D:\\github\\TopCoder\\src\\hackcup2017\\qualification\\zombie.in"));
            Writer writer = new FileWriter("D:\\github\\TopCoder\\src\\hackcup2017\\qualification\\zombie.txt");
            int T = scanner.nextInt();
            for (int i = 1; i <= T; ++i){
                writer.write("Case #" + i +": ");
                H = scanner.nextInt();
                S = scanner.nextInt();
                double ans = 0;
                for (int j = 0; j < S; ++j){
                    spell = scanner.next();
                    double tmp = calc(spell);
                    ans = ans < tmp ? tmp : ans;
                }
                writer.write(String.format("%.8f", ans) + "\n");
            }
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
