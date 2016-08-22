package srm537;

/**
 * Created by Administrator on 2016/8/9.
 */
public class KingXNewCurrency {
    static int maxSize = 200 * 200 + 1;
    private boolean check(int A, int B, int X, int Y, boolean[] fa){
        boolean fb[] = new boolean[maxSize];
        fb[0] = true;
        for (int i = 0; i < A * B * 2; ++i){
            if (i < maxSize){
                if (fb[i]){
                    if (i + X < maxSize){
                        fb[i + X] = true;
                    }
                    if (i + Y < maxSize){
                        fb[i + Y] = true;
                    }
                }else{
                    if (fa[i]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public int howMany(int A, int B, int X){
        if (A % X == 0 && B % X == 0){
            return -1;
        }
        boolean fa[] = new boolean[maxSize];
        fa[0] = true;
        for (int i = 0; i < A * B * 2; ++i){
            if (i < maxSize && fa[i]){
                if (i + A < maxSize){
                    fa[i + A] = true;
                }
                if (i + B < maxSize){
                    fa[i + B]  = true;
                }
            }
        }
        int ret = 0;
        for (int i = 1; i <= Math.max(A, B); ++i){
            if (check(A, B, X, i, fa)){
                ret ++;
            }
        }
        return ret;
    }
}
