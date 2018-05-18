package com.cge.acm;

public class ReverseLinkedList {
	 public ListNode reverseList(ListNode head) {
	     if(head==null) return null;
	     if(head.next==null) return head;
	     else {
	    	 ListNode next = head.next;
	    	 head.next = null;
	    	 return reverse(head, next);
	     }
	 }
	 
	 public ListNode reverse(ListNode pre, ListNode next){
		 if(next==null)
			 return pre;
		 else {
			 ListNode temp = next.next;
			 next.next = pre;
			 pre = next;
			 return reverse(pre, temp);
		 }
		 
	 }
	 public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		node1.next = node2;
		ReverseLinkedList reverse = new ReverseLinkedList();
		ListNode result = reverse.reverseList(node1);
		while(result!=null){
			System.out.println(result.val);
			result = result.next;
		}
	}
}


