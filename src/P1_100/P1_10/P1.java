package P1_100.P1_10;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/*
给定一个整数数组nums和一个整数目标值target请你在该数组中找出和为目标值target的那两个整数，
并返回它们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
你可以按任意顺序返回答案。
*/
public class P1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> posMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (posMap.containsKey(target - nums[i])) {
                return new int[]{i, posMap.get(target - nums[i])};
            } else {
                posMap.put(nums[i], i);
            }
        }
        return null;
    }
}
