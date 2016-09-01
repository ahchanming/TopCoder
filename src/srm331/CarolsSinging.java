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

    void dfs(int dep, int now, int use, String lyrics[] ,int n, int m){
        if (use >= ans){
            return;
        }
        if (now == (1 << m) - 1){
            if (use < ans){
                ans = use;
                return;
            }
        }
        if (dep >= n){
            return;
        }
        if ((now | string2int(lyrics[dep])) != now){
            dfs(dep + 1, (now | string2int(lyrics[dep])), use + 1, lyrics, n, m);
        }
        dfs(dep + 1, now, use, lyrics, n, m);
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
