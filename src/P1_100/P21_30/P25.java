package P1_100.P21_30;

import org.junit.Test;

/*
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
*/
public class P25 {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        ListNode temp = head;
        while (temp != null) {//统计链表长度
            temp = temp.next;
            len++;
        }
        temp = head;
        ListNode tail = null;
        for (int i = 0; i <= len - k; i += k) {
            ReturnInfo info = reverseKNode(temp, k);
            if (i == 0) {
                head = info.head;
            }
            if (tail != null) {
                tail.next = info.head;
            }
            tail = info.tail;
            temp = info.nextHead;
        }
        tail.next = temp;
        return head;
    }

    public class ReturnInfo {
        public ListNode head;
        public ListNode tail;
        public ListNode nextHead;

        public ReturnInfo(ListNode head, ListNode tail, ListNode nextHead) {
            this.head = head;
            this.tail = tail;
            this.nextHead = nextHead;
        }
    }

    //确保节点个数大于等于K
    public ReturnInfo reverseKNode(ListNode head, int k) {
        ListNode pre = null;
        ListNode next = null;
        ListNode temp = head;
        for (int i = 0; i < k; i++) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return new ReturnInfo(pre, temp, next);
    }

    @Test
    public void test() {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(5);
        head = reverseKGroup(head, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
