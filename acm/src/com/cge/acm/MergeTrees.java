package com.cge.acm;

/**
 * 合并二叉树
 * @author Administrator
 *
 */
public class MergeTrees {
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if(t1==null) return t2;
		if(t2==null) return t1;
		return mergeTree(t1, t2);
    }
	public TreeNode mergeTree(TreeNode t1, TreeNode t2){
		if(t1!= null && t2!=null){
			t1.val = t1.val+t2.val;
			t1.left = mergeTree(t1.left, t2.left);
			t1.right = mergeTree(t1.right, t2.right);
		}
		if(t1==null) t1=t2;
		return t1;
	}
}
