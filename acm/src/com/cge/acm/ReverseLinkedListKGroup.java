package com.cge.acm;

public class ReverseLinkedListKGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while(curr!=null && count!=k){
            curr = curr.next;
            count++;
        }
        if(count>0){
            curr = reverseKGroup(curr, k);
            while(count-->0){
                ListNode temp = head.next;
                head.next = curr;
                curr = head;
                head = temp;
            }
            head = curr;
        }
        return head;
    }
	public ListNode reverseList(ListNode head){
		if(null == head || head.next==null) return head;
		ListNode temp = head.next;
		head.next = null;
		head = temp;
		return reverse(head, head.next);
	}
	
	public ListNode reverse(ListNode pre, ListNode next){
		if(next==null) return pre;
		ListNode temp = next.next;
		next.next = pre;
		pre = next;
		next = temp;
		return reverse(pre, next);
	}
}
