package com.cge.acm;

/**
 * 回文串判断
 */
public class Parlindrome {

    public boolean isParlindrome(Node<Character> head){
        if(head != null && head.next == null) return true;
        Node<Character> walker=head, runner=head.next;
        while(walker != null && runner != null && runner.next != null){
            walker = walker.next;
            runner = runner.next.next;
        }
        Node<Character> middle = walker, current = middle.next, pre=middle, next=null;
        if(current != null){
            next = current.next;
            current.next = middle;
        }
        middle.next = null;
        while(next!=null){
            current.next=pre;
            pre = current;
            current = next;
            next = next.next;
        }
        current.next = pre;
        while(head!=null && current!=null && current!=middle && head!=middle){
            if(head.value != current.value) return false;
            head = head.next;
            current = current.next;
        }
        if(head == middle && current == middle) return true;
        return false;
    }

    public static void main(String[] args){
        Node<Character> n1 = new Node('l',null);
        Node<Character> n2 = new Node('e', n1);
        Node<Character> n3 = new Node('v', n2);
        Node<Character> n4 = new Node('e', n3);
        Node<Character> n5 = new Node('l', n4);
        Parlindrome parlindrome = new Parlindrome();
        boolean result = parlindrome.isParlindrome(n5);
        System.out.println("isParlindrome="+result);

    }
}
class Node<T>{
    public T value;
    Node<T> next;

    public Node(T val, Node next){
        this.value = val;
        this.next = next;
    }

    @Override
    public String toString(){
        return "{value:"+value+"}";
    }
}