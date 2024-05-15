package P2501_2600.P2581_2590;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
 * <p>
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 * <p>
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 */
public class P2589 {
    public class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            //以截止时间升序排序 保证了后续任务要么跟当前任务没有交集，要么存在相同的后缀，
            // 当前任务尽可能在较晚的时间段完成，后续的任务可以借前面的任务运行时间运行
            return o1[1] - o2[1];
        }
    }

    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, new MyComparator());
        boolean[] run = new boolean[tasks[tasks.length - 1][1] + 1];//记录每个时间点是否存在可运行的任务
        int res = 0;//最终所需要的运行时间
        for (int i = 0; i < tasks.length; i++) {
            int start = tasks[i][0];//当前任务的起始运行时间
            int end = tasks[i][1];//当前任务的结束运行时间
            int required = tasks[i][2];//当前任务需要的运行时间
            for (int j = start; j <= end; j++) {
                if (run[j]) {//如果当前时间段之前存在任务运行 可以顺带帮我运行1个时间段
                    required--;
                }
            }
            for (int j = end; required > 0; j--) {//剩余运行时间在尽可能晚的时间点完成
                if (!run[j]) {
                    //可能存在当前任务1-6 前面的任务是3-4,5-5,我需要时间5
                    // 因此时间2也是需要的 并且在3-5required不能--
                    run[j] = true;
                    required--;
                    res++;
                }
            }
        }
        return res;
    }

    @Test
    public void testConvert() {
        String str = "[[1,3,2],[2,5,3],[5,6,2]]";
        str = str.replaceAll("\\[", "{");
        str = str.replaceAll("]", "}");
        System.out.println(str);
    }

    @Test
    public void test() {
        int[][] tasks = {{1, 3, 2}, {5, 6, 2}, {2, 5, 3}};
        int time = findMinimumTime(tasks);
        System.out.println("time = " + time);
    }
}
