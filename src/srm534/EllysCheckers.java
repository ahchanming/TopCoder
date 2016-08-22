//package srm534;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by chanming on 16/8/3.
// Class:
//    EllysCheckers
// Method:
//    getWinner
// Parameters:
//    String
// Returns:
//    String
// Method signature:
//    String getWinner(String board)
// */
//
//
//
//public class EllysCheckers {
//    private static Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
////
////    private int hash(String board){
////        int ret  = 0;
////        for (int i = 0; i < board.length(); ++i){
////            if (board.charAt(i) == '.'){
////                ret = ret * 2;
////            }else{
////                ret = ret * 2 + 1;
////            }
////        }
////        return ret;
////    }
////
////    private boolean isChess(int board, int index){
////        return (board & (1 << index)) > 0;
////    }
////
////    private boolean canWin(int board, int length){
////        if (map.containsKey(board)){
////            return map.get(board);
////        }else{
////            //�ܹ�����ذ�̬��һ���Ǳ�ʤ̬, ���ﲻ�˱�ʤ̬��, ���Ǳذ�̬.
////            boolean reachLoseGame = false;
////            //�����ƶ�һλ
////            for (int i = length - 1; i > 0; --i){
////                if (isChess(board, i)){
////                    //�ұ�û���ӻ����ұ������һλ
////                    if (i - 1 == 0 || !isChess(board, i - 1)){
////                        if (!canWin(board ^ (1 << i) | (1 <<(i - 1)), length)){
////                            reachLoseGame = true;
////                        }
////                    }
////
////                    //�ұ���������,���ҵ�3λΪ�ո� ���� �����һλ
////                    if (i > 2 && isChess(board, i - 1) && isChess(board, i - 2)){
////                        if (i - 3 == 0 || !isChess(board, i - 3)){
////                            if (!canWin(board ^ (1 << i) | (1 << (i - 3)), length)){
////                                reachLoseGame = true;
////                            }
////                        }
////                    }
////                }
////            }
////            map.put(board, reachLoseGame);
////            return reachLoseGame;
////        }
////    }
////
////    public String getWinner(String board){
////        map.clear();
////        map.put(1, false);
////        if (canWin(hash(board), board.length())){
////            return "YES";
////        }else{
////            return "NO";
////        }
////    }
//
//    public static void main(String args[]){
//        String test1 = ".o...ooo..oo..";
//        System.out.print(        Long.MAX_VALUE);
//    }
//}
