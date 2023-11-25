/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author RDG
 * @param <T>
 */
public class MyNode<T> {
    //Atributos
    private MyNode next;
    private MyNode prev;
    private T data;
    private Object element;
    
    public MyNode(Object elemento){
        this.next = null;
        this.prev = null;
        this.data = null;
        this.element = elemento;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the element to set
     */
    public void setData(T data) {
        this.data = data;
    }
    
    //Esto es un intento
    /**
     * @return the next
     */
    public MyNode getPrev() {
        return prev;
    }

    /**
     * @param prev the previous to set
     */
    public void setPrev(MyNode prev) {
        this.prev = prev;
    }
    //Esto es el fin del intento
    
    /**
     * @return the next
     */
    public MyNode getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(MyNode next) {
        this.next = next;
    }

    /**
     * @return the element
     */
    public Object getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(Object element) {
        this.element = element;
    }
}
