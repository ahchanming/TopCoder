package srm533;

/**
 * Created by chanming on 16/8/3.
 */


/**
 Class:
 CasketOfStar
 Method:
 maxEnergy
 Parameters:
 int[]
 Returns:
 int
 Method signature:
 int maxEnergy(int[] weight)
 (be sure your method is public)
 */


public class CasketOfStar {
    public int maxEnergy(int[] weight){
        int f[][] = new int[51][51];
        int n = weight.length;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                f[i][j] = 0;
            }
        }
        for (int l = 3; l <= n; ++l){
            for (int i = 0; i + l - 1 < n; ++i){
                int j = i + l - 1;
                for (int k = i + 1; k < j; ++k){
                    f[i][j] = Math.max(f[i][j], f[i][k] + f[k][j] + weight[i] * weight[j]);
                }
            }
        }
        return f[0][n - 1];
    }

    public static void main(String[] args){
        int weight[] = {2,2,7,6,90,5,9};
        System.out.println(new CasketOfStar().maxEnergy(weight));
    }
}


