package hackcup2017.qualification;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * Created by opq.chen on 2017/1/9.
 */
public class ProgressPie {
    private static boolean inner(int x1, int y1){
        if ((x1 - 50) * (x1 - 50) + (y1 - 50) * (y1 - 50) <= 2500){
            return true;
        }
        return false;
    }

    private static int getQuadrant(double X, double Y){
        if (X >= 50){
            if (Y >= 50){
                return 1;
            }else{
                return 2;
            }
        }else{
            if (Y <= 50){
                return 3;
            }else{
                return 4;
            }
        }
    }
    private static String check(int P, int X, int Y){
        if (P == 0){
            return "white";
        }//处理0的情况
        if (!inner(X, Y)){
            return "white";
        }//不在圆上
        //计算点B的坐标
        double x = 50.0 + Math.sin(2 * Math.PI * P / 100) * 50;
        double y = 50.0 + Math.cos(2 * Math.PI * P / 100) * 50;
        //判断象限
        if (getQuadrant(x, y) > getQuadrant(X, Y)){
            return "black";
        }else if (getQuadrant(x, y) < getQuadrant(X, Y)){
            return "white";
        }
        //叉积判左右
        if (cha(x - 50, y - 50, X - 50, Y - 50) >= 0){
            return "black";
        }
        return "white";
    }



    private static double cha(double x1, double y1, double x2, double y2){
        return x1 * y2 - x2 * y1;
    }

    public static void main(String args[]){
        //System.out.println(cha(0, 90, -90, 0));
        try{
            Scanner scanner = new Scanner(new FileInputStream("D:\\github\\TopCoder\\src\\hackcup2017\\qualification\\progresspie.in"));
            Writer writer = new FileWriter("D:\\github\\TopCoder\\src\\hackcup2017\\qualification\\progresspie.txt");
            int T = scanner.nextInt();
            int P, X, Y;
            for (int i = 1; i <= T; ++i){
                P = scanner.nextInt();
                X = scanner.nextInt();
                Y = scanner.nextInt();
                writer.write("Case #" + i +": ");
                writer.write(check(P, X, Y) + "\n");
            }
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(Math.sin(1.57));
    }
}
