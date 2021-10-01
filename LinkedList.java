package com.bridgelabz;

class MyLinkedList<T>{

    MyNode<T> head;
    MyNode<T> current;
    int position;


    public MyLinkedList(){
        head = null;
        current = null;
        position = -1;
    }

    public void add(T data){
        MyNode<T> node = new MyNode<T>(data);
        if(head == null){
            head = node;
            current = head;
        }
        else{
            current.next = node;
            current = current.next;
        }
        position++;
    }

    public void remove(T data){
        MyNode<T> tempCurrent = head;
        MyNode<T> tempPrev = null;
        while(!tempCurrent.data.equals(data)){
            tempPrev = tempCurrent;
            tempCurrent = tempCurrent.next;
        }
        if(tempCurrent == head){
            head = head.next;
        }
        else{
            tempPrev.next = tempCurrent.next;
        }
        if(tempCurrent == current){
            current = tempPrev;
        }
        position--;
    }
    public boolean isEmpty(){
        return position == -1 ? true : false;
    }
    public T get(int location) {
        MyNode<T> tempCurrent = head;
        int tempPosition = 0;

        while(tempPosition != location){
            tempCurrent = tempCurrent.next;
            tempPosition++;
        }

        return tempCurrent.data;

    }

    public int size(){
        return position + 1;
    }

    public T pop(int location){
        MyNode<T> tempCurrent = head;
        MyNode<T> tempPrev = null;
        int tempPosition = 0;
        position--;
        while(tempPosition != location){
            tempPrev = tempCurrent;
            tempCurrent = tempCurrent.next;
            tempPosition++;
        }
        if(tempCurrent == head){
            head = head.next;
            return tempCurrent.data;
        }
        else if(tempCurrent == current){
            current = tempPrev;
            tempPrev.next = tempCurrent.next;
            return tempCurrent.data;
        }
        else{
            tempPrev.next = tempCurrent.next;
            return tempCurrent.data;
        }
    }

    public T pop(){
        return pop(position);
    }
}


class MyNode<T>{
    T data;
    MyNode<T> next;

    MyNode(T data){
        this.data = data;
        next = null;
    }
}
