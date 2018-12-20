package com.cge.acm;

/**
 * 
 * @author zhuojh
 * {@link https://leetcode.com/problems/add-two-numbers/#/description}
 *
 */
public class AddTwoNumbers {

	public static void main(String[] args) {
		
		ListNode l1 = new ListNode(2);
		ListNode l11 = new ListNode(4);
		ListNode l12 = new ListNode(3);
		l1.next = l11;
		l11.next = l12;
		
		ListNode l2 = new ListNode(5);
		ListNode l21 = new ListNode(6);
		ListNode l22 = new ListNode(4);
		l2.next = l21;
		l21.next = l22;
		
		ListNode node = new AddTwoNumbers().addTwoNumbers(l1,l2);
		while(node!=null){
			System.out.print(node.val);
			node = node.next;
			if(node!=null) System.out.print(",");
		}
		
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2){
		ListNode node = new ListNode(0);
		ListNode firstNode = node;
		int sum = 0;
		while(l1 != null || l2 != null){
			sum /=10;
			if(l1 != null){
				sum+=l1.val;
				l1 = l1.next;
			}
			if(l2 != null){
				sum+=l2.val;
				l2 = l2.next;
			}
			node.next = new ListNode(sum%10);
			node = node.next;
			
		}
		if(sum/10==1)
			node.next = new ListNode(1);
		return firstNode.next;
	}
}
