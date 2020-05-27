package com.cge.acm;

public class Sort {
    /**
     * 冒泡排序
     * @param a
     * @param n
     */
    public static void bubbleSort(int[] a, int n){
        if(n<=1) return;
        for(int i=0; i<n; i++){
            boolean flag = false;
            for(int j=0; j<n-i-1; j++){
                if(a[j]>a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag) return;
        }
    }

    /**
     * 插入排序
     * @param a
     * @param n
     */
    public static void insertionSort(int[] a, int n){
        if(n<=1) return;
        for(int i=1; i<n; i++){
            int value = a[i];
            int j = i-1;
            for(; j>=0; --j){
                if(a[j]>value){
                    a[j+1]=a[j];
                } else {
                    break;
                }
            }
            a[j+1]=value;
        }
    }

    public static void main(String[] args) {
        int[] a = {4,5,6,3,2,1};
        insertionSort(a, 6);
        for (int i : a) {
            System.out.print(i+" ");
        }
    }
}
