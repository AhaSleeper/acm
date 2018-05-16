package com.cge.acm;

public class SingleNumber {
	public static int singleNumber(int[] nums){
		int result=0;
		for(int i=0; i<nums.length; i++){
			result ^= nums[i];
			System.out.print(result+",");
		}
		return result;
	}
	public static void main(String[] args) {
		int[] nums = {2,2,1,4,4,3,1};
		System.out.println(singleNumber(nums));
	}
}
