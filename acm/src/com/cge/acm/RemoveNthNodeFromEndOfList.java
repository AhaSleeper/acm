package com.cge.acm;

public class RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		
		/*int len = 0;
    	ListNode node = head;
    	while(node!=null){
    		len++;
    		node=node.next;
    	}
         if(n==len) {
            head = head.next;
            return head;
        }
    	int idx = len-n-1;
    	node = head;
    	while(idx>0){
    		node = node.next;
    		idx--;
    	}
    	node.next = node.next.next;
    	return head;*/
		
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        //Move fast in front so that the gap between slow and fast becomes n
        for(int i=1; i<=n+1; i++)   {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }
}
