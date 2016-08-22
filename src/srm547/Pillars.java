package srm547;

/**
 * Created by opq.chen on 2016/8/22.
 */
public class Pillars {
    public double getExpectedLength(int w, int x, int y){
        long totalNum = 0;
        Double totalLenth = 0.0;
        if (x > y){
            int t = x;
            x = y;
            y = t;
        }
        for (int h = y - 1; h > 0; --h){
            int num = Math.min(y - h, x);
            totalNum += num;
//            double tmp = Math.sqrt(1L * w * w + 1L * h * h);
//            double tmq = totalNum;
//            totalLenth += tmp * tmq;
            totalLenth = totalLenth + (Double)Math.sqrt(1L * w * w + 1L *  h * h) * num;
//            if (h < 92690){
//                break;
//            }
        }
      //  System.out.println(totalLenth);
        for (int h = x - 1; h > 0; --h){
            int num = x - h;
            totalNum += num;
            totalLenth += 1.0 * num * Math.sqrt(1L * w * w + 1L * h * h);
        }

        totalNum += x;
        totalLenth += x * w;
        System.out.println(totalLenth + "   " + totalNum);
        return totalLenth / totalNum;
    }

    public static void main(String args[]){
//        System.out.println(Double.MAX_VALUE);
//        System.out.println(Double.MAX_VALUE / 10);
        System.out.println(new Pillars().getExpectedLength(1, 99175, 56445));
    }
}
