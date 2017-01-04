package srm371;

/**
 * Created by opq.chen on 2017/1/4.
 */
public class SpiralRoute {
    boolean visit[][];
    final static int dx[] = {1, 0, -1, 0};
    final static int dy[] = {0, 1, 0, -1};
    public int[] thronePosition(int width, int length){
        visit = new boolean[5001][5001];
        int dst = 0;
        int nowX = 0;
        int nowY = 0;
        for (int i = 1; i < width * length; ++i){
            visit[nowX][nowY] = true;
            if (nowX + dx[dst] < 0 || nowX + dx[dst] >= width || nowY + dy[dst] < 0 || nowY + dy[dst] >= length
                    || visit[nowX + dx[dst]][nowY + dy[dst]]){
                dst = (dst + 1) % 4;
            }
            nowX += dx[dst];
            nowY += dy[dst];
        }
        int ret[] = {nowX, nowY};
        System.out.println(nowX + "  " + nowY);
        return ret;
    }
}
