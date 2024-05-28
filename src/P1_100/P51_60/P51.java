package P1_100.P51_60;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class P51 {
    List<List<String>> res = new ArrayList<>();//存储不同解决方案的集合

    public List<List<String>> solveNQueens(int n) {
        int[] checkBoard = new int[n];//checkBoard[i]表示第i+1行的皇后放置的位置
        backtrace(checkBoard, 0);
        return res;
    }

    public void backtrace(int[] checkBoard, int row) {
        if (row == checkBoard.length) {
            //所有行均已放置皇后
            summary(checkBoard);
            return;
        }
        for (int i = 0; i < checkBoard.length; i++) {
            checkBoard[row] = i;
            if (check(checkBoard, row)) {//符合规则
                backtrace(checkBoard, row + 1);
            }
        }
    }

    public void summary(int[] checkBoard) {
        List<String> temp = new LinkedList<>();//存储当前棋盘格局
        for (int i = 0; i < checkBoard.length; i++) {
            String str = "";
            int col = checkBoard[i];//当前行皇后所处的列
            for (int j = 0; j < checkBoard.length; j++) {
                str += j == col ? "Q" : ".";
            }
            temp.add(str);
        }
        res.add(temp);
    }

    public boolean check(int[] checkBoard, int row) {//检查row行放置的皇后是否符合规则
        for (int i = 0; i < row; i++) {
            if (checkBoard[i] == checkBoard[row] ||
                    Math.abs(i - row) == Math.abs(checkBoard[i] - checkBoard[row])) {
                //处于同一列或者处于对角线
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        List<List<String>> lists = solveNQueens(9);
        System.out.println(lists);
    }
}
