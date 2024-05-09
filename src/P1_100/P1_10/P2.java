package P1_100.P1_10;

import org.junit.Test;

/*
给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的并且每个节点只能存储一位数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
*/
public class P2 {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        int rest = 0;
        while(l1 != null && l2 != null){
            rest = l1.val + l2.val + rest;
            ListNode next = new ListNode(rest % 10);
            rest /= 10;
            temp.next = next;
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            rest = l1.val + rest;
            ListNode next = new ListNode(rest % 10);
            rest /= 10;
            temp.next = next;
            temp = temp.next;
            l1 = l1.next;
        }
        while(l2 != null){
            rest = l2.val + rest;
            ListNode next = new ListNode(rest % 10);
            rest /= 10;
            temp.next = next;
            temp = temp.next;
            l2 = l2.next;
        }
        if(rest != 0){
            ListNode next = new ListNode(rest % 10);
            temp.next = next;
        }
        return head.next;
    }
}
