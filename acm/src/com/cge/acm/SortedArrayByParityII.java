package com.cge.acm;

public class SortedArrayByParityII {
	public int[] sortArrayByParityII(int[] a) {
        int j=1;
        for(int i=0; i<a.length; i+=2){
            if(a[i]%2==1){
                while(a[j]%2==1){
                    j+=2;
                }
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        return a;
    }
}
