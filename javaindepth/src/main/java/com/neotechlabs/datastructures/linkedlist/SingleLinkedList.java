package com.neotechlabs.datastructures.linkedlist;

public class SingleLinkedList {

    private SingleNode head;
    private int size;

    public SingleLinkedList() {
        head = null;
        size = 0;
    }

    public void add(int data) {
        if( head == null ) {
            head = new SingleNode(data);
        } else {
            SingleNode newNode = new SingleNode(data);
            SingleNode currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        size++;
    }

    public void printList() {
        if (size == 0) {
            System.out.println("EMPTY LIST");
        } else {
            SingleNode currentNode = head;
            while(currentNode != null) {
                System.out.print(currentNode);
                currentNode = currentNode.getNext();
            }
            System.out.println();
        }
    }

    public boolean findNthFromReverse(int n) {
        if (head != null) {
            SingleNode currentNode = head;
            SingleNode nthPtrNode = currentNode;
            for (int i = n; i > 1; i--) {
                currentNode = currentNode.getNext();
                if(currentNode == null)
                    return false;
            }

            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                nthPtrNode = nthPtrNode.getNext();
            }

            System.out.println("FOUND " + n + " TO LAST NODE : " + nthPtrNode.getData());
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.printList();

        list.findNthFromReverse(1);
        list.findNthFromReverse(2);
        list.findNthFromReverse(3);
    }
}
