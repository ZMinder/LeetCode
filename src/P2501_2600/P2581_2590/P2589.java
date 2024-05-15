package P2501_2600.P2581_2590;

import org.junit.Test;

import java.util.*;

/**
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
 * <p>
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 * <p>
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 */
public class P2589 {
    public class Task {
        public int start;//允许运行的起始时间
        public int end;//允许运行的结束时间
        public int duration;//运行时间

        public Task(int start, int end, int duration) {
            this.start = start;
            this.end = end;
            this.duration = duration;
        }
    }

    public int findMinimumTime(int[][] tasks) {
        HashMap<Integer, HashSet<Task>> timeMap = new HashMap<>();//记录每个时间段中可以运行的任务
        for (int i = 0; i < tasks.length; i++) {
            Task task = new Task(tasks[i][0], tasks[i][1], tasks[i][2]);
            for (int j = tasks[i][0]; j <= tasks[i][1]; j++) {
                HashSet<Task> set = timeMap.getOrDefault(j, new HashSet<>());//该时间段可以运行的任务
                set.add(task);//加入当前任务
                timeMap.put(j, set);//更新map
            }
        }
        int res = 0;//记录运行时间
        while (!timeMap.isEmpty()) {
            int time = findMax(timeMap);
            HashSet<Task> task = timeMap.get(time);//当前时间段的任务
            if (task.isEmpty()) {//所有任务均已完成
                break;
            }
            ArrayList<Task> zero = new ArrayList<>();//存储已经完成的任务
            for (Task t : task) {//当前任务运行时间减1
                t.duration--;
                if (t.duration == 0) {//当前任务执行结束
                    zero.add(t);//加入zero
                }
            }
            for (Task t : zero) {
                for (int i = t.start; i <= t.end; i++) {//当前任务起始时间到结束时间移出当前任务
                    if (timeMap.get(i) != null) {//当前时间未被使用过
                        timeMap.get(i).remove(t);
                    }
                }
            }
            timeMap.remove(time);//移出当前时间
            res++;//如果有任务被完成 运行时间加1
        }
        return res;
    }

    public int findMax(HashMap<Integer, HashSet<Task>> timeMap) {
        int time = 0;//记录同时运行任务最多的时间段
        int tasks = -1;//记录任务数
        for (Map.Entry<Integer, HashSet<Task>> entry : timeMap.entrySet()) {
            if (entry.getValue().size() > tasks) {//选取任务数量最多的时间段
                tasks = entry.getValue().size();
                time = entry.getKey();
            }
        }
        return time;
    }

    @Test
    public void testConvert() {
        String str = "[[68,129,2],[58,86,9],[112,142,10],[106,108,1],[48,48,1],[116,143,2],[28,43,5],[1,1,1],[75,83,3],[104,136,10],[11,11,1],[60,63,1],[73,111,8],[57,57,1],[117,119,3],[25,38,2],[20,21,1],[78,80,2],[17,17,1],[28,28,1],[77,117,3],[76,109,4],[61,61,1],[84,92,5],[18,41,4],[47,55,9],[74,132,6],[53,87,3],[102,131,7],[26,26,1],[66,68,3],[59,73,1],[22,30,9],[9,13,2],[31,35,2],[90,91,2],[72,72,1],[62,84,8],[105,106,2],[3,3,1],[32,32,1],[99,103,4],[45,52,4],[108,116,3],[91,123,8],[89,114,4],[94,130,7],[103,104,2],[14,17,4],[63,66,4],[98,112,7],[101,140,9],[58,58,1],[109,145,1],[8,15,8],[4,16,5],[115,141,1],[40,50,4],[118,118,1],[81,120,7]]";
        str = str.replaceAll("\\[", "{");
        str = str.replaceAll("]", "}");
        System.out.println(str);
    }

    @Test
    public void test() {
        int[][] tasks = {{68, 129, 2}, {58, 86, 9}, {112, 142, 10}, {106, 108, 1}, {48, 48, 1}, {116, 143, 2}, {28, 43, 5}, {1, 1, 1}, {75, 83, 3}, {104, 136, 10}, {11, 11, 1}, {60, 63, 1}, {73, 111, 8}, {57, 57, 1}, {117, 119, 3}, {25, 38, 2}, {20, 21, 1}, {78, 80, 2}, {17, 17, 1}, {28, 28, 1}, {77, 117, 3}, {76, 109, 4}, {61, 61, 1}, {84, 92, 5}, {18, 41, 4}, {47, 55, 9}, {74, 132, 6}, {53, 87, 3}, {102, 131, 7}, {26, 26, 1}, {66, 68, 3}, {59, 73, 1}, {22, 30, 9}, {9, 13, 2}, {31, 35, 2}, {90, 91, 2}, {72, 72, 1}, {62, 84, 8}, {105, 106, 2}, {3, 3, 1}, {32, 32, 1}, {99, 103, 4}, {45, 52, 4}, {108, 116, 3}, {91, 123, 8}, {89, 114, 4}, {94, 130, 7}, {103, 104, 2}, {14, 17, 4}, {63, 66, 4}, {98, 112, 7}, {101, 140, 9}, {58, 58, 1}, {109, 145, 1}, {8, 15, 8}, {4, 16, 5}, {115, 141, 1}, {40, 50, 4}, {118, 118, 1}, {81, 120, 7}};
        int time = findMinimumTime(tasks);
        System.out.println("time = " + time);
    }
}
