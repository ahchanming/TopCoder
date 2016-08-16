package srm542;

/**
 * Created by opq.chen on 2016/8/16.
 */
public class PatrolRoute {
    public static int MOD = 1000000007;
    public int countRoutes(int X, int Y, int minT, int maxT){
        int fx[] = new int[2 * X + 1];
        int fy[] = new int[2 * Y + 1];
        for (int i = 0; i < X; ++i){
            for (int j = 0; j + 1 <  i; ++j){
                fx[2 * (i - j)] = (fx[2 * (i - j)] + i - j - 1) % MOD;
            }
        }
        for (int i = 0; i < Y; ++i){
            for (int j = 0; j + 1 < i; ++j){
                fy[2 * (i - j)] = (fy[2 * (i - j)] + i - j - 1) % MOD;
            }
        }
        int ret = 0;
        for (int i = 4; i < 2 * X; i++){
            if (fx[i] <= 0){
                continue;
            }
            int j = Math.max(4, minT - i);
            for (; i + j <= maxT && j <= 2 * Y; ++j){
                if (fy[j] <= 0){
                    continue;
                }
                ret =(int)((6L * fx[i] * fy[j] + ret) % MOD);
            }
        }
/*        for (int i = 0; i <= 8; ++i){
            if (fx[i] > 0){
                System.out.println("fx:[" + i + "]: " + fx[i]);
            }
        }
        for (int i = 0; i <= 8; ++i){
            if (fy[i] > 0){
                System.out.println("fy:[" + i +"]: " + fy[i]);
            }
        }*/
        return ret;
    }

    public static void main(String args[]){
        PatrolRoute route = new PatrolRoute();
        System.out.print(route.countRoutes(4000, 4000, 4000, 14000));
    }
}
