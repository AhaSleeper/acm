package com.cge.acm;
/**
 * sort linked list(quick sort)
 * @author Administrator
 *
 */
public class SortList {
	public ListNode sortList(ListNode head) {
        if(null == head || head.next == null) return head;
        ListNode curr = head;
        int size = 0;
        while(curr!=null){
            size++;
            curr=curr.next;
        }
        int[] arr = new int[size];
        int i = 0;
        curr = head;
        while(curr!=null){
            arr[i]=curr.val;
            curr = curr.next;
            i++;
        }
        quickSort(arr, 0, size-1);
        i=0;
        curr = head;
        while(curr!=null){
            curr.val = arr[i];
            curr = curr.next;
            i++;
        }
        return head;
    }
    public void quickSort(int[] arr, int low, int high){
        if(low>=high) return;
        int benchMarkIdx = arr[(low+high)/2];
        int i=low, j=high, temp;
        while(i<=j){
            while(arr[i]<benchMarkIdx){
                ++i;
            }
            while(arr[j]>benchMarkIdx){
                --j;
            }
            if(i<j){
                temp = arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                ++i;
                --j;
            } else if(i==j){
                ++i;
            }
        }
        quickSort(arr, low, j);
        quickSort(arr, i, high);
    }
}
