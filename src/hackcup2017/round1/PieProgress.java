package hackcup2017.round1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/1/15.
 */
public class PieProgress {

    public static long work(Scanner scanner){
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int pie[][] = new int[N + 1][M + 1];
        for (int i = 1; i <= N; ++i){
            for (int j = 1; j <= M; ++j){
                pie[i][j] = scanner.nextInt();
            }
        }
        long f[][] = new long[N + 1][N + 1];
        for (int i = 0; i <= N; ++i){
            for (int j = 0; j <= N; ++j){
                f[i][j] = -1;
            }
        }
        //calcCost
        long c[][] = new long[N + 1][M + 1];
        for (int i = 1; i <= N; ++i){
            Arrays.sort(pie[i]);
            c[i][0] = 0;
            long total = 0;
            for (int j = 1; j <= M; ++j){
                total += pie[i][j];
                c[i][j] = total + j * j;
            }
        }
        f[0][0] = 0;
        for (int i = 1; i <= N; ++i){
            for (int j = N; j >= i; --j){
                for (int k = 0; k <= Math.min(j, M); ++k){
                    if (f[i - 1][j - k] != -1){
                        if (f[i][j] == -1){
                            f[i][j] = f[i - 1][j - k] + c[i][k];
                        }else{
                            f[i][j] = Math.min(f[i][j], f[i - 1][j - k] + c[i][k]);
                        }
                    }
                }
            }
        }
        return f[N][N];
    }
    public static void main(String[] args){
        try {
            FileInputStream fileInputStream = new FileInputStream("Z:\\github\\TopCoder\\src\\facebookRound1\\pie_progress.txt");
            Scanner scanner = new Scanner(fileInputStream);
            Writer writer = new FileWriter("Z:\\github\\TopCoder\\src\\facebookRound1\\PieProgress.out.txt");
            int T = scanner.nextInt();
            for (int i = 1; i <= T; ++i){
                writer.write(String.format("Case #%d: ", i));
                writer.write(work(scanner) + "\n");
            }
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
