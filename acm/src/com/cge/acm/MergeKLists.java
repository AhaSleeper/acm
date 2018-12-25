package com.cge.acm;

public class MergeKLists {
	/**
	 * insertion sort
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode sortedNode,pre;
        for(ListNode list : lists){
            while(null != list){
                pre = dummy;
                sortedNode = dummy.next;
                ListNode newNode = new ListNode(list.val);
                while(sortedNode!=null){
                    if(sortedNode.val>list.val){
                        pre.next = newNode;
                        newNode.next = sortedNode;
                        break;
                    }
                    pre = sortedNode;
                    sortedNode = sortedNode.next;
                }
                if(null == sortedNode){
                    pre.next = newNode;
                    sortedNode = newNode;
                }
                list = list.next;
            }
        }
        return dummy.next;
    }
}
