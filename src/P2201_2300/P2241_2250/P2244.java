package P2201_2300.P2241_2250;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
 * <p>
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 */
public class P2244 {
    public int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {//统计每个数出现的次数
            Integer def = numsMap.getOrDefault(tasks[i], 0);
            numsMap.put(tasks[i], def + 1);
        }
        for (Map.Entry<Integer, Integer> entry : numsMap.entrySet()) {
            System.out.println(entry);
        }
        return 0;
    }

    @Test
    public void testConvertToArray() {
        String str = "[2,2,3,3,2,4,4,4,4,4]";
        str = str.replace('[','{');
        str = str.replace(']','}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] tasks = {2,2,3,3,2,4,4,4,4,4};
        minimumRounds(tasks);
    }
}
