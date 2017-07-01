package com.cge.acm;

/**
 * 查找一个字符的最长回文串
 * @author Administrator
 *
 */
public class LongestPalindrome {
	public static void main(String[] args) {
		String str = "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc";
		System.out.println(longestPalindrome(str));
	}
	/**
	 * 暴力法
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		if(s.length()==1) return s;
        int length = s.length();
        int start=0, maxLength=0;
        for(int i=0; i<length;i++){
        	for(int j=i+1; j<length; j++){
        		int tempI, tempJ;
        		for(tempI=i, tempJ=j; tempI<tempJ; tempI++,tempJ--){
        			if(s.charAt(tempI)!=s.charAt(tempJ))break;
        		}
        		if(tempI>=tempJ && j-i+1>maxLength){
        			maxLength = j-i+1;
        			start = i;
        		}
        	}
        }
        if(maxLength>0) return s.substring(start, maxLength+start);
        else return s.substring(0,1);
    }
}
