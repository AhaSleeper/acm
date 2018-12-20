package com.cge.acm;

/**
 * 使用快指针、慢指针
 * 1.快指针与慢指针相差一步，慢指针前进一步，快指针前进两步，相遇；
 * 2.快指针与慢指针相差两步，慢指针前进一步，快指针前进两步，转化为情况1；
 * @author zhuojh
 *
 */
public class HasCycle {
	public static boolean hasCycle(ListNode node){
		ListNode walker=node, runner=node;
		while(runner.next!=null && runner.next.next!=null){
			walker = walker.next;
			runner = runner.next.next;
			if(walker==runner) return true;
		}
		return false;
	}
}
