package com.cge.acm;

/**
 * @author zhuojh
 *
 */
public class ReverseLinkedListII {
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(null == head) return head;
		ListNode beforeReverse = null, curr = head,first = null, last = null, pre=null;
		int i=1;
		//reverse 
		while(null != curr && i<n){
			if(i==m){
				beforeReverse=curr;
			}
			if(last==null && i-1==m) {
				last = curr;
				first = curr;
			} else if(i>=m){
				first = curr;
				first.next = pre;
			}
			pre = curr;
			curr = curr.next;
			i++;
		}
		//combine
		last.next = curr;
		if(null == beforeReverse){
			return first;
		} else {
			beforeReverse.next = first;
		}
		return head;
    }
}
