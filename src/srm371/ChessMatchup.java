package srm371;

import java.util.Arrays;

/**
 * Created by opq.chen on 2017/1/4.
 */
public class ChessMatchup {
    public int maximumScore(int[] us, int[] them){
        Arrays.sort(us);
        Arrays.sort(them);
        int ans = 0;
        int li = 0, ri = us.length - 1;
        int lt = 0, rt = them.length - 1;
        while (li <= ri){
            if (us[li] > them[lt]){
                ans += 2;
                li++;
                lt++;
            }else if(us[ri] > them[rt]){
                ans += 2;
                ri--;
                rt--;
            }else{
                if (us[li] == them[rt]){
                    ans += 1;
                }
                li++;
                rt--;
            }
        }
        return ans;
    }
}
