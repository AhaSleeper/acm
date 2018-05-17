package com.cge.acm;

/**
 * 
 * @author Administrator
 *
 */
public class MinStack {
	/** initialize your data structure here. */
	private int length = 100;
	private Integer[] arr = new Integer[length];
	private int top=-1;
    public MinStack() {
    	
    }
    public void push(int x) {
    	top++;
        if(top>=arr.length){
        	Integer[] temp = new Integer[arr.length+100];
        	for(int i=0; i<arr.length; i++){
        		temp[i] = arr[i];
        	}
        	arr = temp;
        }
        arr[top]=x;	
    }
    
    public void pop() {
        top--;
    }
    
    public int top() {
    	if(top>=0)
    		return arr[top];
    	else 
    		return 0;
    }
    
    public int getMin() {
    	if(top<0) return 0;	
    	Integer minVal = Integer.MAX_VALUE;
    	for(int i=0; i<=top; i++){
    		if(arr[i]==null) continue;
    		if(minVal>arr[i]) {
    			minVal = arr[i];
    		}
    	}
    	return minVal;
    }
    
    public static void main(String[] args) {
    	MinStack obj = new MinStack();
    	obj.push(1);
    	obj.push(2);
    	obj.top();
    	obj.getMin();
    	obj.pop();
    	obj.getMin();
    	obj.top();
	}
}
