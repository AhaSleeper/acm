package com.cge.acm;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	/**
	 * use Arrays.sort()
	 * @param lists
	 * @return
	 */
	public ListNode mergeKList(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ArrayList<Integer> arr = new ArrayList<Integer>(50);
        for(ListNode list : lists){
            while(list!=null){
                arr.add(list.val);
                list = list.next;
            }
        }
        int[] sortedArr = new int[arr.size()];
        for(int i=0; i<sortedArr.length; i++){
        	sortedArr[i] = arr.get(i);
        }
        Arrays.sort(sortedArr);
        ListNode pre = dummy, curr = dummy.next;
        for(Integer i : sortedArr){
            ListNode newNode = new ListNode(i);
            if(curr==null){
                pre.next = newNode;
            } else {
                curr.next = newNode;
            }
            curr = newNode;
        }
        return dummy.next;
    }
}
