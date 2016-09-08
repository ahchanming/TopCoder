package srm331;

/**
 * Created by opq.chen on 2016/9/1.
 */
public class CarolsSinging {
    int ans;

    private int string2int(String str){
        int ret = 0;
        for (int i = 0; i < str.length(); ++i){
            if (str.charAt(i) == 'Y'){
                ret += (1 << i);
            }
        }
        return ret;
    }

    //dep表示深度，now表示现有卡牌的情况,use表示选择了多少人
    //lyrics表示拥有卡牌的情况，
    void dfs(int dep, int now, int use, String lyrics[] ,int n, int m){
        if (use >= ans){
            return; //最优解剪枝
        }
        if (now == (1 << m) - 1){ //到达目标状态，即集齐了m张卡牌
            if (use < ans){
                ans = use;
                return;
            }
        }
        if (dep >= n){
            return;
        }
        if ((now | string2int(lyrics[dep])) != now){ // 可行性剪枝，判断这个人是否有新的卡牌
            dfs(dep + 1, (now | string2int(lyrics[dep])), use + 1, lyrics, n, m); //选择这个人
        }
        dfs(dep + 1, now, use, lyrics, n, m); //不选这个人
    }

    int choose(String[] lyrics){
        ans = lyrics.length;
        int n = lyrics.length;
        int m = lyrics[0].length();
        dfs(0, 0, 0, lyrics, n, m);
        return ans;
    }

    public static void main(String arg[]){
        String list[] = {"YNY","NYN","YNY","NYY","NYY","NYN"};
        System.out.println(new CarolsSinging().choose(list));
    }
}
