package com.cge.acm;
	
import java.util.HashMap;
import java.util.Map;

/**
 * 求数组中的两个数字之和等于目标值
 * @author Administrator
 *
 */
public class TwoSum {
	public static int[] twoSum(int[] nums, int target) throws Exception{
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<nums.length; i++){ 
			if(map.containsKey(target-nums[i])){//
				result[1] = i;
				result[0] = map.get(target-nums[i]);
				return result;
			} 
			map.put(nums[i], i);
		}
		throw new Exception("no result.");
	}
	public static void main(String[] args) throws Exception {//args
		int[] nums = {2,7,6,10};
		int[] result = twoSum(nums, 9); 
		for(Integer idx : result){
			System.out.print(idx+",");
		}
		//向右移位，+1
		System.out.println(5>>1);
	}
}
