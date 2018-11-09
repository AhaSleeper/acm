package com.cge.acm;

public class RotateList {
	public static ListNode rotate(ListNode head, int k){
		if(null == head) return head;
		ListNode sentinelNode = new ListNode(0),pre;
		sentinelNode.next = head;
		int i=0;
		int n=0;
		ListNode curr = head;
		while(curr!=null){
			n++;
			curr=curr.next;
		}
		k = k%n;
		while(i<k){
			curr = sentinelNode.next;pre = sentinelNode.next;
			while(curr!=null && curr.next!=null){
				pre = curr;
				curr = curr.next;
			}
			curr.next = sentinelNode.next;
			sentinelNode.next = curr;
			pre.next = null;
			i++;
		}
		return sentinelNode.next;
	}
	//[1,2,3,4,5]
	//2
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
//		ListNode node4 = new ListNode(4);
//		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
//		node3.next = node4;
//		node4.next = node5;
		ListNode node = rotate(node1, 2000000000);
		while(node!=null){
			System.out.print(node.val+",");
			node = node.next;
		}
	}
}
