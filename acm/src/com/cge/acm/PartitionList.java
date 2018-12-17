package com.cge.acm;

public class PartitionList {
	
	/**
	 * 
	 * @param head
	 * @param val
	 * @return
	 */
	public static ListNode partitionList(ListNode head, int val){
		if(null == head) return head;
		ListNode lessListFirst = null,lessListLast = null, curr = head, pre = null, insertPosition=null;
		while(curr != null){//find the position of val
			if(null == insertPosition && curr.val>=val){//find where to combine the new list
				insertPosition=curr;
			}
			if(curr.val!=val){
				curr = curr.next;
			} else break;
		}
		//find nodes whose val is less than val, delete them and put them in a new list
		curr = curr.next;
		pre=curr;
		while(curr!=null){
			if(curr.val<val){
				if(null == lessListFirst){
					lessListFirst = curr;
					lessListLast = curr;
					pre.next = curr.next;
					curr = curr.next;
					lessListFirst.next = null;
				} else {
					lessListLast.next = curr;
					lessListLast = lessListLast.next;
					lessListLast.next = null;
					pre.next = curr.next;
					curr = curr.next;
				}
			} else {
				pre = curr;
				curr = curr.next;
			}
		}
		if(insertPosition!=null){
			lessListLast.next = insertPosition.next;
			insertPosition.next = lessListLast;
		} else {
			lessListLast.next = insertPosition;
		}
		return head;
	}
	public static void main(String[] args) {
		
	}

}
