package P1_100.P11_20;

import org.junit.Test;

import java.util.Arrays;

/*
给你一个长度为 n 的整数数组 nums 和 一个目标值 target。
请你从 nums 中选出三个整数，使它们的和与 target 最接近。
返回这三个数的和。
假定每组输入只存在恰好一个解。
*/
public class P16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);//对数组进行排序
        int minDif = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {//第一个数
            int k = nums.length - 1;//第三个数
            int j = i + 1;//第二个数
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                minDif = Math.abs(target - minDif) > Math.abs(target - sum) ? sum : minDif;
                if (sum > target) {
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    return target;
                }
            }
        }
        return minDif;
    }

    @Test
    public void test() {
        int[] nums = {0, 0, 0};
        System.out.println(threeSumClosest(nums, 1));
    }
}
