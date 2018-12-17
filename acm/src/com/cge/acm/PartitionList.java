package com.cge.acm;

public class PartitionList {
	public ListNode partition(ListNode head, int x) {
        if(null == head) return head;  
        ListNode xNode = head, insertPointer=null, insertPre=null, pre=null;
        //find the node whose val greater or equals x
        while(xNode!=null){
        	if(xNode.val>=x && insertPointer==null){
        		insertPre = pre;
        		insertPointer = xNode; 
        		break;
        	}
        	pre = xNode;
        	xNode = xNode.next;
        }
        if(xNode==null) return head;
        ListNode first=null, last=null;
        pre = xNode;
        xNode = xNode.next;
        while(xNode!=null){
        	if(xNode.val<x){
        		if(first==null){
        			first = xNode;
        			last = xNode;
        		} else {
        			last.next = xNode;
        			last = last.next;
        		}
        		pre.next = xNode.next;
        		xNode = xNode.next;
        	} else {
        		pre = xNode;
        		xNode = xNode.next;
        	}
        }
        if(null == first || null == last) return head;
        if(null != insertPre){
        	last.next = insertPointer;
        	insertPre.next = first;
        } else {
        	last.next = head;
        	return first;
        }
        return head;
    }
}

