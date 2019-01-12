package com.cge.acm;

public class QuickSort {
	public void sort(int[] a, int low, int high){
		if(low>=high) return;
		int j = partition(a, low, high);
		sort(a, low, j-1);
		sort(a, j+1, high);
	}
	public int partition(int[] a, int low, int high){
		int pivot = a[low], i=low, j=high+1;
		while(true){
			while(a[++i]<pivot){
				if(i==high) break;
			}
			while(a[--j]>pivot){
				if(j==low) break;
			}
			if(i>=j) break;
			swap(a, i, j);
		}
		swap(a, low, j);
		return j;
	}
	public void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
