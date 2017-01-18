package hackcup2017.qualification;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by opq.chen on 2017/1/9.
 */
public class LazyLoading {
    private static int calc(int[] weight, int n){
        Arrays.sort(weight);
        int i = 0;
        int j = n - 1;
        int ans = 0;
        while (i <= j){
            if (weight[j] >= 50){
                ans++;
                j--;
            }else{
                int tot = 1;
                int w = weight[j];
                j--;
                while (i <= j){
                    tot++;
                    i++;
                    if (tot * w >= 50){
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    };

    public static void main(String args[]){
        try {
            FileWriter fileWriter = new FileWriter("D:\\github\\TopCoder\\src\\hackcup2017\\qualification\\lazy.out");
            Scanner scanner = new Scanner(new FileInputStream("D:\\github\\TopCoder\\src\\hackcup2017\\qualification\\lazy.in"));
            int T = scanner.nextInt();
            for (int i = 1; i <= T; ++i){
                fileWriter.write("Case #" + i +": ");
                int N = scanner.nextInt();
                int weights[] = new int[N];
                for (int j = 0; j < N; ++j){
                    weights[j] = scanner.nextInt();
                }
                fileWriter.write(calc(weights, N) + "\n");
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
