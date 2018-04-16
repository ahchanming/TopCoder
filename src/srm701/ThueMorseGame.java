package srm701;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThueMorseGame {
    static List<Boolean> isOdd;
    static final int step = 4096;

    public static boolean checkOdd(int tmp){
        int count = 0;
        while (tmp > 0){
            tmp = tmp & (tmp - 1);
            count++;
        }
        return count % 2 > 0;
    }

    public static void init(){
        isOdd = new ArrayList<>();
        isOdd.add(false);
        for (int i = 1; i <= step; ++i){
            if (checkOdd(i)){
                isOdd.add(true);
            }else{
                isOdd.add(false);
            }
        }
    }

    static Map<Integer, Integer> winStatus;

    public int dfs(int n, int m){
        if (winStatus.containsKey(n)){
            return winStatus.get(n);
        }
        if (isOdd.get(n) == true){
            winStatus.put(n, 1);
            return 1;
        }
        for (int i = 1;i <= m; ++i){
            if (n - i >= 0){
                if (dfs(n - i, m) == 2){
                    winStatus.put(n, 1);
                    return 1;
                }
            }
        }
        winStatus.put(n, 2);
        return 2;
    }

    static int tmq = 0;
    static boolean checkTmq = false;

    private static boolean checkOdd2(int tmp){
        if (tmp >= tmq + step){
            tmq = tmq + step;
            checkTmq = checkOdd(tmq);
        }
        tmp = tmp % step;
        return isOdd.get(tmp) ^ checkTmq;
    }

    public String get(int n, int m){
        init();
//        for (int i = 0; i < 500; ++i){
//            System.out.println(i + " " + checkOdd(i));
//        }
        int i = 0;
        while (i < n){
            if (!checkOdd2(i + m + 1)){
                i = i + m + 1;
            }else if ((i + m + 2) % 2 > 0 || !checkOdd2(i + m + 2)){
                i = i + m + 2;
            }else {
                i = i + m + 3;
            }
        }
        if (i == n){
            return "Bob";
        }else{
            return "Alice";
        }
//        winStatus = new HashMap<>(n);
//        winStatus.put(0, 2);
//        dfs(n, m);
//        int lastOne = 0;
//        for (int i = 1; i < 8000; ++i){
//            if (winStatus.get(i) == 2){
//                System.out.println(i + " " + (i - lastOne) + " "  + isOdd.get(i));
//                System.out.println((i + 28) + " " + isOdd.get(i + 28));
//                System.out.println((i + 29) + " " + isOdd.get(i + 29));
//                System.out.println((i + 30) + " " + isOdd.get(i + 30));
//                lastOne = i;
//            }
//        }
//        if (dfs(n, m) == 2){
//            return "Bob";
//        }else{
//            return "Alice";
//        }
    }

    public static void main(String[] args) {
        long time = new Date().getTime();
        System.out.println(new ThueMorseGame().get(499999975, 1));
        long end = new Date().getTime();
        System.out.println(end -time);
    }
}
