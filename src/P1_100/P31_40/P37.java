package P1_100.P31_40;

public class P37 {
    public void solveSudoku(char[][] board) {
        final int n = 9;//九宫格的长和宽
        boolean[][] rows = new boolean[n][n];//每一行1-9是否出现
        boolean[][] cols = new boolean[n][n];//每一列1-9是否出现
        boolean[][][] block = new boolean[3][3][n];//三行三列九宫格1-9是否出现
        init(cols, rows, block, board);//初始化 将已经存在的位置标记为true
        dfs(cols, rows, block, board);
    }

    public void init(boolean[][] cols, boolean[][] rows, boolean[][][] block, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';//实际数值
                    rows[i][num - 1] = true;//当前行置为true
                    cols[j][num - 1] = true;//当前列置为true
                    block[i / 3][j / 3][num - 1] = true;//当前位置所在九宫格置为true
                }
            }
        }
    }

    public boolean dfs(boolean[][] cols, boolean[][] rows, boolean[][][] block, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {//当前位置需要填充字符
                    for (int k = 0; k < board.length; k++) {//可以填充的字符
                        if (!cols[j][k] && !rows[i][k] && !block[i / 3][j / 3][k]) {
                            //在当前行 当前列 当前九宫格均为出现 则可以填

                            //标记为已使用
                            rows[i][k] = true;
                            cols[j][k] = true;
                            block[i / 3][j / 3][k] = true;
                            board[i][j] = (char) (k + 1 + '0');//当前位置放k+1

                            if (dfs(cols, rows, block, board)) {//递归填写下一个未填写位置
                                //当前填写可以填完所有格子 直接返回true 因为只有一种答案 无需继续试探
                                return true;
                            }

                            //如果走到这里 说明当前格子填k不合适
                            rows[i][k] = false;
                            cols[j][k] = false;
                            block[i / 3][j / 3][k] = false;
                            board[i][j] = '.';//重新放置为'.'
                        }
                    }
                    //如果当前格子九种数字都无法填写 说明数独填写失败
                    return false;
                }
            }
        }
        //如果所有位置均已填过 说明找到一组有效填法
        return true;
    }
}
