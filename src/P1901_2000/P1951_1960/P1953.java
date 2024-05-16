package P1901_2000.P1951_1960;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 * <p>
 * 你可以按下面两个规则参与项目中的工作：
 * <p>
 * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 * <p>
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。
 */
public class P1953 {
    public long numberOfWeeks(int[] milestones) {
        int max = -1;//记录项目阶段最大值
        long sum = 0;//记录总的阶段数量
        for (int i = 0; i < milestones.length; i++) {
            sum += milestones[i];//累加阶段数量
            max = Math.max(max, milestones[i]);//统计阶段最大值
        }
        sum -= max;//计算出去阶段数量最多的项目其余项目的阶段数量
        if (sum < max - 1) {//其余阶段不能填满最大项目的缝隙
            return 2 * sum + 1;//sum是最多填充的缝隙 与sum相邻的都是max
        } else {//其余阶段能填满max的缝隙
            return sum + max;
        }
    }

    @Test
    public void testConvert() {
        String str = "[5,9,4,4,8,9,9,8,7,3]";
        str = str.replace('[', '{');
        str = str.replace(']', '}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] milestones = {5, 9, 4, 4, 8, 9, 9, 8, 7, 3};
        long res = numberOfWeeks(milestones);
        System.out.println(res);
    }
}
