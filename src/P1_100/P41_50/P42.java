package P1_100.P41_50;

import org.junit.Test;

//给定n个非负整数表示每个宽度为1的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
public class P42 {
    //dp版本
    public int trap1(int[] height) {
        if (height == null || height.length < 2) {//两根柱子接不到雨水
            return 0;
        }
        int len = height.length;
        int[] leftMax = new int[len];//每个位置左边最大值
        int[] rightMax = new int[len];//每个位置右边最大值
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
            rightMax[len - i - 1] = Math.max(height[len - i], rightMax[len - i]);
        }
        int sum = 0;
        for (int i = 1; i < len; i++) {
            sum += Math.max(Math.min(leftMax[i], rightMax[i]) - height[i], 0);
        }
        return sum;
    }

    //双指针
    public int trap(int[] height) {
        if (height == null || height.length < 2) {//两根柱子接不到雨水
            return 0;
        }
        int len = height.length;
        int leftMax = height[0];//左边扫描到的最大值
        int rightMax = height[len - 1];//右边扫描到的最大值
        int left = 1;
        int right = len - 2;
        int sum = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {//左边最大值小于右边最大值 left可以结算 left右侧最大值下限就是rightMax
                sum += Math.max(leftMax - height[left], 0);
                leftMax = Math.max(leftMax, height[left++]);
            } else {
                //右边最大值小于左边最大值 right可以结算 right左侧最大值下限就是leftMax
                sum += Math.max(rightMax - height[right], 0);
                rightMax = Math.max(height[right--], rightMax);
            }
        }
        return sum;
    }

    @Test
    public void test() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
