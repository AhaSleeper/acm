package com.cge.acm;
/**
 * @author Administrator
 *
 * test case
 * //["MyLinkedList","addAtHead","addAtHead","deleteAtIndex","addAtIndex","addAtHead","addAtHead" ,"addAtHead","get","addAtTail","addAtIndex","addAtHead"]
    [[],[5],[2],[1],[1,9],[4],[9],[8],[3],[1],[3,6],[3]]
 */
public class MyLinkedList {
	private ListNode first;
	private ListNode last;
	private int size = 0;
    /** Initialize your data structure here. */
    public MyLinkedList() {
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
    	if(index>size-1 || index<0) return -1;
    	int i=0;
    	ListNode curr = first;
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
        if(null == this.first)
        	this.first = node;
        else {
        	node.next = this.first;
        	this.first = node;
        }
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if(this.first==null){
        	this.first = node;
        	this.last = node;
        	size++;
        	return;
        }
        node.next = this.last.next;
        this.last = node;
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list.
     *  If index equals to the length of linked list, the node will be appended to the end of linked list. 
     *  If index is greater than the length, the node will not be inserted.
     *  
     */
    public void addAtIndex(int index, int val) {
        if(index>size || index<0) return;
        ListNode curr = this.first, pre = null;
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
        		this.first = node;
        }
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
    	if(index>size-1 || index<0) return;
        ListNode curr = this.first, pre=null;
        int i=0;
        while(i<index){
        	pre = curr;
        	curr = curr.next;
        	i++;
        }
        if(pre==null){
        	this.first = null;
        } else {
        	if(curr==null)
        		pre.next = null;
        	else 
        		pre.next = curr.next;
        }
        size--;
    }
    
    @Override
	public String toString() {
		ListNode curr = this.first;
		String str = "";
		while(curr!=null){
			str += curr.val+",";
			curr = curr.next;
		}
		return str;
	}
}
