package srm700;

public class XMarksTheSpot2 {

    static int n;
    static int m;

    public static int typeChar(char c){
        if (c == '.'){
            return 2;
        }else if (c == '0'){
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

    public int countArea(String[] board){
        int area[][] = getArea(board);
        n = board.length;
        m = board[0].length();
        int result = 0;
        for (int T = 0; T < n; ++T){
            if (!checkAreaOk(0, 0, T, m, area)){
                continue;
            }
            //check TopOk
            for (int B = T; B < n; ++ B){
                if (!checkAreaOk(B+1, 0, n, m, area)){
                    continue;
                }
                //check ButtomOK
                for (int L = 0; L < m; ++L){
                    if (!checkAreaOk(0, 0, n, L - 1, area)){
                        continue;
                    }
                    //check Left OK
                    for (int R = L; R < m; ++R){
                        if (!checkAreaOk(0, R + 1, n, m, area)){
                            continue;
                        }
                        //check Right OK
                        //calc
                    }
                }
            }
        }
        return result;
    }

}
