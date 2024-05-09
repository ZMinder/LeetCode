package P1_100.P21_30;

import org.junit.Test;

import java.util.Arrays;

/*
给你一个非严格递增排列的数组nums，请你原地删除重复出现的元素，使每个元素只出现一次 ，
返回删除后数组的新长度。元素的相对顺序应该保持一致 。然后返回 nums中唯一元素的个数。
考虑nums的唯一元素的数量为k，你需要做以下事情确保你的题解可以被通过：
更改数组nums，使nums的前k个元素包含唯一元素，并按照它们最初在nums中出现的顺序排列。
nums的其余元素与nums的大小不重要。
返回 k 。
*/
public class P26 {
    public int removeDuplicates(int[] nums) {
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {//当前数字与前一个数字相同
                continue;
            }
            nums[index++] = nums[i];
        }
        return index;
    }

    @Test
    public void test() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
