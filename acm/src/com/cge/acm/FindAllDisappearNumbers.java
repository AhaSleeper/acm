package com.cge.acm;

import java.util.ArrayList;
import java.util.List;

public class FindAllDisappearNumbers {
	public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] numMemo = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            numMemo[i]=-1;
        }
        List<Integer> disappearNumbers = new ArrayList<Integer>();
        for(int i=0; i<nums.length; i++){
            numMemo[nums[i]-1] = 1;
        }
        for(int i=0; i<nums.length; i++){
            if(numMemo[i]==-1) disappearNumbers.add(i+1);
        }
        return disappearNumbers;
    }
}
