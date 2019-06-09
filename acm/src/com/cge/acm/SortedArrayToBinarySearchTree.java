package com.cge.acm;

import java.util.HashMap;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 * [-10,-3,0,5,9]
 *
 */
public class SortedArrayToBinarySearchTree {
    /**
     * 由于链表中的元素已经排好序了
     * 1.获取链表的中间元素作为根节点
     * 2.中间元素的左边的元素生成左子树
     * 3.中间元素的右边的元素生成右子树
     * 4.递归执行以上步骤，直到当前元素等于中间元素或当前元素为空
     */
    public ListNode findMidElement(ListNode head){
        ListNode walker = head,runner = head, pre=null;
        while(runner != null && runner.next != null){
            pre = walker;
            walker = walker.next;
            runner = runner.next.next;
        }
        if(null != pre) {
            pre.next = null;
        }
        return walker;
    }
    public TreeNode sortedListToBST(ListNode head){
        if(null == head) return null;
        ListNode mid = findMidElement(head);
        TreeNode treeNode = new TreeNode(mid.val);
        if(head == mid) return treeNode;
        treeNode.left = sortedListToBST(head);
        treeNode.right = sortedListToBST(mid.next);
        return treeNode;
    }
    public  TreeNode createTreeFromList(ListNode head) {
        if(null == head) return null;
        TreeNode treeNode = new TreeNode(head.val);
        head = head.next;
        while(head!=null){
            insertToTree(treeNode, new TreeNode(head.val));
            head = head.next;
        }
        return treeNode;
    }

    /**
     * 排序数组转化为平衡查找树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0) return null;
        return arrayToBST(nums, 0, nums.length-1);
    }
    public TreeNode arrayToBST(int[] nums, int low, int high){
        int mid = (high+low)/2;
        if(low>high) return null;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = arrayToBST(nums, low, mid-1);
        node.right = arrayToBST(nums, mid+1, high);
        return node;
    }

    public static void insertToTree(TreeNode head, TreeNode node){
        if(node.val > head.val) {
            if(head.right == null)
                head.right = node;
            else
                insertToTree(head.right, node);
        } else {
            if(head.left == null)
                head.left = node;
            else
                insertToTree(head.left, node);
        }
    }

    public static void inOrderTree(TreeNode head){
        if(null == head) {
            return;
        }
        inOrderTree(head.left);
        System.out.print(head.val+" ");
        inOrderTree(head.right);
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(-10);
        ListNode node1 = new ListNode(-3);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(9);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
       SortedArrayToBinarySearchTree s = new SortedArrayToBinarySearchTree();
       TreeNode treeNode = s.sortedListToBST(node);
        inOrderTree(treeNode);
    }

}

