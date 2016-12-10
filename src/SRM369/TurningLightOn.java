package SRM369;

/**
 * Created by opq.chen on 2016/12/10.
 */
public class TurningLightOn {
    public int minFlips(String[] board){
        int r = board.length;
        int c = board[0].length();
        int ans = 0;
        Boolean[][] map = new Boolean[r][c];
        for (int i = 0; i < r; ++i){
            for (int j = 0; j < c; ++j){
                map[i][j] = board[i].charAt(j) == '1';
            }
        }
        for (int i = r - 1; i >= 0; --i){
            for (int j = c - 1; j >= 0; --j){
                if (!map[i][j]){
                    ans += 1;
                    for (int ix = 0; ix <= i; ++ix){
                        for (int jx = 0; jx <= j; ++jx){
                            map[ix][jx] = !map[ix][jx];
                        }
                    }
                }
            }
        }
        return ans;
    }
}
