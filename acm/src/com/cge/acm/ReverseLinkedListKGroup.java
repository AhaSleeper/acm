package com.cge.acm;

public class ReverseLinkedListKGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while(curr!=null && count!=k){
            curr = curr.next;
            count++;
        }
        if(count==k){
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
		return reverse(head, temp);
	}
	
	public ListNode reverse(ListNode pre, ListNode next){
		if(next==null) return pre;
		ListNode temp = next.next;
		next.next = pre;
		pre = next;
		return reverse(pre, temp);
	}
}
