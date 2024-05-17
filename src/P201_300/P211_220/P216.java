package P201_300.P211_220;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * <p>
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 */
public class P216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(res, n, k, 1, new ArrayList<>(), 0);
        return res;
    }

    //回溯法
    public void backtrace(List<List<Integer>> res, int n, int k,
                          int i, List<Integer> temp, int sum) {
        //剪枝
        if ((temp.size() + 9 - i + 1 < k) || (sum > n) || (temp.size() < k && sum == n)) {
            //如果剩余的数凑不出k个 或者累加和已经超过了n 或者还没选够k个，累加和已经是sum
            return;
        }
        if (temp.size() == k) {
            //选取了k个数
            if (sum == n) {//k个数的和为n
                res.add(new ArrayList<>(temp));
            }
            return;//即便k个数和不是n，也不应该继续选取数字
        }
        for (int j = i; j <= 9; j++) {//选取的范围从i开始 保证不会重复
            temp.add(j);//选取j
            sum += j;//总和累加上j
            backtrace(res, n, k, j + 1, temp, sum);//选取下一个数
            temp.remove(temp.size() - 1);//移除当前添加的数
            sum -= j;//总和减去j
        }
    }

    @Test
    public void test() {
        List<List<Integer>> lists = combinationSum3(4, 1);
        System.out.println(lists);
    }
}
