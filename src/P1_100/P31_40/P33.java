package P1_100.P31_40;

import org.junit.Test;

/*
整数数组 nums 按升序排列，数组中的值互不相同。
在传递给函数之前，nums在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，使数组变为[nums[k],
nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标从0开始计数）。
例如，[0,1,2,4,5,6,7]在下标3处经旋转后可能变为[4,5,6,7,0,1,2]。
给你旋转后的数组nums和一个整数target，如果nums中存在这个目标值target，则返回它的下标，否则返回-1。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class P33 {
    public int search(int[] nums, int target) {
        int index = searchLeftLess(nums, nums[0]);
        int left = -1;
        int right = -1;
        if (index != 0 && target > nums[0]) {
            left = binarySearch(nums, 0, index - 1, target);
        }
        right = binarySearch(nums, index, nums.length - 1, target);
        return left == -1 ? right : left;
    }

    public int searchLeftLess(int[] nums, int target) {
        int left = 1;
        int right = nums.length - 1;
        int less = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                left = mid + 1;
            } else {
                right = mid - 1;
                less = Math.min(mid, less);
            }
        }
        return less == nums.length ? 0 : less;
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = {1};
        System.out.println(search(nums, 1));
    }
}
