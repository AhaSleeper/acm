package com.cge.acm;

public class OddEvenList {
	 public ListNode oddEvenList(ListNode head) {
	        if(null==head) return null;
	        ListNode curr = head, pre = head, temp = null,evenListLast = null;
	        ListNode evenList = new ListNode(0);
	        boolean isEven = false;
	        while(curr!=null){
	            if(isEven){
	                temp = curr;
	                pre.next = curr.next;
	                curr = curr.next;
	                temp.next = null;
	                if(null == evenList.next){
	                    evenList.next = temp;
	                    evenListLast = evenList.next;
	                } else {
	                    evenListLast.next = temp;
	                    evenListLast = evenListLast.next;
	                }
	            } else {
	                pre = curr;
	                curr = curr.next;
	            }
	            isEven = !isEven;
	        }
	        pre.next = evenList.next;
	        return head;
	    }
}
