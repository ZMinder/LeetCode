package P2201_2300.P2241_2250;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
 * <p>
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 */
public class P2244 {
    public int minimumRounds(int[] tasks) {
        Arrays.sort(tasks);//根据难度降序排序
        int nums = 1;//记录每个难度任务的数量
        int res = 0;
        for (int i = 1; i < tasks.length; i++) {
            if(tasks[i] != tasks[i - 1]){//当前面一个难度任务数量统计结束 计算轮数
                int ans = cal(nums);
                if(ans == -1){//如果轮数为-1 说明完不成
                    return -1;
                }
                res += ans;
                nums = 0;//统计下一难度任务数量
            }
            nums++;//统计相同难度任务的数量
        }
        int ans = cal(nums);//计算最后一个难度需要的轮数
        if(ans == -1){
            return -1;
        }
        return res + ans;
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
        String str = "[2,2,3,3,2,4,4,4,4,4]";
        str = str.replace('[', '{');
        str = str.replace(']', '}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] tasks = {2,2,3,3,2,4,4,4,4,4};
        System.out.println(minimumRounds(tasks));
    }
}
