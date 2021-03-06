package srm692;

/**
 * Created by chanming on 16/6/10.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Class:
 HardProof
 Method:
 minimumCost
 Parameters:
 int[]
 Returns:
 int
 Method signature:
 int minimumCost(int[] D)
 (be sure your method is public)
 */
public class HardProof {
    int stop, bcnt, index;
    int[] dfn;
    int[] low;
    int[] stap;
    int[] belong;
    boolean[] instack;
    private void tarjan(int i, int map[][]){
        int n = map.length;
        dfn[i] = low[i] = ++index;
        instack[i] = true;
        stap[++stop] = i;
        for (int j = 0; j < n; ++j){
            if (map[i][j] > 0){
                if (dfn[j] == 0){
                    tarjan(j, map);
                    if (low[j] < low[i]){
                        low[i] = low[j];
                    }
                }else if (instack[j] && dfn[j] < low[i]){
                    low[i] = dfn[j];
                }
            }
        }
        if (dfn[i] == low[i]){
            bcnt++;
            int j;
            do{
                j = stap[stop--];
                instack[j] = false;
                belong[j] = bcnt;
            }while (j != i);
        }
    }

    private boolean checkOk(int map[][]){
        int n = map.length;
        stop = bcnt = index = 0;
        dfn = new int[51];
        low = new int[51];
        stap = new int[51];
        belong = new int[51];
        instack = new boolean[51];
        for (int i = 0; i < 50; ++i){
            dfn[i] = low[i] = 0;
            instack[i] =false;
        }
        boolean first = false;
        for (int i = 0; i < n; ++i){
            if (dfn[i] == 0){
                if (first) return false;
                first = true;
                tarjan(i, map);
            }
        }
        if (bcnt > 1) return false;
        return true;
    }

    private int binaryFind(int a[][], int l, int r, int D[]){
        int n = a.length;
        int[][] map = new int[n][n];
        int answer = 0;
        while (l < r){
            int mid = (l + r) / 2;
            boolean flag = false;
            for (int i = 0; i < n; ++i){
                if (flag) break;
                for (int j = 0; j < n; ++j){
                    if (flag) break;
                    if (i != j) {
                        int x = a[i][j];
                        for (int ii = 0; ii < n; ++ii) {
                            for (int jj = 0; jj < n; ++jj) {
                                if (a[ii][jj] >= x && a[ii][jj] <= x + mid) {
                                    map[ii][jj] = 1;
                                } else {
                                    map[ii][jj] = 0;
                                }
                                if (ii == jj) {
                                    map[ii][jj] = 0;
                                }
                            }
                        }
                        if (!flag){
                            flag = checkOk(map);
                        }
                    }
                }
            }
            if (flag){
                r = mid;
                answer = mid;
            }else{
                l = mid + 1;
            }
        }
        return answer;
    }

