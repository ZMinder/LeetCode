package P1_100.P11_20;

import org.junit.Test;

import java.util.*;

/*
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。
*/
public class P15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);//保证a b c 从小到大
        List<List<Integer>> ans = new ArrayList<>();
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {//第一个数不能出现重复值
                continue;
            }
            int third = nums.length - 1;//第三个数初始位置
            int target = -nums[first];
            //第二个数下标不能与第一个数下标重复
            for (int second = first + 1; second < nums.length; second++) {
                //第二个数不能出现重复值
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                //两数相加大于目标值
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                if (second == third) {//为了不重复，二者不能相遇，一旦相遇，second下一个更大，需要third更小，必定重复
                    break;
                }
                if (nums[second] + nums[third] == target) {//满足条件
                    ans.add(Arrays.asList(nums[first], nums[second], nums[third]));
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }
}
