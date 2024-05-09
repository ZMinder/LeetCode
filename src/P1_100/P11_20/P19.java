package P1_100.P11_20;

import org.w3c.dom.Node;

import java.util.List;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
public class P19 {
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

    //快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {//快指针先走k步
            fast = fast.next;
        }
        if (fast == null) {//删除第一个节点
            return head.next;
        }
        while (fast.next != null) {//慢指针走到待删除节点的前一个节点
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
