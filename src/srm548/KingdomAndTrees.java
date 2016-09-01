package srm548;

import java.util.Map;

/**
 * Created by opq.chen on 2016/8/23.
 */
public class KingdomAndTrees {
    boolean checkOk(int[] heights, int maxChange){
        int last = 0;
        for (int i = 0; i < heights.length; ++i){
            if (heights[i] > last){
                last = Math.max(last + 1, heights[i] - maxChange);
            }else{
                if (heights[i] + maxChange <= last){
                    return false;
                }
                last += 1;
            }
        }
        return true;
    }

    public int minLevel(int[] heights){
        int l = 0;
        int r = 1000000011;
        int ans = r;
        while (l < r){
            int mid = (l + r) >> 1;
            if (checkOk(heights, mid)){
                ans = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        if (checkOk(heights, l)){
            ans = l;
        }
        return ans;
    }

    public static void main(String args[]){
        int list[] = {2,2};
        System.out.println(new KingdomAndTrees().minLevel(list));
    }
}
