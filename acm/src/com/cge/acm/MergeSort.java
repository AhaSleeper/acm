package com.cge.acm;

public class MergeSort {
	public static void mergeSort(int[] a, int low, int high){
		if(low>=high) return ;
		int mid = low + (high-low)/2;
		mergeSort(a, low, mid);
		mergeSort(a, mid+1, high);
		merge(a, low, mid, high);
	}
	public static void merge(int[] a, int low, int mid, int high){
		int i=low, j=mid+1, k=0;
		int[] temp = new int[high-low+1];
		while(i<=mid && j<=high){
			if(a[i]<=a[j]){
				temp[k]=a[i];
				k++;
				i++;
			} else {
				temp[k]=a[j];
				k++;
				j++;
			}
		}
		int start=i, end=mid;
		if(j<=high) {
			start=j;
			end=high;
		}
		while(start<=end){
			temp[k]=a[start];
			k++;
			start++;
		}
		k=0;
		for(i=low; i<=high; i++){
			a[i]=temp[k++];
		}
	}
	public static void main(String[] args) {
		int[] a = {2,3,12,4,96,34,84,67,10,8,384,90};
		mergeSort(a, 0, a.length-1);
		for(Integer i : a){
			System.out.print(i+",");
		}
	}
}
