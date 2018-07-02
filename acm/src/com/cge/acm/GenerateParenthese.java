package com.cge.acm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GenerateParenthese {
	 static Set<String> combinationList = new HashSet<String>();
	 public static List<String> generateParenthesis(int n) {
		 List<String> resultList = new ArrayList<String>();
		 String parenthStr = "";
		 //1.first get all the combination of the given parenthesis.
		 for(int i=0; i<n; i++){
			 parenthStr += "()";
		 }
		 permutation(parenthStr.toCharArray(), 0, parenthStr.length()-1);	 
		 //2.check those legal parenthesis.
		 LinkedList<Character> stack = new LinkedList<Character>();
		 for(String str : combinationList){
			 int i=0;boolean addFlag =true;
			 while(i<str.length()){
				 char c = str.charAt(i);
				 if(c=='(') stack.push(c);
				 else if(c==')'){
					 if(stack.size()>0){
						 char topChar = stack.pop();
						 if(topChar!='('){
							 addFlag = false;
							 break;
						 }
					 } else {
						 addFlag = false;
						 break;
					 }
				 }
				 i++;
			 }
			 if(stack.isEmpty() && addFlag) resultList.add(str);
			 stack.clear();
		 }
//		 backTrack(resultList, "", 0, 0, n);
		 return resultList;
	 }
	 
	 public static void permutation(char[] s, int from, int to){
		 if(to<1) return;
		 if(from == to){
			 combinationList.add(String.valueOf(s));
		 }else {
			 for(int i=from; i<=to; i++){
				 swap(s, i, from);
				 permutation(s, from+1, to);
				 swap(s, from, i);
			 }
		 }
	 }
	 public static void swap(char[] s, int i, int j){
		 char temp = s[i];
		 s[i] = s[j];
		 s[j] = temp;
	 }
	 
	 public static void backTrack(List<String> list, String str, int open, int close, int max){
		 if(str.length()==max*2){
			 list.add(str);
			 return;
		 }
		 
		 if(open<max)
			 backTrack(list, str+"(", open+1, close, max);
		 if(close<open)
			 backTrack(list, str+")", open, close+1, max);
	 }
	 public static void main(String[] args) {
		System.out.println(generateParenthesis(3));
	}
}
