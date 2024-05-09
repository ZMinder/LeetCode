package P2301_2400.P2301_2310;

import org.junit.Test;

public class P2385 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    int startHeight = -1;
    int time = -1;
    int pre = -1;//start所在子树感染所需要的时间
    int preVal = -1;

    public int getDepth(TreeNode root, int start) {
        if (root == null) {//如果根节点为null 深度为0
            return 0;
        }
        int thisHeight = getDepth(root.left, start);
        int otherHeight = 0;
        if (startHeight == -1) {
            int temp = getDepth(root.right, start);//实际的thisDepth
            otherHeight = thisHeight;
            thisHeight = temp;
        } else {
            otherHeight = getDepth(root.right, start);
        }
        int depth = Math.max(thisHeight, otherHeight) + 1;
        //处理感染点的深度
        if (root.val == start) {
            startHeight = 1;
            pre = depth - 1;
            time = pre;
            preVal = start;
        }
        if (((root.left != null && root.left.val == preVal)
                || (root.right != null && root.right.val == preVal))
                && startHeight != -1) {//只有包含start的子树进行计算
            if (startHeight <= otherHeight) {//感染点的高度小于等于另一子树的高度
                pre = Math.max(pre,
                        2 * startHeight + otherHeight - startHeight);
            } else {
                pre = Math.max(pre, startHeight + otherHeight);
            }
            time = Math.max(pre, time);
            startHeight++;
            preVal = root.val;
        }
        return depth;
    }

    public int amountOfTime(TreeNode root, int start) {
        int depth = getDepth(root, start);
        if (startHeight == 0) {//start是根节点
            return depth - 1;
        } else {
            return time;
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(16);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(12);
        root.right.left.right = new TreeNode(15);
        root.right.left.right.right = new TreeNode(1);
        root.right.right.right = new TreeNode(19);
        root.right.right.right.left = new TreeNode(2);


        System.out.println(amountOfTime(root, 1));
    }
}
