package srm697;

import java.util.*;

/**
 * Created by opq.chen on 2016/8/18.
 */
public class DivisibleSetDiv1 {
    public String isPossible(int[] b){
        List<Integer> bList = new ArrayList<>();
        for (int each : b){
            bList.add(each + 1);
        }
        Collections.sort(bList);
        int total = 1000000009;
        Set<Integer> mySet = new HashSet<Integer>();
        int last = 0;
        long ctotal = 0;
        for (int i = bList.size() - 1; i >= 0; --i){
            int each = bList.get(i);
            int tmp = total / each + 1;
            if (tmp <= last){
                tmp = last + 1;
            }
            last = tmp;
            ctotal += tmp;
            System.out.println(ctotal + " " +tmp);
        }
        if (ctotal > total){
            return "Impossible";
        }else{
            return "Possible";
        }
    }

    public static void main(String args[]){
        int b[] = {5,3,5,4,6,1,3,7,9,6,2,5,4,1,1,9,6,10,10,6,10,7,7,8};
        System.out.println(new DivisibleSetDiv1().isPossible(b));
    }
}
