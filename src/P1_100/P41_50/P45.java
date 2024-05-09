package P1_100.P41_50;

import org.junit.Test;

/*
给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。
换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
0 <= j <= nums[i]
i + j < n
返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
*/
public class P45 {
    //dp
    public int jump1(int[] nums) {
        if (nums.length == 1) {//长度为1不用跳
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];//跳到i位置的最少跳跃次数
        dp[0] = 0;
        int index = 0;//可以到达的最远距离所在的下标
        for (int i = 1; i < len; i++) {
            while ((index + nums[index]) < i) {//找到可以到达i的最小下标
                index++;
            }
            dp[i] = dp[index];
            for (int j = index + 1; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
            dp[i]++;
        }
        return dp[len - 1];
    }

    //贪心
    public int jump(int[] nums) {
        if (nums.length == 1) {//长度为1不用跳
            return 0;
        }
        int len = nums.length;
        int maxRight = nums[0];//每次跳跃可达到的最远距离
        int end = maxRight;//当前跳跃可达到的最远距离
        int count = 1;
        for (int i = 1; i < len - 1; i++) {
            //在本次跳跃可达到的距离中，哪个位置可以让下一次跳跃距离最远
            maxRight = Math.max(maxRight, i + nums[i]);
            if (i == end) {
                end = maxRight;
                count++;
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }
}
