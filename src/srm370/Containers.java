package srm370;

/**
 * Created by opq.chen on 2016/12/13.
 */
public class Containers {

    public int wastedSpace(int[] containers, int[] packages){
        int ret = 0;
        int j = 0;
        for (int pack : packages){
            while (pack > containers[j] && j < containers.length){
                ret += containers[j];
                j++;
            }
            containers[j] -= pack;
        }
        while (j < containers.length){
            ret += containers[j];
            j++;
        }
        return ret;
    }

}
