package com.cge.acm;

import java.util.Arrays;

public class RemoveDuplicates {
	 public static int removeDuplicates2(int[] nums){
		 int n = nums.length;
		 if(n < 2) return n;
        int id = 1;
        for(int i = 1; i < n; ++i) 
            if(nums[i] != nums[i-1]) nums[id++] = nums[i];
        return id;
	 }
	 public static int removeDuplicates(int[] nums){
		 int idx = 1;
		 for(int i=1; i < nums.length; ++i){
			 if(nums[i] != nums[i-1]) nums[idx++] = nums[i];
		 }
		 return idx;
	 }
	 public static void main(String[] args) {
		int[] nums = {1,1,2};
		System.out.println(removeDuplicates(nums));
		System.out.println(Arrays.toString(nums));
	}
}
 