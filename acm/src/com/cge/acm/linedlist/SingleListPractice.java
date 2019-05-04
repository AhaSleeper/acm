package com.cge.acm.linedlist;

/**
 * 单链表练习
 */
public class SingleListPractice {
    public Node singleListReverse(Node head){
        if(null == head || head.next != null) return head;
        Node current = head, pre = null, next = null;
        if(head.next != null) next = head.next;
        while(next!=null){
            next = current.next;
            current.next = pre;
            pre = current;
            if(null != next)
                current = next;
        }
        return current;
    }

    public static void main(String[] args){
        Node<Integer> n1 = new Node(1,null);
        Node<Integer> n2 = new Node(2, n1);
        Node<Integer> n3 = new Node(3, n2);
        Node<Integer> n4 = new Node(4, n3);
        Node<Integer> n5 = new Node(5, n4);
        SingleListPractice singleListPractice = new SingleListPractice();
        Node.printNode(n5);
        System.out.println("\nafter reverse:");
        Node.printNode(singleListPractice.singleListReverse(n5));

    }
}
class Node<T> {
    T value;
    Node<T> next;
    public Node(T value, Node<T> next){
        this.value = value;
        this.next = next;
    }

    public static void printNode(Node node){
        while(node!=null){
            System.out.print(node.value+",");
            node = node.next;
        }
    }
}
