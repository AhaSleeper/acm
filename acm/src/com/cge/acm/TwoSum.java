package com.cge.acm;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public static int[] twoSum(int[] nums, int target) throws Exception{
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<nums.length; i++){
			if(map.containsKey(target-nums[i])){
				result[1] = i;
				result[0] = map.get(target-nums[i]);
				return result;
			} 
			map.put(nums[i], i);
		}
		throw new Exception("no result");
	}
	public static void main(String[] args) throws Exception {
		int[] nums = {2,7,6,10};
		int[] result = twoSum(nums, 9); 
		for(Integer idx : result){
			System.out.print(idx+",");
		}
	}
}
