package P1_100.P31_40;

import org.junit.Test;

import java.util.Arrays;

/*
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列
更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
那么数组的下一个排列就是在这个有序容器中排在它后面的那个排列。
如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。
必须 原地 修改，只允许使用额外常数空间。
 */
public class P31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int len = nums.length;
        int max = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            max = Math.max(max, nums[i]);
            if (nums[i] == max) {
                int j = i;
                while (j + 1 < len && nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    j++;
                }
            } else {
                int index = binarySearch(nums, i + 1, nums[i]);
                swap(nums, i, index);
                break;
            }
        }
    }

    public int binarySearch(int[] nums, int begin, int target) {
        int left = begin;
        int right = nums.length - 1;
        int more = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
                more = Math.min(more, mid);
            }
        }
        return more;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 1, 5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
