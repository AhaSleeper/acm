package com.cge.acm;

import java.util.ArrayList;
import java.util.List;

/**
 * 17.
 * @author Administrator
 *
 */
public class LetterCombinationOfPhoneNum {
	//2-abc, 3-def, 4-ghi, 5-jkl, 6-mno, 7-pqrs, 8-tuv, 9-wxyz
	public static List<String> letterCombinations(String digits) {
		if(digits==null || digits.length()==0) return null;
		List<String> phoneNumStrList = new ArrayList<String>();
		for(int i=0; i<digits.length(); i++){
			char c = digits.charAt(i);
			switch(c){
				case '2':
					phoneNumStrList.add("abc");
					break;
				case '3':
					phoneNumStrList.add("def");
					break;
				case '4':
					phoneNumStrList.add("ghi");
					break;
				case '5':
					phoneNumStrList.add("jkl");
					break;
				case '6':
					phoneNumStrList.add("mno");
					break;
				case '7':
					phoneNumStrList.add("pqrs");
					break;
				case '8':
					phoneNumStrList.add("tuv");
					break;
				case '9':
					phoneNumStrList.add("wxyz");
					break;
				case '0':
					phoneNumStrList.add(" ");
					break;
				default: break;	
			}
		}
		List<String> letterCombinationList = new ArrayList<String>();
		for(int i=0; i<phoneNumStrList.get(0).length(); i++){
			letterCombinationList.add(phoneNumStrList.get(0).charAt(i)+"");
		}
		
		for(int i=1; i<phoneNumStrList.size(); i++){
			String str = phoneNumStrList.get(i);
			List<String> tempList = new ArrayList<String>();
			for(int j=0; j<str.length(); j++){
				for(String currStr : letterCombinationList){
					tempList.add(currStr+str.charAt(j));
				}
			}
			letterCombinationList = tempList;
		}
        return letterCombinationList;
    }
	public static void main(String[] args) {
		System.out.println(letterCombinations("13640888494"));
	}
}
