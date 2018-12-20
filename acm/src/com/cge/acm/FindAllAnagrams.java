package com.cge.acm;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagrams {
	public static List<Integer> findAnagrams(String s, String p) {
		 List<Integer> list = new ArrayList<Integer>();
	        if(s==null || s.length()==0 || p==null || p.length()==0) return list;
	        int left=0, right=0, count=p.length(); 
	        //构建hash表
	        int[] hash = new int[256];
	        for(int i=0; i<p.length(); i++){
	            hash[p.charAt(i)]++;
	        }
	        while(right<s.length()){
	            if(hash[s.charAt(right)] >=1){
	            	hash[s.charAt(right)]--;
	            	right++;
	            	count--;
	            }
	            if(count==0) list.add(left);
	            if(right-left==p.length()){
	            	if(hash[s.charAt(left)] >=0 ){
	            		hash[s.charAt(left)]++;
	            		left++;
	            		count++;
	            	}
	            }
	        }
	        return list;
    }
	public static void main(String[] args) {
		System.out.println(findAnagrams("abab", "ab"));
	}
}
