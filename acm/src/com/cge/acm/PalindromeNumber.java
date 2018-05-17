package com.cge.acm;

/**
 * 回文
 * @author zhuojh
 *
 */
public class PalindromeNumber {
	public static void main(String[] args) {
		System.out.println(isPalindrome(-321123));
		System.out.println(isPalindromeRecursive(2313));
	}
	
	/**
	 * 普通解法
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x){
		if(x<0) return false;
		String str = String.valueOf(x);
		for(int i = 0,j= str.length()-1; i<str.length(); i++,j--){
			if(str.charAt(i)!=str.charAt(j)) return false;
		}
		return true;
	}
	
	/**
	 * 递归解法
	 * @param x
	 * @return
	 */
	public static boolean isPalindromeRecursive(int x){
		if(x<0) return false;
		else {
			String str = String.valueOf(x);
			return recursiveStr(0, str.length()-1, str, str.length());
		}
	}
	public static boolean recursiveStr(int low, int high, String str, int length){
		if(length==0 || length==1)
			return true;
		if(str.charAt(low)!=str.charAt(high))
			return false;
		return recursiveStr(low+1, high-1, str, length-2);
		
	}
	
}
