package facebookRound1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/1/15.
 */
public class FightingTheZombies {
    private static boolean inner(Point a, Point b, int R){
        if (b.getX() - a.getX() >= 0 && b.getX() - a.getX() <= R
                && b.getY() - a.getY() >= 0 && b.getY() - a.getY() <= R){
            return true;
        }
        return false;
    }

    private static int work(Scanner scanner){
        int N = scanner.nextInt();
        int R = scanner.nextInt();
        Point points[] = new Point[N + 1];
        for (int i = 0; i < N; ++i){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }
        //离散点,搞出所有的坐标
        List<Point> list = new ArrayList<>();
        for (int i = 0 ; i < N; ++i){
            for (int j = 0; j < N; ++j){
                list.add(new Point(points[i].getX(), points[j].getY()));
            }
        }
        //枚举两个点，并统计
        int ans = 1;
        for (int i = 0; i < N * N; ++i){
            for (int j = i + 1; j < N * N; ++j){
                boolean visit[] = new boolean[N];
                int tmp = 0;
                for (int k = 0; k < N; ++k){
                    if (inner(list.get(i), points[k], R) || inner(list.get(j), points[k], R)){
                        tmp++;
                    }
                }
                ans = ans > tmp ? ans : tmp;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        try {
            FileInputStream fileInputStream = new FileInputStream("Z:\\github\\TopCoder\\src\\facebookRound1\\FightingTheZombies.in.txt");
            Scanner scanner = new Scanner(fileInputStream);
            Writer writer = new FileWriter("Z:\\github\\TopCoder\\src\\facebookRound1\\FightingTheZombies.out.txt");
            int T = scanner.nextInt();
            for (int i = 1; i <= T; ++i){
                writer.write(String.format("Case #%d: ", i));
                writer.write(work(scanner) + "\n");
            }
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
