package P1_100.P21_30;

/*
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
*/
public class P24 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {//无节点或只有一个节点
            return head;
        }
        ListNode temp = head;
        head = head.next;//最终的头结点
        ListNode pre = null;
        while (temp != null && temp.next != null) {
            ListNode next = temp.next;//获取2号节点
            if (pre != null) {//前驱指向2号
                pre.next = next;
            }
            pre = temp;//前驱更新
            temp.next = next.next;//1号指向3号
            next.next = temp;//2号指向1号
            temp = temp.next;//1号跳到3号
        }
        return head;
    }
}
