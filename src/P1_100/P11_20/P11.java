package P1_100.P11_20;

import org.junit.Test;

/*
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
返回容器可以储存的最大水量。
*/
public class P11 {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int area = area(height, left, right);//初始容量为两侧板的容量
        //如果短板移动，容量有可能增大
        //如果长板移动，容量只会变小或不变
        while (left < right) {
            int leftVal = height[left];
            int rightVal = height[right];
            if (leftVal <= rightVal) {
                while (left < right && height[left] <= leftVal) {//比leftVal还小容量不可能增大
                    left++;
                }
            } else {
                while (right > left && height[right] <= rightVal) {
                    right--;
                }
            }
            area = Math.max(area, area(height, left, right));
        }
        return area;
    }

    //计算两板之间的容量
    public int area(int[] height, int i, int j) {
        return (j - i) * Math.min(height[i], height[j]);
    }


    @Test
    public void test() {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
