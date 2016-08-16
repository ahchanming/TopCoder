package srm543;

/**
 * Created by Administrator on 2016/8/14.
 */
public class EllysXors {
    private long getContinueXor(long x){
        long ret = 0;
        if (x % 4 == 1 || x % 4 == 2){
            ret  = 1;
        }//特殊处理第0位
        long tmp = 2; //第1位的值
        long tmq = 4; //第1位的循环节
        while (tmp <= x){
            long y = x % tmq;
            y = y + 1 - tmp;
            if (y > 0 && y % 2 == 1){
                ret |= tmp;
            } //每一个循环节，前一半是0，后一半都是1，看看是否为奇数个
            tmp = tmp << 1; //左移一位
            tmq = tmq << 1; //左移一位
        }
        return ret;
    }

    public long getXor(long L, long R){
        return getContinueXor(R) ^ getContinueXor(L - 1);
    }

    public static void main(String args[]){
        System.out.print(new EllysXors().getXor(1234567L, 89101112L));
    }
}
