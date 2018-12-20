package com.cge.acm;

public class InsertionSortLinkedList {
	public ListNode insertionSortList(ListNode head) {
        ListNode sortedList = new ListNode(0);
        ListNode pre = null,curr = null;
        while(null != head){
            if(sortedList.next==null){
            	ListNode temp = head;
            	head = head.next;
                sortedList.next = temp;
                temp.next = null;
                continue;
            }
            pre = sortedList;
            curr = sortedList.next;
            ListNode node = new ListNode(head.val);
            while(curr!=null){
                if(head.val<=curr.val){
                    pre.next = node;
                    node.next =curr;
                    break;
                }
                pre = curr;
                curr = curr.next;
            }
            if(curr==null) pre.next = node;
            head = head.next;
        }
        return sortedList.next;
    }
}
