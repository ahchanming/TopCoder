package srm685;

/**
 * Created by chanming on 16/6/10.
 */

/**
 Class:
 MultiplicationTable2
 Method:
 minimalGoodSet
 Parameters:
 int[]
 Returns:
 int
 Method signature:
 int minimalGoodSet(int[] table)
 (be sure your method is public)
 */
public class MultiplicationTable2 {
    int n;
    int map[][];
    int ans = n;
    boolean visit[];

    private void init(int[] table){
        n = (int)Math.sqrt(table.length);
        map = new int[n][n];
        for (int i = 0; i< n; ++i){
            for (int j = 0; j < n; ++j){
                map[i][j] = table[i * n + j];
            }
        }
    }

    private void dfs(int now){
        if (visit[now]) return;
        visit[now] = true;
        for (int i = 0; i < n; ++i){
            if (visit[i]){
                dfs(map[now][i]);
            }
        }
    }

    private int search(){
        int ans = n;
        visit = new boolean[n];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                visit[j] = false;
            }
            dfs(i);
            int tmp = 0;
            for (int j = 0; j < n; ++j){
                if (visit[j]){
                    tmp++;
                }
            }
            ans = tmp < ans ? tmp : ans;
        }
        return ans;
    }

    public int minimalGoodSet(int[] table){
        init(table);
        return search();
    }

    public static void main(String args[]){
        int[] table = {1,1,2,3,
                1,0,3,2,
                2,0,1,3,
                1,2,3,0};
        System.out.println(new MultiplicationTable2().minimalGoodSet(table));
    }
}
