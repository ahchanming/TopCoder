package srm596;

/**
 * Created by opq.chen on 2016/8/18.
 */
public class IncrementAndDoubling {

    public class Result{
        int totalStep;
        int add;
        int mul;
    }

    private Result getResult(int x){
        if (x == 0){
            Result result = new Result();
            return result;
        }
        int add = 1; //统计+次数，一开始从0变成1
        int mul = 0; //统计*次数
        while (x > 1){
            if (x % 2 == 1){
                add += 1; //如果这个数不是2的倍数，说明他是有一次加的操作
            }
            mul += 1;
            x /= 2;
        }
        Result result = new Result();
        result.add = add;
        result.mul = mul;
        return result;
    }

    public int getMin(int[] desiredArray){
        int totalAdd = 0;
        int maxMul = 0;
        for (int each : desiredArray){
            Result result = getResult(each);
            if (result.mul > maxMul){
                maxMul = result.mul;
            }
            totalAdd += result.add;
        }
        System.out.println(totalAdd + "----" + maxMul);
        return totalAdd + maxMul;
    }

    public static void main(String args[]){
        int[] arr = {16, 16, 16};
        System.out.println(new IncrementAndDoubling().getMin(arr));
    }
}
