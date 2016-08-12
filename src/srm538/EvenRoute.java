package srm538;

/**
 * Created by Administrator on 2016/8/9.
 */
public class EvenRoute {
    public String isItPossible(int[] x, int[] y, int wantedParity){

        for (int i = 0; i < x.length; ++i){
            if ((x[i] + y[i]) % 2 == wantedParity || (-x[i] - y[i]) % 2 == wantedParity){
                return "CAN";
            }
        }
        return "CANNOT";
    }

    public static  void main(String args[]){
        System.out.println(-5 % 2);
    }
}
