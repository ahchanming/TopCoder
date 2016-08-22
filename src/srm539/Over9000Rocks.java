package srm539;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Administrator on 2016/8/9.
 */
public class Over9000Rocks {
    private class Segment implements Comparable<Segment>{
        private int begin, end;

        public Segment(int begin, int end){
            this.begin = begin;
            this.end = end;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getBegin() {
            return begin;
        }

        public void setBegin(int begin) {
            this.begin = begin;
        }

        @Override
        public int compareTo(Segment o) {
            return this.begin - o.begin;
        }
    }

    private int n;
    private List<Segment> sList = new ArrayList<Segment>();

    private void dfs(int dep, int[] lowerBound, int[] upperBound, int low, int upp){
        if (dep == n){
            Segment segment = new Segment(low, upp + low);
            sList.add(segment);
            return;
        }
        dfs(dep + 1, lowerBound, upperBound, low, upp);
        dfs(dep + 1, lowerBound, upperBound, low + lowerBound[dep], upp + upperBound[dep] - lowerBound[dep]);
    }

    public int getAns(List<Segment> list){
        int ret = 0;
        int r = 9000;
        for (Segment each : list){
            if (each.begin > r){
                r = each.begin - 1;
            }
            if (each.end > r){
                ret += (each.end - r);
                r = each.end;
            }
        }
        return  ret;
    }

    public int countPossibilities(int[] lowerBound, int[] upperBound){
        n = lowerBound.length;
        sList.clear();
        dfs(0, lowerBound, upperBound, 0, 0);
        Collections.sort(sList);
        return getAns(sList);
    }
}
