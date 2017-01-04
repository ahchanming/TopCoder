package srm371;

/**
 * Created by opq.chen on 2017/1/4.
 */
public class FloodRelief {
    int n, m, total;
    boolean visit[][];
    private int[] findlowest(String[] heights){
        char low = 127;
        int retX = 0, retY = 0;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                if (!visit[i][j] && heights[i].charAt(j) < low){
                    low = heights[i].charAt(j);
                    retX = i;
                    retY = j;
                }
            }
        }
        int ret[] = {retX, retY};
        return ret;
    }

    private void doflow(String[] heights, int nowX, int nowY, char last){
        if (nowX < 0 || nowY < 0 || nowX >= n || nowY >= m || visit[nowX][nowY] || heights[nowX].charAt(nowY) < last){
            return ;
        }
        total += 1;
        visit[nowX][nowY] = true;
        doflow(heights, nowX + 1, nowY, heights[nowX].charAt(nowY));
        doflow(heights, nowX - 1, nowY, heights[nowX].charAt(nowY));
        doflow(heights, nowX, nowY + 1, heights[nowX].charAt(nowY));
        doflow(heights, nowX, nowY - 1, heights[nowX].charAt(nowY));
    }

    public int minimumPumps(String[] heights){
        n = heights.length;
        m = heights[0].length();
        visit = new boolean[n][m];
        total = 0;
        int ans = 0;
        while (total < n * m){
            ans++;
            //find lowest
            int[] dst = findlowest(heights);
            //doflow
            doflow(heights, dst[0], dst[1], heights[dst[0]].charAt(dst[1]));
        }
        return ans;
    }
}
