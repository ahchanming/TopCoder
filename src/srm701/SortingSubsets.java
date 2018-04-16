package srm701;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingSubsets {
    public int getMinimalSize(int[] a){
        List<Integer> list = new ArrayList<>();
        for (Integer each : a){
            list.add(each);
        }
        list.sort(Comparator.naturalOrder());
        int result = 0;
        for (int i = 0; i < a.length; ++i){
            if (a[i] != list.get(i)){
                result++;
            }
        }
        return  result;
    }
}
