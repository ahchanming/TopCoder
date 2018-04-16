package srm700;

import java.util.HashMap;
import java.util.Map;

/**
 * N个房间，每个房间有S个人
 * 每个人都有一个排名，不存在两个人的排名相同。
 * 现在按照房间顺序把第一名取出来
 * 问总共有多少种不同的队列。
 */
public class XYZCoder {
    static int MOD = 1000000007;

    public Map<Integer, Integer> calc(Map<Integer, Integer> origin, int roomSize, int times){
        int maxNum = times * roomSize + 1;
        int total = 0;
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 2; i <= maxNum; ++i){
            if (origin.containsKey(i - 1)){
                total = (total + origin.get(i - 1)) % MOD;
            }
            if (total > 0){
                result.put(i, total);
            }
        }
        return result;
    }

    public int countWays(int room, int size){

        Map<Integer, Integer> resultMap = new HashMap<>();
        resultMap.put(1, 1);
        for (int i = 1; i < room; ++i){
            resultMap = calc(resultMap, size, i);
        }
        int total = 0;
        for(Integer value : resultMap.values()){
            total = (total + value) % MOD;
        }
        for (int i = 1; i <= room; ++i){
            total =(int)(total * 1L * i % MOD);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new XYZCoder().countWays(100, 100));
    }
}
