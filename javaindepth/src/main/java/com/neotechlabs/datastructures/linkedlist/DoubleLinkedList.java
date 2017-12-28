package com.neotechlabs.datastructures.linkedlist;

public class DoubleLinkedList {

    private DLLNode head;
    private DLLNode tail;
    private int length;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    public void insert(int data) {
        DLLNode newNode = new DLLNode(data);
        if(head == null) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }

        length++;
        System.out.println(this);
    }

    public void insertAtEnd(int data) {
        DLLNode newNode = new DLLNode(data);
        if(head == null) {
            head = tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }

        length++;
        System.out.println(this);
    }

    public void insertAtPosition(int data, int position) {
        if (length == 0) {
            insert(data);
        } else {
            if (position == 0) {
                insert(data);
            } else if (position == length) {
                insertAtEnd(data);
            } else {
                int currentPosition = 0;
                DLLNode positionNode = head;
                while (position != currentPosition) {
                    positionNode = positionNode.getNext();
                    currentPosition++;
                }

                DLLNode newNode = new DLLNode(data, positionNode.getPrev(), positionNode);
                newNode.getPrev().setNext(newNode);
                positionNode.setPrev(newNode);
                length++;
                System.out.println(this);
            }
        }
    }

    public int size() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Items: [ ");
        DLLNode temp = head;
        while (temp != null) {
            stringBuilder.append(temp.getData());
            temp = temp.getNext();
            if(temp != null) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.insert(10);
        linkedList.insert(20);
        linkedList.insertAtEnd(30);
        linkedList.insertAtPosition(5, 0);
        linkedList.insertAtPosition(35, linkedList.size());
        linkedList.insertAtPosition(15, 2);
    }
}
