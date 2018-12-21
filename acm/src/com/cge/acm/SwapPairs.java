package com.cge.acm;

public class SwapPairs {
	 public ListNode swapPairs(ListNode head) {
	        if(null==head || head.next==null) return head;
	        ListNode temp = head;
	        head = head.next;
	        temp.next = swapPairs(head.next);
	        head.next = temp;
	        return head;
	    }
}
