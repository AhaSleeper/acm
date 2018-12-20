package com.cge.acm;

public class InvertTree {
	public TreeNode invertTree(TreeNode root){
		if(root == null) return null;
		TreeNode left = root.left, right = root.right;
		root.left = invertTree(right);
		root.right = invertTree(left);
		return root;
	}
}
