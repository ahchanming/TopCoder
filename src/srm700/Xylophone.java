package srm700;

import java.util.HashMap;

public class Xylophone {
    public int countKeys(int[] keys){
        int result = 0;
        HashMap<Integer, Boolean> tmp = new HashMap<>();
        for (int each : keys){
            if (!tmp.containsKey(each % 7)){
                result ++;
                tmp.put(each % 7, true);
            }
        }
        return result;
    }
}
