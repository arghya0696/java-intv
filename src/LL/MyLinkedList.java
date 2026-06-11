package LL;

public class MyLinkedList {

    Node head = null;

    void addLast(int value) {

        Node newNode = new Node(value);

        if(head == null) {
            head = newNode;
        }
        else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    void addFirst(int value) {
        Node newNode = new Node(value);

        if(head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }


    void removeHead() {
        if(head == null)
            return;
        Node prev = head;
        head = head.next;
        prev.next = null;
    }

    void removeTail() {
        if(head == null)
            return;
        Node prev = head;
        Node next = head.next;

        while (next.next!=null) {
            prev = prev.next;
            next = next.next;
        }
        prev.next = null;
    }

    void removeNthElement(int n) {

        Node cur = head;
        Node next = head.next;
        if(n==1)
            head = head.next;
        else if (n==2) {
            head.next = next.next;
        }

        for(int i=1;i<n-1;i++){
            cur = cur.next;
            next = next.next;
        }
        cur.next = next.next;
    }

    void print() {
        Node temp = head;

        while (temp!=null) {
            System.out.print(temp.value+" ");
            temp = temp.next;
        }
    }

    void reverse() {

        if(head == null)
            return;
        Node prev = head.next;
        if(prev == null) { // size 1
            return;
        }
        Node next = head.next.next;
        head.next = null;
        while (prev!=null) {

            prev.next = head;
            head = prev;
            prev = next;
            if(next!=null) {
                next = next.next;
            }
        }
    }






    private class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            next = null;
        }
    }
}
