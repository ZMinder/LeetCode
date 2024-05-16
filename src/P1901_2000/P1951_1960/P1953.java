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
        //根据项目的阶段数量升序排序 后续一个任务的所有阶段必须在前面任务的插缝中完成
        // 例如：已经完成的项目 2 1 2 现在3项目可选的位置只有 * 2 * 1 * 2 * 四个位置 * 所在的位置
        //为了工作周数最多要从项目的阶段小的开始 否则可能一上来插缝的缝就不够

        Arrays.sort(milestones);//排序
        int block = 0;//工作的周数（项目阶段的数量）（缝隙的数量-1）
        for (int i = 0; i < milestones.length; i++) {
            if (milestones[i] <= block + 1) {
                block += milestones[i];//当前项目的阶段有足够的缝隙插入 插完后 阶段数量增加}
            } else {
                block += (block + 1);//没有足够的缝隙插入 最多只能把当前缝隙填满 结束工作
                break;
            }
        }
        return block;
    }

    @Test
    public void testConvert() {
        String str = "[1,2,3]";
        str = str.replace('[', '{');
        str = str.replace(']', '}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] milestones = {1, 2, 3};
        long res = numberOfWeeks(milestones);
        System.out.println(res);
    }
}
