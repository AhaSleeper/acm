package com.cge.acm;

/**
 * 字符串转正整数
 * @author Administrator
 *
 */
public class Atoi {
	public static int myAtoi(String str) {
		if(null == str || str.length()==0) return 0;
		if((str.charAt(0)>='a' && str.charAt(0) <= 'z') ||
				(str.charAt(0)>='A' && str.charAt(0) <= 'Z'))return 0;
		boolean flag = true;
		int j=0;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(j==0 && c==' '){
				continue;
			} else if(j==0){
				j=1;
			}
			if(j==1){
				j++;
				if(c=='+') 
					flag = true;
				else if(c=='-') 
					flag = false;
				else if(c>='0' && c<='9')
					sb.append(c);
				else return 0;
			} else if(c>='0' && c<='9') {
				sb.append(c);
			} else break;
		}
		if(sb.length()>0){
			if(flag) {
				try{
					return Integer.valueOf(sb.toString());
				}catch(NumberFormatException e){
					return Integer.MAX_VALUE;
				}
			}
			else {
				try{
					return -Integer.valueOf(sb.toString());
				}catch(NumberFormatException e){
					return Integer.MIN_VALUE;
				}
			}
		}
        return 0;
    }
	public static void main(String[] args) {
		System.out.println(myAtoi("  +0 123"));
	}
}
