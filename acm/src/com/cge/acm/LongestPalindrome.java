package com.cge.acm;

/**
 * 查找一个字符的最长回文串
 * @author Administrator
 *
 */
public class LongestPalindrome {
	public static void main(String[] args) {
		String str = "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc";
//		System.out.println(longestPalindromeBf(str));
		System.out.println(longestPalindromeDp("c"));
	}
	/**
	 * 暴力法
	 * @param s
	 * @return
	 */
	public static String longestPalindromeBf(String s) {
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
	/**
	 * 最长回文子串，动态规划求法
	 * @param s
	 */
	public static String longestPalindromeDp(String s){
		boolean[][] flag= new boolean[1000][1000];
		int length = s.length();
		for(int i = 0; i<1000;i++){
			for(int j=0; j<1000; j++)
				flag[i][j]=false;
		}
		for(int i=0; i<length-1; i++){
			flag[i][i]=true;
		}
		int start = 0;
		int maxLength = 1;
		for(int i=0; i<length-1; i++){
			if(s.charAt(i)==s.charAt(i+1)){
				flag[i][i+1]=true;
				start = i;
				maxLength=2;
			}
		}
		
		for(int len=3; len<=length; len++){
			for(int i=0; i<length-len+1; i++){
				int j = i+len-1;
				if(s.charAt(i)==s.charAt(j) && flag[i+1][j-1]){
					flag[i][j]=true;
					maxLength=len;
					start = i;
				}
			}
		}
		System.out.println("start="+start+",maxLen="+maxLength);
		return s.substring(start, start+maxLength);
	}
}
