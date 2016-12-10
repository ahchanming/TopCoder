package SRM369;

/**
 * Created by opq.chen on 2016/12/10.
 */
public class PirateTreasure {
    public double getDistance(int[] steps, String[] directions){
        int ix, iy, dx, dy;
        ix = iy = dx = dy = 0;
        for (int i = 0; i < steps.length; ++i){
            if ("NORTH".equals(directions[i])){
                iy += steps[i];
            }else if ("SOUTH".equals(directions[i])){
                iy -= steps[i];
            }else if ("EAST".equals(directions[i])){
                ix += steps[i];
            }else if ("WEST".equals(directions[i])){
                ix -= steps[i];
            }else if ("NORTHEAST".equals(directions[i])){
                dx += steps[i];
                dy += steps[i];
            }else if ("NORTHWEST".equals(directions[i])){
                dx -= steps[i];
                dy += steps[i];
            }else if ("SOUTHEAST".equals(directions[i])){
                dx += steps[i];
                dy -= steps[i];
            }else{
                dx -= steps[i];
                dy -= steps[i];
            }
        }
        double ans = Math.sqrt(Math.pow((ix + dx / 2.0 * Math.sqrt(2)), 2) + Math.pow((iy + dy / 2.0 * Math.sqrt(2)), 2));
        return ans;
    }
}
