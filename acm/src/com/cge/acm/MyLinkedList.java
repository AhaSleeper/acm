package com.cge.acm;
public class MyLinkedList {
	private ListNode head;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.head = new ListNode(0);
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
    	int i=1;
    	ListNode curr = head.next;
    	while(curr!=null && i<index){
    		curr = curr.next;
    		i++;
    	}
    	if(i==index) 
    		return curr.val;
    	else 
    		return -1;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = this.head.next;
        this.head.next = node;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        ListNode curr = this.head;
        while(curr.next!=null){
        	curr = curr.next;
        }
        curr.next = node;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        ListNode node = new ListNode(val);
        ListNode pre = this.head, curr = this.head.next;
        int i=1;
        while(i<index && curr != null){
        	curr = curr.next;
        	pre = curr;
        	++i;
        }
        if(i==index && curr!=null){
        	pre.next = node;
        	node.next = curr;
        } else if(i==index && curr==null){
        	pre.next = node;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        ListNode pre = this.head;
        ListNode curr = this.head.next;
        int i=0;
        while(i<index && curr !=null){
        	pre = curr;
        	curr = curr.next;
        	++i;
        }
        if(i==index){
        	pre.next=curr.next;
        }
    }
    
    @Override
	public String toString() {
		ListNode curr = this.head.next;
		String str = "";
		while(curr!=null){
			str += curr.val+",";
			curr = curr.next;
		}
		return str;
	}

	public static void main(String[] args) {
    	MyLinkedList obj = new MyLinkedList();
    	int param_1 = obj.get(0);
    	obj.addAtHead(5);
    	System.out.println(obj);
    	obj.addAtTail(4);
    	System.out.println("get:"+obj.get(1));
    	System.out.println(obj);
    	obj.addAtIndex(1,9);
    	obj.addAtIndex(1,10);
    	System.out.println(obj);
    	obj.deleteAtIndex(2);
    	System.out.println(obj);
    	obj.addAtTail(12);
    	System.out.println(obj);
	}
}
