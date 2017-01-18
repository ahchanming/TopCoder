package hackcup2017.round1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * Created by opq.chen on 2017/1/15.
 */
public class ManicMoving {
    private static int DEFAULT_MAX = Integer.MAX_VALUE;
    public static int work(Scanner scanner){
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        int dist[][] = new int[N + 1][N + 1];
        //init
        for (int i = 0 ; i <= N ; ++i){
            for (int j = 0; j <= N ; ++j){
                dist[i][j] = i == j ? 0 : -1;
            }
        }
        //readEdge
        for (int i = 0; i < M; ++i){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if (dist[a][b] == -1){
                dist[a][b] = dist[b][a] = c;
            }else{
                dist[a][b] = dist[a][b] < c ? dist[a][b] : c;
                dist[b][a] = dist[a][b];
            }
        }
        //floyed
        for (int k = 1; k <= N; ++k){
            for (int i = 1; i <= N; ++i){
                if (dist[i][k] != -1){
                    for (int j = 1; j <= N; ++j){
                        if (dist[k][j] != -1){
                            if (dist[i][j] == -1){
                                dist[i][j] = dist[i][k] + dist[k][j];
                            }else{
                                dist[i][j] = dist[i][j] < dist[i][k] + dist[k][j] ? dist[i][j] : dist[i][k] + dist[k][j];
                            }
                        }
                    }
                }
            }
        }
        //readBeginend
        int begin[] = new int[K + 1];
        int end[] = new int[K + 1];
        for (int i = 1; i <= K; ++i){
            begin[i] = scanner.nextInt();
            end[i] = scanner.nextInt();
        }
        end[0] = 1;
        int f[][] = new int[K + 1][2];
        for (int i = 0; i <= K; ++i){
            f[i][0] = f[i][1] = DEFAULT_MAX;
        }
        f[0][0] = 0;
        for (int i = 0; i < K; ++i){
            if (f[i][0] < DEFAULT_MAX){
                if (dist[end[i]][begin[i + 1]] > -1 && dist[begin[i + 1]][end[i + 1]] > -1) {
                    f[i + 1][0] = Math.min(f[i + 1][0],
                            f[i][0] + dist[end[i]][begin[i + 1]] + dist[begin[i + 1]][end[i + 1]]);
                }
                if (i + 2 <= K && dist[end[i]][begin[i + 1]] > -1 && dist[begin[i + 1]][begin[i + 2]] > -1 && dist[begin[i + 2]][end[i + 1]] > -1){
                        f[i + 1][1] = Math.min(f[i + 1][1],
                                f[i][0] + dist[end[i]][begin[i + 1]] + dist[begin[i + 1]][begin[i + 2]] + dist[begin[i + 2]][end[i + 1]]);

                }
            }
            if (f[i][1] < DEFAULT_MAX){
                if (dist[end[i]][end[i + 1]] > -1){
                    f[i + 1][0] = Math.min(f[i + 1][0],
                            f[i][1] + dist[end[i]][end[i + 1]]);
                }
                if (i + 2 <= K &&  dist[end[i]][begin[i + 2]] > -1 && dist[begin[i + 2]][end[i + 1]] > -1){
                        f[i + 1][1] = Math.min(f[i + 1][1],
                                f[i][1] + dist[end[i]][begin[i + 2]] + dist[begin[i + 2]][end[i + 1]]);

                }
            }
        }
        return f[K][0] < DEFAULT_MAX ? f[K][0] : -1;
    }
    /*
    f[i][0]表示到第i个客户，身上没有其它货物
	f[i][0] --> f[i + 1][0] = dist[end[i]][begin[i + 1]] + dist[begin[i + 1]][end[i + 1]]
	f[i][0] --> f[i + 1][1] = dist[end[i]][begin[i + 1]] + dist[begin[i + 1]][begin[i + 2]] + dist[begin[i + 2]][end[i + 1]]
f[i][1]表示到第i个客户，身上还有第i加1个用户的货物
	f[i][1] --> f[i + 1][0] = dist[end[i]][end[i + 1]]
	f[i][1] --> f[i + 1][1] = dist[end[i]][begin[i + 2]] + dist[begin[i + 2]][end[i + 1]]
     */
    public static void main(String[] args){
        try {
            FileInputStream fileInputStream = new FileInputStream("Z:\\github\\TopCoder\\src\\facebookRound1\\ManicMoving.in.txt");
            Scanner scanner = new Scanner(fileInputStream);
            Writer writer = new FileWriter("Z:\\github\\TopCoder\\src\\facebookRound1\\ManicMoving.out.txt");
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