    public int minimumCost(int[] D){
        int n = (int)Math.sqrt(D.length);
        int a[][] = new int[n][n];
        int l = 0;
        int r = -1;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                a[i][j] = D[i * n + j];
                if (i != j){
                    r = a[i][j] > r ? a[i][j] + 1: r;
                }
            }
        }
        return binaryFind(a, l, r, D);
    }

    public static void main(String args[]){
//        int d[] = {0,11,13,13,
//                10,0,12,13,
//                10,10,0,11,
//                12,10,10,0};
//        int d[] = {0,1000,1000,1000,1000,
//                1,0,1000,1000,1000,
//                1,1,0,1000,1000,
//                1,1,1,0,1000,
//                1,1,1,1,0};
        int d[] = {0,44,47,42,44,45,47,42,47,43,46,46,42,47,47,45,44,46,43,47,44,43,43,47,47,44,46,47,44,44,46,43,44,42,42,44,43,43,46,44,45,44,43,43,42,47,47,45,45,42,
                44,0,44,43,43,46,44,47,43,46,44,42,42,46,44,44,45,44,43,46,46,46,47,47,44,47,42,44,45,44,42,42,43,46,46,44,43,44,42,45,44,45,44,45,46,44,42,47,43,43,
                42,46,0,42,44,43,42,42,43,46,45,45,45,45,46,46,47,46,42,42,43,45,44,43,45,47,47,47,45,47,46,44,43,46,42,47,42,47,43,43,47,45,44,42,44,44,47,47,42,45,
                45,43,47,0,47,42,43,47,47,45,43,43,46,46,43,43,43,45,42,45,42,45,47,45,42,47,45,44,42,44,42,43,45,45,42,47,45,42,45,47,43,44,42,42,45,47,44,42,47,45,
                43,43,46,47,0,44,47,42,45,45,46,46,42,45,43,47,42,45,42,47,44,43,43,45,47,46,47,44,43,42,42,47,44,42,45,47,44,44,44,47,43,42,46,47,46,47,44,43,45,45,
                42,43,44,45,43,0,43,43,43,44,42,47,42,44,47,45,46,43,47,46,47,43,44,45,43,47,44,44,46,43,43,43,44,43,43,46,47,47,43,44,44,44,44,47,43,43,43,47,45,47,
                46,45,46,43,47,42,0,43,42,46,42,42,42,44,47,46,46,43,46,44,42,45,45,46,47,47,47,47,42,47,46,45,42,47,45,45,44,44,47,42,47,43,43,44,47,46,47,44,43,45,
                46,43,45,47,44,47,45,0,44,43,45,47,44,45,44,47,45,42,47,46,44,46,44,44,45,45,43,47,44,44,45,45,42,42,42,42,45,46,42,43,43,42,44,45,47,46,47,47,45,42,
                46,46,47,44,43,46,44,42,0,43,44,45,43,46,43,42,45,43,43,42,46,47,43,44,44,42,44,47,45,46,42,45,43,45,44,42,45,45,42,43,46,47,43,46,47,46,43,47,46,45,
                43,46,45,44,43,45,42,42,46,0,44,47,43,42,46,42,45,45,46,45,47,44,45,44,46,47,45,43,45,46,46,45,47,44,46,42,44,46,44,44,46,43,42,45,42,43,42,45,44,44,
                42,45,46,42,46,42,42,45,44,47,0,45,43,44,44,46,43,42,42,42,45,47,44,44,44,47,44,43,43,47,42,45,47,42,45,45,43,42,45,42,47,44,44,44,44,45,42,45,43,42,
                47,47,46,43,44,45,45,42,42,43,44,0,43,43,43,46,42,43,42,43,43,46,47,47,42,43,46,47,43,42,43,42,45,45,42,47,45,42,42,44,46,44,43,42,47,47,46,47,47,46,
                42,43,42,43,43,46,43,47,47,44,44,45,0,47,44,42,44,44,42,46,44,44,46,42,46,47,43,45,46,42,43,46,43,45,42,47,47,47,45,47,47,45,47,45,42,47,43,42,45,44,
                43,47,47,46,46,43,42,45,47,47,44,44,47,0,44,45,47,45,44,43,46,43,42,43,44,43,42,43,47,42,42,47,44,46,44,47,44,47,47,42,42,42,45,46,45,47,45,44,43,47,
                43,47,47,42,44,42,45,43,44,47,43,42,46,42,0,43,46,44,42,43,47,45,47,46,46,42,47,42,42,44,44,47,42,43,44,42,42,44,43,45,46,45,42,45,43,47,47,46,45,47,
                42,42,43,47,46,46,43,42,46,43,43,43,42,45,47,0,42,43,46,47,46,46,44,42,47,46,42,46,44,45,47,46,45,45,42,44,43,46,42,45,45,45,42,47,42,47,42,46,44,44,
                42,44,45,46,42,45,44,45,47,43,44,45,44,45,46,42,0,43,44,44,46,45,45,43,42,43,47,46,46,43,46,47,46,45,43,47,44,42,42,47,45,46,46,47,43,42,44,43,47,47,
                45,42,45,46,43,43,42,46,44,46,47,46,47,44,46,42,43,0,44,44,47,45,45,42,43,42,44,46,43,45,45,43,46,47,45,45,46,43,44,47,45,47,45,46,45,47,46,43,45,47,
                42,45,47,47,46,42,42,43,43,47,46,42,43,47,46,43,42,45,0,42,45,46,42,47,43,47,42,47,44,44,42,44,45,44,46,44,43,46,42,47,46,43,44,47,42,42,44,47,42,45,
                47,42,45,42,44,47,44,44,43,46,45,46,46,42,45,45,42,42,43,0,45,43,44,42,47,43,43,45,47,42,42,43,46,42,44,47,47,46,44,43,47,43,43,46,47,47,45,45,45,46,
                42,42,46,46,45,44,42,43,43,45,44,44,46,42,44,46,47,42,43,45,0,43,42,42,46,46,42,45,45,45,44,46,45,43,43,47,42,47,47,43,44,43,44,44,46,45,44,43,43,46,
                44,44,47,42,46,42,45,47,46,44,43,42,42,44,46,47,46,45,42,47,46,0,43,43,47,42,44,44,42,44,45,45,46,44,46,44,47,46,44,46,46,47,45,42,45,44,43,42,42,42,
                43,42,44,44,42,44,44,42,47,42,47,47,47,45,45,43,43,45,47,47,46,42,0,42,45,42,47,42,44,43,46,46,42,46,43,43,45,43,46,47,44,42,47,45,43,46,46,47,44,45,
                44,46,44,43,44,47,46,42,47,47,47,46,43,47,42,47,42,44,44,42,47,46,44,0,44,45,44,42,43,43,42,43,42,46,45,43,44,42,46,43,46,47,43,45,44,46,45,42,42,42,
                47,46,44,44,42,47,43,47,47,46,42,45,44,43,44,43,45,42,43,43,42,42,45,46,0,46,43,45,43,47,44,42,47,43,42,43,46,45,45,43,43,42,45,45,44,45,42,42,45,45,
                43,44,47,46,43,43,44,42,44,46,45,45,42,42,44,46,47,44,47,44,46,44,47,47,43,0,43,42,45,42,45,45,44,44,45,46,45,47,45,45,47,47,46,47,46,45,45,43,45,44,
                46,42,46,42,44,44,45,45,44,44,46,45,43,43,47,43,44,44,43,46,46,43,47,47,45,44,0,44,42,43,47,42,42,47,44,43,45,47,44,43,44,47,43,45,47,42,43,45,46,42,
                44,47,43,43,45,44,46,42,47,45,46,45,45,42,45,42,44,45,45,47,47,42,47,46,44,42,42,0,44,43,47,47,47,42,44,45,44,42,44,44,47,46,42,45,46,47,42,43,44,43,
                42,43,42,44,45,45,47,42,42,45,43,47,45,46,46,44,42,46,43,42,47,42,43,47,47,44,45,42,0,42,43,46,46,44,44,45,42,45,43,45,44,42,45,43,44,45,47,47,44,45,
                47,42,42,46,44,44,46,44,42,47,45,44,43,45,42,44,46,43,44,42,43,42,46,43,44,45,46,47,42,0,45,43,45,43,44,45,46,47,46,45,47,43,44,42,42,46,47,42,43,44,
                44,44,45,46,47,42,42,45,44,42,44,47,47,44,45,45,45,43,46,42,45,47,43,43,42,44,43,47,47,42,0,47,43,45,45,44,46,43,42,42,44,42,45,43,46,43,42,47,43,42,
                42,45,43,47,45,46,46,42,43,44,42,47,44,47,45,44,44,45,42,46,47,45,44,46,42,47,42,46,45,42,46,0,44,44,43,45,44,44,44,44,43,44,42,42,44,47,47,45,45,42,
                43,43,43,46,44,43,43,46,45,45,44,43,42,45,47,42,45,45,46,42,44,42,46,47,43,44,42,44,42,46,43,47,0,42,42,42,45,47,46,45,43,46,45,45,46,47,45,44,45,46,
                43,47,45,42,46,47,43,46,44,45,45,42,44,47,44,44,46,47,46,46,42,46,43,45,43,45,46,42,46,42,47,46,42,0,43,47,43,42,43,44,47,44,45,42,46,46,47,43,46,43,
                42,42,44,45,43,45,45,47,47,45,45,43,42,45,42,45,45,43,43,43,46,42,44,47,43,43,42,43,46,42,47,46,47,43,0,44,45,46,42,47,45,44,46,45,46,44,47,46,42,44,
                43,44,45,45,47,44,44,45,42,45,45,47,46,47,43,46,45,47,44,47,42,44,44,42,45,42,42,43,47,43,42,47,43,47,42,0,43,42,46,46,43,46,45,46,44,45,42,42,47,46,
                45,47,42,45,45,42,42,44,42,44,42,42,47,47,43,46,47,47,42,43,42,44,42,46,45,44,47,43,45,42,44,44,47,43,43,46,0,45,46,42,43,47,46,44,45,43,47,42,43,46,
                45,45,44,43,43,45,45,42,42,43,44,47,46,44,42,47,46,45,42,44,43,45,44,46,46,44,47,42,47,44,45,47,47,47,46,42,47,0,46,46,44,44,46,45,47,45,45,44,45,44,
                47,42,45,46,43,47,42,43,47,42,42,42,46,42,44,42,42,46,45,43,44,46,47,47,43,47,45,44,45,42,46,47,44,44,43,47,42,42,0,46,46,45,47,42,45,46,44,43,44,42,
                46,42,46,44,44,44,44,46,42,44,44,45,42,44,47,45,44,47,44,42,46,43,45,45,45,43,46,44,44,47,43,42,46,45,47,43,42,46,45,0,43,45,43,42,43,45,43,47,47,42,
                43,44,46,43,45,42,42,43,43,45,42,46,42,47,45,44,43,42,47,44,42,42,42,44,42,42,46,44,45,42,47,42,42,44,42,46,47,42,44,44,0,45,44,43,45,44,42,47,43,47,
                45,43,45,45,42,47,42,46,43,45,43,46,43,42,47,45,45,44,47,47,47,43,44,42,47,47,44,45,44,42,43,47,47,45,46,46,43,45,47,44,45,0,47,47,45,42,45,47,42,42,
                46,42,45,45,47,46,47,46,45,43,43,43,46,45,46,46,47,44,45,44,46,47,45,42,45,45,45,42,44,43,45,42,47,47,46,44,43,45,46,45,44,43,0,44,45,46,47,45,46,43,
                44,43,43,42,47,45,46,46,43,42,43,45,42,42,42,42,45,46,47,47,45,46,45,43,47,47,45,47,45,42,46,45,46,45,45,44,47,47,45,44,44,47,42,0,44,47,47,43,47,42,
                46,46,45,47,42,42,47,42,47,45,44,47,46,46,45,43,44,42,44,43,43,47,43,43,47,44,42,42,46,42,46,44,47,47,45,45,44,42,45,47,44,45,46,43,0,44,45,45,46,47,
                46,46,44,42,42,44,46,45,45,46,44,43,46,45,43,47,47,47,42,42,42,42,45,44,45,45,42,45,44,42,46,43,43,43,44,42,43,43,45,43,47,47,43,43,45,0,43,44,44,46,
                43,43,42,42,45,47,42,46,44,45,45,45,45,42,44,45,42,43,45,43,43,43,47,43,44,45,47,43,47,45,43,43,45,42,46,47,43,47,44,43,43,42,42,42,43,45,0,44,42,42,
                47,43,46,42,45,43,47,44,47,45,44,45,43,42,42,46,46,44,46,42,46,43,44,43,42,42,47,45,47,46,45,42,42,47,45,45,46,47,43,43,44,44,42,43,43,42,47,0,42,43,
                45,45,46,47,42,47,46,46,43,44,46,47,43,46,46,42,47,45,42,45,47,42,42,45,47,43,42,43,43,42,45,47,45,43,43,45,43,43,47,42,46,43,45,46,47,45,47,46,0,45,
                46,46,42,47,47,47,42,44,43,46,43,47,43,43,46,45,42,45,47,42,47,42,44,42,43,47,43,45,42,44,45,46,43,46,46,44,46,43,42,44,45,42,42,47,43,47,45,46,42,0};
        System.out.print(new HardProof().minimumCost(d));
    }
}
