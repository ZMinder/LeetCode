package P1_100.P71_80;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class P78 {
    List<List<Integer>> res = new ArrayList<>();//存储所有子集的集合
    List<Integer> temp = new ArrayList<>();//存储当前子集的集合

    public List<List<Integer>> subsets(int[] nums) {
        backtrace(nums, 0);
        return res;
    }

    /**
     * 子集问题，就是选取元素问题，数组中的每个元素都可以选取或者不选取
     *
     * @param nums  给定的数据数组
     * @param index 当前处理的元素下标
     */
    public void backtrace(int[] nums, int index) {
        if (index == nums.length) {//所有元素均已处理完毕
            res.add(new ArrayList<>(temp));
            return;
        }
        backtrace(nums, index + 1);//不选取当前元素
        //选取当前元素
        temp.add(nums[index]);
        backtrace(nums, index + 1);
        temp.remove(temp.size() - 1);//回到上一层就不再选取当前元素
    }

    @Test
    public void test() {
        int[] nums = {0};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }
}
