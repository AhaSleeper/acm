package com.cge.acm;

/**
 * Given an array nums, write a function to move all 0's to the end of it
 *  while maintaining the relative order of the non-zero elements.
 *  Note:
 *  You must do this in-place without making a copy of the array.
 *  Minimize the total number of operations.
 * @author Administrator
 *
 */
public class MoveZeroes {
	public static void moveZeroes(int[] nums) {
		if(nums==null || nums.length==0) return;
		int insertIdx = 0;
		for(int num : nums){
			if(num!=0)nums[insertIdx++]=num;
		}
		while(insertIdx<nums.length){
			nums[insertIdx]=0;
			insertIdx++;
		}
        System.out.print("[");
        for(int i=0; i<nums.length;i++){
        	System.out.print(nums[i]+",");
        }
        System.out.print("]");
    }
	public static void main(String[] args) {
		int[] nums = {0,1};
		moveZeroes(nums);
	}
}
