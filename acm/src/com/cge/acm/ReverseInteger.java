package com.cge.acm;

public class ReverseInteger {
	public static void main(String[] args) {
		System.out.println(reverse(-123));
	}
	
	public static int reverse(int x){
		if(x>Integer.MAX_VALUE || x<Integer.MIN_VALUE){
			return 0;
		}
		String str = String.valueOf(x);
		str = str.replace("-", "");
		StringBuilder sb = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i--){
			sb.append(str.charAt(i));
		}
		try{
			int result = Integer.valueOf(sb.toString());
			if(x>0)
				return result;
			else 
				return -result;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
