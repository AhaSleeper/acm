package com.cge.acm;

public class AddTwoNumbers {

	public static void main(String[] args) {
		
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       ListNode node = null;
		int mod = 0;
		if(l1.val+l2.val<10){
			node = new ListNode(l1.val+l2.val);
		} else {
			node = new ListNode((l1.val+l2.val)%10);
			mod=1;
		}
		ListNode firstNote = node;
		l1 = l1.next;
		l2 = l2.next;
		while(l1!=null && l2!=null){
			if((l1.val + l2.val)<10){
				ListNode childNode = new ListNode(l1.val+l2.val+mod);
				node.next = childNode;
				mod=0;
			} else {
				ListNode childNode = new ListNode((l1.val+l2.val)%10+mod);
				node.next = childNode;
				mod = 1;
			}
			l1 = l1.next;
			l2 = l2.next;
			node = node.next;
		}
		return firstNote;
    }
}
	 class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; next=null;}
	}
