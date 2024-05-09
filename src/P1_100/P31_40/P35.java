package P1_100.P31_40;

import org.junit.Test;

/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
请必须使用时间复杂度为 O(log n) 的算法。
 */
public class P35 {
    public int searchInsert(int[] nums, int target) {
        int ret = binarySearch(nums, target);
        return ret;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int less = -1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                less = Math.max(less, mid);
                left = mid + 1;
            }
        }
        return less + 1;
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 5, 6};
        int target = 0;
        System.out.println(searchInsert(nums, target));
    }
}
