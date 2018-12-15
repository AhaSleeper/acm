package com.cge.acm;

/**
 * leetcode 
 * 27. remove element
 * @author Administrator
 * {@link https://leetcode.com/problems/remove-element/description/}
 */
public class RemoveElement {
	public int removeElement(int[] nums, int val) {
        int count = 0;
        int low=0, high=nums.length-1;
        while(low<=high){
            if(nums[low]==val){
                if(nums[high]!=val){
                    nums[low]=nums[high];
                    low++;
                }
                high--;
                count++;
            } else low++;
        }
        return nums.length-count;
    }
}
