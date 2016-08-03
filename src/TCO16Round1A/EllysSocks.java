package TCO16Round1A;

/**
 * Created by chanming on 16/6/18.
 */

import java.util.Arrays;

/**
 * Class:
 EllysSocks
 Method:
 getDifference
 Parameters:
 int[], int
 Returns:
 int
 Method signature:
 int getDifference(int[] S, int P)
 (be sure your method is public)
 */


public class EllysSocks {

    private boolean find(int[] S, int P, int mid){
        int n = S.length;
        int i = 0;
        int tot = 0;
        while (i < n - 1){
            if (S[i + 1] - S[i] <= mid){
                tot++;
                i += 2;
            }else{
                i += 1;
            }
        }
        return tot >= P;
    }

    public  int getDifference(int[] S, int P){
        int l = 0;
        int r = 1000000001;
        int ans = 0;
        Arrays.sort(S);
        while (l < r){
            //System.out.println(l + "----" + r);
            int mid = (l + r) / 2 + 1;
            if (find(S, P, mid)){
                ans = mid;
                r = mid - 1;
            }else{
                l = mid;
            }
        }
        if (find(S, P, l)){
            ans = l;
        }
        return ans;
    }

    public static void main(String argv[]){
        int[] S = {42, 37, 84, 36, 41, 42};
        System.out.println(new EllysSocks().getDifference(S, 2));
    }
}
