package srm542;

/**
 * Created by opq.chen on 2016/8/16.
 */
public class PatrolRoute {
    public static int MOD = 1000000007;
    public int countRoutes(int X, int Y, int minT, int maxT){
        int fx[] = new int[2 * X + 1];
        int fy[] = new int[2 * Y + 1];
        for (int i = 0; i < X; ++i){ //x3的坐标
            for (int j = 0; j + 1 <  i; ++j){ //x1的坐标
                fx[2 * (i - j)] = (fx[2 * (i - j)] + i - j - 1) % MOD; //距离为 2（x3-x1）
            }
        }//预处理x轴
        for (int i = 0; i < Y; ++i){
            for (int j = 0; j + 1 < i; ++j){
                fy[2 * (i - j)] = (fy[2 * (i - j)] + i - j - 1) % MOD;
            }
        }
        int ret = 0;
        for (int i = 4; i < 2 * X; i++){//枚举x轴的距离总和
            if (fx[i] <= 0){
                continue;
            } // 如果可能数为0 没有意义进行下去
            int j = Math.max(4, minT - i); //算一下Y轴最少需要多少
            for (; i + j <= maxT && j <= 2 * Y; ++j){
                if (fy[j] <= 0){
                    continue;
                }
                ret =(int)((6L * fx[i] * fy[j] + ret) % MOD); // 统计结果
            }
        }
        return ret;
    }

    public static void main(String args[]){
        PatrolRoute route = new PatrolRoute();
        System.out.print(route.countRoutes(4000, 4000, 4000, 14000));
    }
}
