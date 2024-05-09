package P1_100.P31_40;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 */
public class P36 {
    public boolean isValidSudoku1(char[][] board) {
        HashMap<Integer, ArrayList<Point>> eleMap = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            eleMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    int value = board[i][j] - 48;
                    Point point = new Point(i, j);
                    if (isValid(eleMap, value, point)) {
                        eleMap.get(value).add(point);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[board.length][9];//各行每个数出现的次数
        int[][] cols = new int[board[0].length][9];//各列各个数出现的次数
        int[][][] boxs = new int[board.length / 3][board[0].length / 3][9];//每个小方格每个数出现的次数
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    int value = board[i][j] - 48 - 1;
                    rows[i][value]++;
                    cols[j][value]++;
                    boxs[i / 3][j / 3][value]++;
                    if (rows[i][value] > 1 || cols[j][value] > 1 || boxs[i / 3][j / 3][value] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValid(HashMap<Integer, ArrayList<Point>> eleMap, int value, Point point) {
        ArrayList<Point> list = eleMap.get(value);
        int row = point.x;
        int col = point.y;
        for (Point pre : list) {
            int x = pre.x;
            int y = pre.y;
            //相同行、相同列、位于同一九宫格
            if (row == x || col == y || (x / 3 == row / 3 && y / 3 == col / 3)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValid = isValidSudoku(board);
        System.out.println(isValid);
    }
}
