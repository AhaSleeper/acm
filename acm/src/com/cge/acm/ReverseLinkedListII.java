package com.cge.acm;
/**
 * @author zhuojh
 *
 */
public class ReverseLinkedListII {
	
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if(null == head) return head;
		ListNode beforeReverse = null, curr = head;
		for(int i=1; i<m;i++){
			beforeReverse = curr;
			curr = curr.next;
		}
		ListNode first = null, last = null, pre=null, temp = null;
		//reverse
		while(null != curr && m<=n){
			if(last==null) {
				last = curr;
				first = curr;
				pre = curr;
				curr = curr.next;
			} else {
				first = curr;
				temp = curr.next;
				first.next = pre;
				pre = first;
				curr = temp;
			}
			m++;
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
