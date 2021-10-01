package com.bridgelabz;

public class MyQueue<T> {

    MyLinkedList<T> myLinkedList;


    public MyQueue(){
        myLinkedList = new MyLinkedList<T>();
    }

    public void enqueue(T data){
        myLinkedList.add(data);
    }

    public T dequeue(){
        return myLinkedList.pop(0);
    }

    public boolean isEmpty(){
        return myLinkedList.isEmpty();
    }

    public int size(){
        return myLinkedList.size();
    }
}
