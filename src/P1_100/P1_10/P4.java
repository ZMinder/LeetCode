package P1_100.P1_10;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
给定两个大小分别为m和n的正序（从小到大）数组nums1和nums2。请你找出并返回这两个正序数组的中位数。
算法的时间复杂度应该为 O(log (m+n)) 。
*/
public class P4 {
    public class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (int) (o2 - o1);
        }
    }

    //利用堆查找中位数 O((m+n)*log(m+n))
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
            return 0;
        }
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        PriorityQueue<Integer> largeHeap = new PriorityQueue<>(new MyComparator());
        int[] more = nums1.length > nums2.length ? nums1 : nums2;
        int[] less = more == nums1 ? nums2 : nums1;
        largeHeap.offer(more[0]);
        readInfo(more, largeHeap, smallHeap, 1);
        readInfo(less, largeHeap, smallHeap, 0);
        if (largeHeap.size() > smallHeap.size()) {
            return largeHeap.peek();
        } else if (largeHeap.size() < smallHeap.size()) {
            return smallHeap.peek();
        } else {
            return (largeHeap.peek() + smallHeap.peek()) / 2.0;
        }
    }

    public void readInfo(int[] nums, PriorityQueue<Integer> largeHeap, PriorityQueue<Integer> smallHeap, int start) {
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > largeHeap.peek()) {
                smallHeap.offer(nums[i]);
            } else {
                largeHeap.offer(nums[i]);
            }
            if (largeHeap.size() - smallHeap.size() > 1) {
                smallHeap.offer(largeHeap.poll());
            } else if (smallHeap.size() - largeHeap.size() > 1) {
                largeHeap.offer(smallHeap.poll());
            }
        }
    }

    //O(m+n)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
            return 0;
        }
        int len = nums1.length + nums2.length;
        int[] nums = merge(nums1, nums2);
        if (len % 2 == 1) {
            return nums[len / 2];
        } else {
            return (nums[len / 2] + nums[len / 2 - 1]) / 2.0;
        }
    }

    public int[] merge(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] nums = new int[len];
        int p1 = 0;
        int p2 = 0;
        int i = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            nums[i++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        while (p1 < nums1.length) {
            nums[i++] = nums1[p1++];
        }
        while (p2 < nums2.length) {
            nums[i++] = nums2[p2++];
        }
        return nums;
    }

    //缺O(log(min(m,n)))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
            return 0;
        }
        int[] more = nums1.length > nums2.length ? nums1 : nums2;
        int[] less = more == nums1 ? nums2 : nums1;
        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            if (less.length == 0) {
                return (more[more.length / 2] + more[more.length / 2 - 1]) / 2.0;
            }
            return (findKthNum(nums1, nums2, len / 2) +
                    findKthNum(nums1, nums2, len / 2 + 1)) / 2.0;
        } else {
            if (less.length == 0) {
                return more[more.length / 2];
            }
            return findKthNum(nums1, nums2, len / 2 + 1);
        }
    }

    public int findKthNum(int[] nums1, int[] nums2, int k) {
        int[] more = nums1.length > nums2.length ? nums1 : nums2;
        int[] less = more == nums1 ? nums2 : nums1;
        if (k <= less.length) {
            return findUpMedian(less, 0, k - 1, more, 0, k - 1);
        }
        if (k > more.length) {
            if (less[k - more.length - 1] > more[more.length - 1]) {
                return less[k - more.length - 1];
            }
            if (more[k - less.length - 1] > less[less.length - 1]) {
                return more[k - less.length - 1];
            }
            return findUpMedian(less, k - more.length, less.length - 1,
                    more, k - less.length, more.length - 1);
        }
        if (more[k - less.length - 1] > less[less.length - 1]) {
            return more[k - less.length - 1];
        }
        return findUpMedian(less, 0, less.length - 1,
                more, k - less.length, k - 1);
    }

    //两个数组长度相等,寻找上中位数
    public int findUpMedian(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {
        if ((s1 - e1) == 0) {
            return Math.min(nums1[s1], nums2[s2]);
        }
        int mid1 = (s1 + e1) / 2;
        int mid2 = (s2 + e2) / 2;
        int offset = ((s1 - e1 + 1) & 1) ^ 1;//offset == 0 奇数
        if (nums1[mid1] == nums2[mid2]) {
            return nums1[mid1];
        } else if (nums1[mid1] > nums2[mid2]) {
            if (offset == 0 && nums2[mid2] > nums1[mid1 - 1]) {//数组长度是奇数并且nums2[mid2]大于nums1[mid1-1]
                return nums2[mid2];
            }
            return findUpMedian(nums1, s1, mid1 + offset - 1, nums2, mid2 + 1, e2);
        } else {
            if (offset == 0 && nums1[mid1] > nums2[mid2 - 1]) {//数组长度是奇数并且nums1[mid1]大于nums2[mid2-1]
                return nums1[mid1];
            }
            return findUpMedian(nums1, mid1 + 1, e1, nums2, s2, mid2 + offset - 1);
        }
    }

    @Test
    public void test() {
        int[] nums1 = {1, 4, 5};
        int[] nums2 = {2, 3, 6, 7};
//        System.out.println(findKthNum(nums1, nums2, 3));
        System.out.println(findKthNum(nums1, nums2, 4));
//        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
