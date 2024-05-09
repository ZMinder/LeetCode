package P1301_1400.P1321_1330;

import java.util.ArrayList;
import java.util.Collections;

public class P1329 {
    public int[][] diagonalSort(int[][] mat) {
        int row = 0;//行
        int col = mat[0].length - 1;//列
        while (row < mat.length) {
            sort(mat, row, col);
            if (col == 0) {//对角线起始位置向下移
                row++;
            }
            if (col > 0) {//对角线起始位置向左移
                col--;
            }
        }
        return mat;
    }

    public void sort(int[][] mat, int row, int col) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = row, j = col; i < mat.length && j < mat[0].length; i++, j++) {
            for (int m = i, n = j; m > 0 && n > 0 && mat[m - 1][n - 1] > mat[m][n]; m--, n--) {
                swap(mat, m - 1, n - 1, m, n);
            }
        }
    }

    public void swap(int[][] mat, int i, int j, int m, int n) {
        int temp = mat[i][j];
        mat[i][j] = mat[m][n];
        mat[m][n] = temp;
    }
}
