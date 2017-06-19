package com.cge.acm;

/**
 * 回文
 * @author zhuojh
 *
 */
public class PalindromeNumber {
	public static void main(String[] args) {
		System.out.println(isPalindrome(-321123));
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
		return true;
	}
}
