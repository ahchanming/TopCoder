package srm701;

public class SquareFreeString {
    public static boolean check(String s){
        int l = s.length() / 2;
        for (int step = 1; step <= l; ++step){
            for (int i = 0; i < s.length() - step * 2; ++i){
                //System.out.println(i, step, i + step)
                String s1 = s.substring(i, i + step);
                String s2 = s.substring(i + step, i + 2 * step);
                if (s1.equals(s2)){
                    return true;
                }
            }
        }
        return false;
    }

    public String isSquareFree(String s){
        if (check(s)){
            return "not square-free";
        }else{
            return "square-free";
        }
    }

    public static void main(String[] args) {
        System.out.println(new SquareFreeString().isSquareFree("aydyamrbnauhftmphyrooyq"));
    }
}
