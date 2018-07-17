package com.cge.acm;

public class LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		if(null == strs || strs.length==0) return "";
		int j=0;
		StringBuilder sb = new StringBuilder("");
        while(true){
        	if(j<strs[0].length()){
	        	char c = strs[0].charAt(j);
	        	boolean flag = true;
	        	for(int i=1;i<strs.length; i++){
	        		if(j<strs[i].length()){
		        		if(c!=strs[i].charAt(j)) {
		        			flag = false;
		        		} 
	        		} else flag = false;
	        	}
	        	if(flag) sb.append(c);
	        	else return sb.toString();
	        	j++;
        	} else return sb.toString();
        }
    }
	
	public static void main(String[] args) {
		String[] strs = {"dog","racecar","car"};
		System.out.println(longestCommonPrefix(strs));
	}
}
