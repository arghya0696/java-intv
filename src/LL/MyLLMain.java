package LL;

public class MyLLMain {

    public static void main(String[] args) {

        MyLinkedList myLinkedList = new MyLinkedList();

        myLinkedList.addLast(10);
        myLinkedList.addLast(11);
        myLinkedList.addLast(12);
        myLinkedList.addFirst(9);
        myLinkedList.addFirst(8);
        myLinkedList.addLast(13);

        myLinkedList.print();

//        myLinkedList.reverse();
//        myLinkedList.removeHead();
//        myLinkedList.removeHead();
//        myLinkedList.removeTail();
//        myLinkedList.removeTail();
        myLinkedList.removeNthElement(4);
        System.out.println();

        myLinkedList.print();

    }
}
