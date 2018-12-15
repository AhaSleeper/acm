package com.cge.acm;
public class MyLinkedList {
	private ListNode head;
	private int size = 0;
    /** Initialize your data structure here. */
    public MyLinkedList() {
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
    	while(index>size || index<0) return -1;
    	int i=0;
    	ListNode curr = head;
    	while(i<index){
    		curr = curr.next;
    		i++;
    	}
    	if(null != curr)
    		return curr.val;
    	else 
    		return -1;
    }
    
    /** Add a node of value val before the first element of the linked list.
     *  After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if(null != this.head)
        	node.next = this.head.next;
        this.head = node;
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        ListNode curr = this.head;
        if(this.head==null){
        	this.head = node;
        	return;
        }
        while(curr.next != null){
        	curr = curr.next;
        }
        curr.next = node;
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list.
     *  If index equals to the length of linked list, the node will be appended to the end of linked list. 
     *  If index is greater than the length, the node will not be inserted.
     *  
     */
    public void addAtIndex(int index, int val) {
        if(index>size || index<0) return;
        ListNode curr = this.head, pre = null;
        if(index<size){
        	int i=0;
        	while(i<index){
        		pre = curr;
        		curr = curr.next;
        		i++;
        	}
        	ListNode node = new ListNode(val);
        	pre.next = node;
        	node.next = curr;
        } else {
        	while(curr!=null && curr.next!=null){
        		curr = curr.next;
        	}
        	ListNode node = new ListNode(val);
        	if(curr!=null)
        		curr.next = node;
        	else 
        		this.head = node;
        }
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
    	if(index>size || index<0) return;
        ListNode curr = this.head, pre=null;
        int i=0;
        while(i<index){
        	pre = curr;
        	curr = curr.next;
        }
        if(pre==null){
        	this.head = null;
        } else {
        	pre.next = curr.next;
        }
        size--;
    }
    
    @Override
	public String toString() {
		ListNode curr = this.head;
		String str = "";
		while(curr!=null){
			str += curr.val+",";
			curr = curr.next;
		}
		return str;
	}

	public static void main(String[] args) {
		//["MyLinkedList","get","addAtIndex","get","get","addAtIndex","get","get"]
		//[[],[0],[1,2],[0],[1],[0,1],[0],[1]]
    	MyLinkedList obj = new MyLinkedList();
    	obj.get(0);
    	obj.addAtIndex(1, 2);;
    	obj.get(0);
    	obj.get(1);
    	obj.addAtIndex(0, 1);
    	obj.get(0);
    	obj.get(1);
//    	obj.addAtHead(5);
//    	System.out.println(obj);
//    	obj.addAtTail(4);
//    	System.out.println("get:"+obj.get(1));
//    	System.out.println(obj);
//    	obj.addAtIndex(1,9);
//    	obj.addAtIndex(1,10);
//    	System.out.println(obj);
//    	obj.deleteAtIndex(2);
//    	System.out.println(obj);
//    	obj.addAtTail(12);
//    	System.out.println(obj);
	}
}
