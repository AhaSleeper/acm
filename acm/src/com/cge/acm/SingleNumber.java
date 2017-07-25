package com.cge.acm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SingleNumber {
	public static void main(String[] args) {
		int[] nums = {1, 2, 1, 3, 2, 5};
		System.out.println(singleNumber(nums));
	}
	
	public static int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++){
        	Integer count = map.get(nums[i]);
        	if(null == count){
        		map.put(nums[i], 1);
        	} else {
        		map.put(nums[i], count+1);
        	}
        }
        
        Set<Entry<Integer, Integer>> entrySet = map.entrySet();
        List<Integer> list = new ArrayList<Integer>();
        for(Entry<Integer, Integer> entry : entrySet){
        	if(entry.getValue()==1)list.add(entry.getKey());
        }
        int[] numArr = new int[list.size()];
        for(int i=0; i<list.size(); i++)
        	numArr[i] = list.get(i);
        return numArr;
    }
}
