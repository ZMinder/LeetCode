package P1_100.P21_30;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

//给你一个链表数组，每个链表都已经按升序排列。
//请你将所有链表合并到一个升序链表中，返回合并后的链表。
public class P23 {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;//返回非空的那个，如果都为空，返回空
        }
        ListNode head = new ListNode();
        ListNode temp = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        head.next = list1 == null ? list2 : list1;
        return temp.next;
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        return mergeTwoLists(merge(lists, 0, mid), merge(lists, mid + 1, right));
    }

    //利用归并排序思想
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    //利用小根堆
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> smallHeap = new PriorityQueue<>(new MyComparator());
        for (int i = 0; i < lists.length; i++) {//从小到大排列
            if (lists[i] != null) {
                smallHeap.offer(lists[i]);
            }
        }
        ListNode head = new ListNode();
        ListNode temp = head;
        while (!smallHeap.isEmpty()) {
            ListNode node = smallHeap.poll();
            head.next = node;
            if (node.next != null) {
                smallHeap.offer(node.next);
            }
            head = head.next;
        }
        return temp.next;
    }

    public class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    @Test
    public void testMergeKLists() {
        // Test case with empty lists array
        ListNode[] emptyLists = {};
        ListNode result1 = mergeKLists(emptyLists);
        assertNull(result1); // Expecting null result for empty lists array

        // Test case with one list
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(-5);
        l1.next.next = new ListNode(11);
        ListNode[] singleList = {l1};
        ListNode result2 = mergeKLists1(singleList);

        // Test case with multiple lists
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(6);
        l3.next = new ListNode(10);
        ListNode[] multipleLists = {new ListNode(), l1, new ListNode(), l3};
        ListNode result3 = mergeKLists1(multipleLists);
    }

}
