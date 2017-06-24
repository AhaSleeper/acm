package com.cge.acm;

public class MedianOfTwoSortedArray {
	private static int[] temp;
	private static int[] mergeNums;
	public static void main(String[] args) {
		int[] nums1 = {1,831,94};
		int[] nums2 = {3,4,7,23,90,23};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m=nums1.length, n = nums2.length;
		mergeNums = new int[m+n];
		for(int i = 0; i<nums1.length; i++){
			mergeNums[i] = nums1[i];
		}
        for(int j=m,i=0; j<m+n; j++,i++){
        	mergeNums[j]=nums2[i];
        }
//        int temp,j;
//        for(int i=1; i<mergeNums.length; i++){
//        	temp = mergeNums[i];
//        	j = i;
//        	while(j>0 && temp<mergeNums[j-1]){
//        		mergeNums[j] = mergeNums[j-1];
//        		j--;
//        	}
//        	mergeNums[j] = temp;
//        }
        mergeSort(mergeNums);
        for(int i=0; i<mergeNums.length; i++){
        	System.out.print(mergeNums[i]+" ");
        }
        System.out.println();
        int mid = mergeNums.length/2;
        if(mergeNums.length%2==0){
        	return (mergeNums[mid-1]+mergeNums[mid])/2.0;
        } else {
        	return (mergeNums[(int)Math.floor(mid)]);
        }
    }
	
	public static int[] mergeSort(int a[]){
		temp = new int[a.length];
		sort(a, 0, a.length-1);
		return a;
 	}
	
	public static void sort(int a[], int low, int high){
		if(high<=low) return;
		int mid = low+(high-low)/2;
		sort(a,low, mid);
		sort(a,mid+1, high);
		merge(a, low, mid, high);
	}
	
	public static void merge(int a[], int low, int mid, int high){
		int i=low, j=mid+1;
		for(int k=low; k<=high; k++){
			temp[k] = a[k];
		}
		for(int k=low; k<=high; k++){
			if(i>mid) a[k]=temp[j++];
			else if(j>high) a[k]=temp[i++];
			else if(temp[i]<temp[j]) a[k] = temp[i++];
			else a[k]=temp[j++];
		}
	}
}
