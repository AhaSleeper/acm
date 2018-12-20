package com.cge.acm;

/**
 * 原理：Floyd判圈算法  https://zh.wikipedia.org/wiki/Floyd%E5%88%A4%E5%9C%88%E7%AE%97%E6%B3%95
 * @author Administrator
 *
 */
public class LinkedListCycleII {
	public ListNode detectCycle(ListNode head) {
        if(null == head || head.next==null) return null;
        ListNode walker = head, runner = head;
        while(runner != null && runner.next!=null){
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner){
                walker = head;
                while(walker!=runner){
                    walker = walker.next;
                    runner = runner.next;
                }
                return walker;
            }
        }
        return null;
    }
}
