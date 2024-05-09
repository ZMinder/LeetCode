package P1_100.P31_40;

import org.junit.Test;

import java.util.Arrays;

/*
给你一个按照非递减顺序排列的整数数组nums，和一个目标值target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
public class P34 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        ReturnInfo info = binarySearch(nums, 0, nums.length - 1, target);
        int targetIndex = info.targetIndex;
        int leftRet = targetIndex;
        int rightRet = targetIndex;
        int index = targetIndex;
        ReturnInfo leftInfo = info;
        ReturnInfo rightInfo = info;
        while (index != -1) {//寻找左边界
            leftInfo = binarySearch(nums, leftInfo.leftRange, index - 1, target);
            index = leftInfo.targetIndex;
            if (index != -1) {
                leftRet = index;
            }
        }
        while (targetIndex != -1) {//寻找右边界
            rightInfo = binarySearch(nums, targetIndex + 1, rightInfo.rightRange, target);
            targetIndex = rightInfo.targetIndex;
            if (targetIndex != -1) {
                rightRet = targetIndex;
            }
        }
        return new int[]{leftRet, rightRet};
    }

    public class ReturnInfo {
        public int targetIndex;
        public int leftRange;
        public int rightRange;

        public ReturnInfo(int targetIndex, int leftRange, int rightRange) {
            this.targetIndex = targetIndex;
            this.leftRange = leftRange;
            this.rightRange = rightRange;
        }
    }

    public ReturnInfo binarySearch(int[] nums, int left, int right, int target) {
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[mid] == target) {
                return new ReturnInfo(mid, left, right);
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }
        return new ReturnInfo(-1, -1, -1);
    }

    @Test
    public void test() {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] ret = searchRange(nums, 6);
        System.out.println(Arrays.toString(ret));
    }
}
