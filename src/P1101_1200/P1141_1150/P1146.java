package P1101_1200.P1141_1150;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class P1146 {

    public class SnapshotArray {

        int snapId;//调用snap的次数
        HashMap<Integer, ArrayList<int[]>> history;//每个下标的历史记录

        //初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
        public SnapshotArray(int length) {
            history = new HashMap<>();
            for (int i = 0; i < length; i++) {
                history.put(i, new ArrayList<>());
            }
        }

        //会将指定索引 index 处的元素设置为 val。
        public void set(int index, int val) {
            history.get(index).add(new int[]{snapId, val});
        }

        // 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
        public int snap() {
            return snapId++;
        }

        //根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
        public int get(int index, int snap_id) {
            int res = binarySearch(history.get(index), snap_id);
            return res == -1 ? 0 : history.get(index).get(res)[1];
        }

        public int binarySearch(ArrayList<int[]> list, int snap_id) {
            int left = 0;
            int right = list.size() - 1;
            int less = -1;
            int mid;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (list.get(mid)[0] <= snap_id) {
                    left = mid + 1;
                    less = Math.max(less, mid);
                } else {
                    right = mid - 1;
                }
            }
            return less;
        }
    }

    @Test
    public void test() {
        SnapshotArray snapshotArr = new SnapshotArray(4); // 初始化一个长度为 3 的快照数组
        snapshotArr.set(0, 5);  // 令 array[0] = 5
        snapshotArr.snap();  // 获取快照，返回 snap_id = 0
        snapshotArr.set(0, 6);
        snapshotArr.get(0, 0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
    }
}
