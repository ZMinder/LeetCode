package P1_100.P31_40;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < candidates.length; i++) {//记录candidates中每个数出现的次数
            Integer init = countMap.getOrDefault(candidates[i], 0);
            init++;
            countMap.put(candidates[i], init);
        }
        int[] arr = new int[countMap.size()];
        int[] size = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            arr[index] = entry.getKey();
            size[index++] = entry.getValue();
        }
        backtrace(res, new ArrayList<>(), arr, size, target, 0);
        return res;
    }

    /**
     * 利用candidates数组i及其之后数，凑出target，每个数只能用一次
     *
     * @param res    存储所有组合
     * @param temp   存储当前的已经选取的数字
     * @param arr    记录提供所有提供的数据 不重复
     * @param size   记录每个数据出现的次数
     * @param target 要凑出的目标
     * @param i      candidates数组下标
     */
    public void backtrace(List<List<Integer>> res, List<Integer> temp, int[] arr,
                          int[] size, int target, int i) {
        if (target == 0) {//当凑够target 不再继续选取 因为后续不会凑出0
            res.add(new ArrayList<>(temp));
            return;
        }
        if (i == arr.length) {//防止下标越界
            return;
        }
        for (int j = 0; j <= size[i] && arr[i] * j <= target; j++) {//每个数可以选取多次
            for (int k = 0; k < j; k++) {//选取j个数
                temp.add(arr[i]);
            }
            backtrace(res, temp, arr, size, target - j * arr[i], i + 1);//选取下一个数字
            for (int k = 0; k < j; k++) {//移除
                temp.remove(temp.size() - 1);
            }
        }
    }

    @Test
    public void testConvert() {
        String str = "[2,5,2,1,2]";
        str = str.replace('[', '{');
        str = str.replace(']', '}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> lists = combinationSum2(candidates, target);
        System.out.println(lists);
    }
}
