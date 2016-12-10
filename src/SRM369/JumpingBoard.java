package SRM369;

/**
 * Created by opq.chen on 2016/12/10.
 */
public class JumpingBoard {
    private int ans;
    private int[][] map;
    private boolean[][] visit;
    private int[][] dst;
    private int n,m;
    private final static int HOLE =24;

    private void DFS(int dep, int x, int y){
        //System.out.println(dep + " " + x + " " + y);
        if (ans == -1){
            return;
        }else if (dep > ans){
            ans = dep;
        }
        if (x < 0 || y < 0 || x >= n || y >= m || map[x][y] == HOLE ){
            return;
        }
        if (visit[x][y]){
            ans = -1;
            return;
        }
        if (dep <= dst[x][y]) {
            return;
        }
        dst[x][y] = dep;
        visit[x][y] = true;
        DFS(dep + 1, x + map[x][y], y);
        DFS(dep + 1, x - map[x][y], y);
        DFS(dep + 1, x, y + map[x][y]);
        DFS(dep + 1, x, y - map[x][y]);
        visit[x][y] =false;
    }

    public int maxJumps(String[] board){
        ans = 0;
        n = board.length;
        m = board[0].length();
        map = new int[n][m];
        visit = new boolean[n][m];
        dst = new int[n][m];
        dst[0][0] = -1;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                map[i][j] = board[i].charAt(j) - '0';
            }
        }
        DFS(0, 0, 0);
        return ans;
    }
}
