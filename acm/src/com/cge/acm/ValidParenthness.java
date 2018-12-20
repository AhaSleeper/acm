package com.cge.acm;

import java.util.LinkedList;

public class ValidParenthness {
	 public static boolean isValid(String s) {
	        if(s==null || s.length()==0) return true;
	        LinkedList<Character> stack = new LinkedList<Character>();
	        Character temp;
	        for(int i=0; i<s.length(); i++){
	            char c= s.charAt(i);
	            if(c == '(' || c=='[' || c=='{'){
	                stack.push(c);
	            } else {
	                if(stack.size()<=0) return false;
	                temp = stack.pop();
	                if(temp==null) return false; 
	                if(c==')' && temp!='(') 
	                    return false;
	                else if(c==']' && temp!='[')
	                    return false;
	                else if(c=='}' && temp!='{')
	                    return false;
	            }
	        }
	        return true;
	    }
	 public static void main(String[] args) {
		System.out.println(isValid("]"));
	}
}
