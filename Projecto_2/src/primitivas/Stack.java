/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 * La clase stack y sus primitivas
 * @author RDG
 * @param <T>
 * @version 10/16/2023
 */
public class Stack<T> {
    //Atributos
    private Node<T> head;
    private Node<T> tail;
    private Node<T> temp;
    private int size;
    private int maxStackValue;

    public Stack() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.maxStackValue = 0;
    }
    
    public boolean isEmpty(){
        return head == null;
    }
    
    public int isSize(){
        return size;
    }
    
    public void empty(){
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.maxStackValue = 0;
    }
    
    public T peek(){
        if(this.isEmpty()){
            return null;
        }
        else{
            T data = head.getData();
            return data;
        }
    }
    
    public void push(T data){
        Node<T> newData = new Node(data);
        if(this.isEmpty()){
            newData.setData(data);
            head = newData;
        }else{
            newData.setData(data);
            newData.setNext(head);
            head = newData;
        }
        size++;
    }
    
    public T pop(){
        if(this.isEmpty()){
            return null;
        }else{
            temp = head;
            head = head.getNext();
            temp.setNext(null);
        }
        size--;
        return temp.getData();
    }

    public Stack<T> copyStack(){
        Stack<T> tempStack = new Stack<T>();
        Stack<T> copyStack = new Stack<T>();
        if(this.isEmpty()){
            System.out.println("Stack Vacio");
            return null;
        }else{
            int tempSize = this.size;
            for (int i = 0; i < tempSize; i++) {
                T tempData = this.pop();
                System.out.println(tempData);
                tempStack.push(tempData);
            }
            while(!tempStack.isEmpty()) {
                T tempData = tempStack.pop();
                this.push(tempData);
                copyStack.push(tempData);
            }
        }
        return copyStack;
    }
    
    public void revert(){
        Node temp = head;
        Node aux = null;
        Node prev = null;
        if(this.isEmpty()){
            System.out.println("Error: list is empty.");
        }else{      
            while (temp != null){                    
                aux = temp.getNext();
                temp.setNext(prev);
                prev = temp;
                temp = aux;
            }
            head = prev;
        }
    }
    
    public void print() {
        Stack<T> tempStack = new Stack<T>();
        if(this.isEmpty()) {
            System.out.println("Stack Vacio");
        } else {
            int tempSize = this.size;
            for (int i = 0; i < tempSize; i++) {
                T tempData = this.pop();
                System.out.println(tempData);
                tempStack.push(tempData);
            }
            // Restore the original stack
            while(!tempStack.isEmpty()) {
                this.push(tempStack.pop());
            }
        }
}
}