package com.neotechlabs.datastructures.linkedlist;

public class SingleNode {

    private int data;
    private SingleNode next;

    public SingleNode(int data) {
        this(data, null);
    }

    public SingleNode(int data, SingleNode next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(data);
        if(next != null) {
            stringBuilder.append(" -> ");
        }
        return stringBuilder.toString();
    }
}
