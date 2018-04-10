package srm700;

import java.util.ArrayList;
import java.util.Date;

import java.util.Date;
import java.util.List;

public class XMarksTheSpot {

    static int n;
    static int m;
    static int result;
    static int times = 0;
    static int area[][];
    List<Node> list;

    class Node{
        int x, y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int typeChar(char c){
        if (c == '.'){
            return 2;
        }else if (c == 'O'){
            return 1;
        }
        return 0;
    }

    public static int[][] getArea(String[] board){
        int[][] result = new int[board.length][board[0].length()];
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[0].length(); ++j){
                result[i][j] = typeChar(board[i].charAt(j));
            }
        }
        return result;
    }

    /**
     * 范围内不能有landmark 1
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static boolean checkAreaOk(int x1, int y1, int x2, int y2, int area[][]){
        if (x1 < 0 || y1 < 0){
            return true;
        }
        if (x2 > n || y2 > m){
            return true;
        }
        for (int i = x1; i < x2; ++i){
            for (int j = y1; j < y2; ++j){
                if (area[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static void calc(int[][] area){
        int tmp =0;
        int L = m + 1;
        int R = -1;
        int T = n + 1;
        int B = -1;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                if (area[i][j] == 1){
                    L = j < L ? j : L;
                    R = j > R ? j : R;
                    T = i < T ? i : T;
                    B = i > B ? i : B;
                }
            }
        }
        //System.out.println(L + " " + R + " " + B + " " + T);
        if (L <= R && T <= B){
            result += (R - L + 1) * (B - T + 1);
        }
    }

    public static void dfs(int x, int y, int L, int R, int T, int B, int pow){
        if (x >= n){
            //calc
            //System.out.println(".");
//            for (int i = 0; i < n; ++i){
//                for (int j = 0; j < m; ++j){
//                    System.out.print(area[i][j]);
//                }
//                System.out.println();
//            }
            times++;
            if (L <= R && T <= B){
                result += (R - L + 1) * (B - T + 1) * (int)Math.pow(2, pow);
            }
            return;
        }
        if (y >= m){
            dfs(x + 1, 0, L, R, T, B, pow);
            return;
        }
        if (area[x][y] == 1){
            dfs(x, y + 1, y < L ? y : L, y > R ? y : R, x < T ? x : T, x > B ? x : B, pow);
        }else if (area[x][y] == 2){
            dfs(x, y + 1, L, R, T, B, pow);
        }else{
            if (x < T || x > B || y < L || y > R){
                area[x][y] = 1;
                dfs(x, y + 1, y < L ? y : L, y > R ? y : R, x < T ? x : T, x > B ? x : B, pow);
                area[x][y] = 2;
                dfs(x, y + 1, L, R, T, B, pow);
                area[x][y] = 0;
            }else{
                area[x][y] = 2;
                dfs(x, y + 1, L, R, T, B, pow + 1);
                area[x][y] = 0;
            }
        }
    }

    public int countArea(String[] board){
        area = getArea(board);
        n = board.length;
        m = board[0].length();
//        System.out.println(n + "   " + m);
        result = 0;
        int L = m + 1;
        int R = -1;
        int T = n + 1;
        int B = -1;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                if (area[i][j] == 1){
                    L = j < L ? j : L;
                    R = j > R ? j : R;
                    T = i < T ? i : T;
                    B = i > B ? i : B;
                }
            }
        }
        dfs(0, 0, L, R, T, B, 0);
        return result;
    }


    public static void main(String[] args) {
        long start = new Date().getTime();
        //String[] tmp = {".OOOOOOOOOOOOO.OO.OOOOOOOOOOOOOOOO.OOOOOOOOOOO", "OOOOOOO?OOOOOOOOOOOO.OOOOOO.OOOO.OOOOOOOO.OOOO", "OOOOOOOO.OOOOOOOOOOOO.OOOOOO.OOOOO.OOOOOOOOOO.", "?OOOOOO.OOOOOOO.OOOO.OOOOOO.OOOOO.OOO..OOO.OOO", "OOOOOOOOO..OO.OOOO.O?O.OOO?OOOOOOOOOO.OOO.OO.O", ".OOO.OO.O.OOOOOOOOOOOOOO.OOOOOOOOOOOOOOO.OO?OO", "OOOOO.OOOO.OOOOOO.OO.OOOO.OOOOOOOOO.OOO.OOOOOO", "OOOOOOOOOOOOOOOOOOOOOOOO.OOOOOOOOOOO.O.OOO.OOO", "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO..OOOOO", "O.OOOOOOOO.OOOO.OOOOO.OOOOO.OOO.OOO.OOOO.OO.OO", "O.OOOOOO.OO?OOOOOOOO.OOOO.OOO.OOOOOO?OOOOOOOOO", "OO.OOOOOOOOOOOOO.OOOOOO.OOOOOOOO.OOOOOOOOO.O..", "OOOOOOO.O.OOOOOOO.OOOOO.OOOOOOOOO.OOOOO.OOOOOO", "OOOO.OOOOOOOO.O.OOOOOOO.OOOOOO.OOOOOOOOOOOO.OO", "OOOOOOO..OOOOOOOOOOOOOOOOOOOOO.OOOOOOOOO.?OOOO", "OOOO.O?O.OOOOOOOOOOOO.OOOOOO.O.OO.O.OOOOOOOOOO", "OOOOOOOOO.OO.OOOOOOOOO.OOO.OO.OOOOOOOO.OOOOO.O", "OOO.OOOOOOOO.OO.OOOOOOOOOOO?OOOOOO.OOOOOO.OOOO", "OOOO.OOOOOOOOOOOOOOOOOO.OOOO.O..OOOOOOOO..OO.O", "O.OOOOOOO.OOOOOOOOOOOOOO?OOO.O.OOO.OO..OOOOO.O", "OO.OOOOOOOOOOOOOOOOO.OOOOOOOOO.OOOOO.OO.OOOOOO", "OOOOOOOOOOOOOOOOOOOOOOO.OOOOOOO.OOO.OO.OOO.OOO", "OO.OOOOOOOOOOOOOOOOOOOOOOOOO.OOOOOOOOOOO.OOOOO", "OOOOOOOO.OOOOOO.O.OOOOOOOO.OOOOOOOOOOOOOOOOOOO", "OOOOOO.OOO.O.OOOOOOOO.OOOOOOOOOOOO.OO?OO.OOOOO", "OOOOO..O.OOOOOOOOOOO..OOOOOOOOOOOOO.OO.OO.OOO.", "OOO.OOOOOOO.OOOOOOOO..OOOOOO.OOOOOOOOOOOOOOO.O", "OOOO.O.OOOOOOOOOOO.OOOOO.OOOOOOOOOO?OOOO.OO.OO", "OOO.OOO...OOOOOOOOOOOOOOOOO..OOOOOOOO.OOO.OOOO", "OOO.OOO..OO...OOOOOOOO.OOOOOOOO..OOOOO.OOOOOOO", ".OO.O.OOOOOOO.O.OOOOOOOOOOOOOOOOOOOOOOOOO..OOO", "OOOOOOOOOOOOOOOOOO.OOOOOOOOO.OOOO..OOOOO.OOOOO", "O.OOOOOOOOOOOOOO.OOOOOOOOOOOOOOOOOOO.OOO.OOOOO", "OOOOOOOOOOOOO.O.OOOO?.OO.OO.OOOOOOOO.OOO.OOOOO", "OOOOOOO.O.OOOOOOO?OOOOOOOOOOOOOOOOOO.OOO.O.OOO", "OOO.OOOOOOO.OOOOOOOOOOOOOOOOOOOOOOOOO.O.OOOOOO", ".OOOOOOOOOOOOOO..OOO.OOOOOOOOOOO.OOO.OOOOOOOOO", "OO.OOOO..OOOO?OOOOOO.O.....OOOOO.OOOOOOOOOOOOO", ".OOOOOOO.OO.O.O..O..OOOOOOO.O?OOOOOO.OOOOO.OOO", "OOOOOOOOOOOOOOOOOO.OOOOOOOOOOO.OOOOOOOOO....OO", "OOOOOOOOOOOOOOOOO.O.OOO.OOOOOOO.OOOO...OOOO?OO", "OO..OOOOO.O.OOOOO.OOOOOOOO.OOOOOOOOOOOOOOO.OOO", "O.O.OOO.OOOOOO.OO.OOOOOOOOOOOOO.OOOOOOOOOOOOOO", "OOOOOOOOOOOOOOO.OOOOOO..OOOOOOOOOOO.OOO.OOOOOO", "OOOO.O.OOOOO..OOOO.OO?OOOOOOOOOO.OOO.O.OOOOOO.", "O.OOOOOOO.OOOOOO.O.O.O.OO.OOO.OO.OOO..OOOOOOOO"};
        //String[] tmp = {"..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "...................?..?..?........................", "...................?.???..........................", "....................??O?.?........................", "...................?.???.?........................", "...................?.?..?.........................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", "..................................................", ".................................................."};
        String[] tmp = {"?................................................O", ".?...............................................O", "..?..............................................O", "...?.............................................O", "....?............................................O", ".....?...........................................O", "......?..........................................O", ".......?.........................................O", "........?........................................O", ".........?.......................................O", "..........?......................................O", "...........?.....................................O", "............?....................................O", ".............?...................................O", "..............?..................................O", "...............?.................................O", "................?................................O", ".................?...............................O", "..................?..............................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", ".................................................O", "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"};
        System.out.println(new XMarksTheSpot().countArea(tmp));
        long end = new Date().getTime();
        System.out.println(end - start);
        System.out.println(times);
    }

}
