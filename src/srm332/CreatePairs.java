package srm332;

import java.util.Arrays;

/**
 * Created by opq.chen on 2016/9/7.
 */
public class CreatePairs {

    int f[] = new int[100];
    int findF(int x, int[] data){
        if (f[x] != Integer.MIN_VALUE){
            return f[x];
        }
        if (x == 0){
            f[x] = data[0];
        }else if (x == 1){
            f[x] = Math.max(data[1] + data[0], data[0] * data[1]);
        }else{
            f[x] = Math.max(findF(x - 1, data) + data[x], findF(x- 2, data) + data[x] * data[x - 1]);
        }
        return f[x];
    }

    int maximalSum3(int[] data){
        for (int i = 0; i < 100; ++i){
            f[i] = Integer.MIN_VALUE;
        }
        return findF(data.length - 1, data);
    }

    int maximalSum2(int[] data){
        int f[] = new int[100];
        for (int i = 0; i < 100; ++i){
            f[i] = -100000000;
        }
        f[0] = 0;
        for (int i = 0; i < data.length; ++i){
            f[i + 1] = Math.max(f[i + 1], f[i] + data[i]);
            if (i + 2 <= data.length){
                f[i + 2] = Math.max(f[i + 2], f[i] + data[i] * data[i + 1]);
            }
        }
        for (int i = 0; i <= data.length; ++i){
            System.out.println(i + " " + f[i]);
        }
        return f[data.length];
    }

    public int maximalSum(int[] data){
        Arrays.sort(data);
        int r = data.length - 1;
        int l = 0;
        int ans = 0;
        while (r > 0){
            if (data[r - 1] > 1){
                ans += data[r] * data[r - 1];
                r -= 2;
            }else{
                break;
            }
        }
        while (l < r){
            if (data[l + 1] <= 0){
                ans += data[l] * data[l + 1];
                l += 2;
            }else{
                break;
            }
        }
        while (l <= r){
            ans += data[l];
            l++;
        }
        return ans;
    }


        public static void main(String args[]){
        int a[] = {1, 2};
        System.out.println(new CreatePairs().maximalSum(a));
    }
}
