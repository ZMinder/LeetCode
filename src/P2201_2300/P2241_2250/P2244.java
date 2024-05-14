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
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : numsMap.entrySet()) {
            int num = entry.getValue();//当前数出现的次数
            int ans = cal(num);
            if (ans == -1) {//当前难度任务无法完成返回-1
                return ans;
            } else {//加上完成当前难度任务的轮数
                res += ans;
            }
        }
        return res;
    }

    public int cal(int num) {
        int round = num / 3;//优先每轮完成三个任务
        num %= 3;//剩余的任务每轮完成两个 num此时取值范围{0,1,2}
        if (num == 0 || num == 2) {
            return round + num / 2;
        } else if (round > 0) {//此时num=1 可以减少一轮完成三个任务
            // round-1,num+3,round+2->round+1
            return round + 1;
        } else {
            return -1;
        }
    }

    @Test
    public void testConvertToArray() {
        String str = "[2,3,3]";
        str = str.replace('[', '{');
        str = str.replace(']', '}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] tasks = {2, 3, 3};
        System.out.println(minimumRounds(tasks));
    }
}
