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
public class Queue<T> {
    //Atributos
    private MyNode<T> first;
    private MyNode<T> last;
    private MyNode<T> temp;
    int size;
    int maxSize;

    public Queue() {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.maxSize = 0;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public int isSize(){
        return size;
    }
    
    public int isMaxSize(){
        return maxSize;
    }
    
    public void empty(){
        this.first = null;
        this.last = null;
        this.size = 0;
        this.maxSize = 0;
    }
    
    public T peek(){
        if(this.isEmpty()){
            return null;
        }
        else{
            T data = first.getData();
            return data;
        }
    }
    
    public void addQueue(T data){
        MyNode<T> newData = new MyNode(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }else{
            newData.setData(data);
            last.setNext(newData);
            newData.setPrev(last);
            last = newData;

        }
        size++;
    }
    
    public T unQueue(){
        if(this.isEmpty()){
            return null;
        } else {
            temp = first;
            first = first.getNext();
            if (first != null) {
                first.setPrev(null);
            } else {
                last = null;
            }
            size--;
            return temp.getData();
        }
    }
    
    public Queue<T> copyQueue(){
        Queue<T> tempQueue = new Queue<T>();
        Queue<T> copyQueue = new Queue<T>();
        if(this.isEmpty()){
            System.out.println("Stack Vacio");
            return null;
        }else{
            int tempSize = this.size;
            for (int i = 0; i < tempSize; i++) {
                T tempData = this.unQueue();
                System.out.println(tempData);
                tempQueue.addQueue(tempData);
            }
            while(!tempQueue.isEmpty()) {
                T tempData = tempQueue.unQueue();
                this.addQueue(tempData);
                copyQueue.addQueue(tempData);
            }
        }
        return copyQueue;
    }
    
    public void revert(){
        MyNode temp = first;
        MyNode aux = null;
        MyNode prev = null;
        if(this.isEmpty()){
            System.out.println("Error: list is empty.");
        }else{      
            while (temp != null){                    
                aux = temp.getNext();
                temp.setNext(prev);
                prev = temp;
                temp = aux;
            }
            first = prev;
        }
    }
    
    public void print(){
        MyNode temp = first;
        if(this.isEmpty()){
            System.out.println("Error: list is empty.");
        }
        while(temp != null){
//          int, String, byte, long, float, double, boolean, char and short
            Object data = temp.getData();
            //
            //INT INT INT INT INT
            //
            if (data instanceof int[]) {
                int[] dataArray = (int[]) data;
                for (int i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //STRING STRING STRING STRING STRING
            //
            else if (data instanceof String[]) {
                String[] dataArray = (String[]) data;
                for (String i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //BYTE BYTE BYTE BYTE BYTE
            //
            else if (data instanceof byte[]) {
                byte[] dataArray = (byte[]) data;
                for (byte i : dataArray) {
                    System.out.println(i);
                }
            } 
            //
            //LONG LONG LONG LONG LONG
            //
            else if (data instanceof long[]) {
                long[] dataArray = (long[]) data;
                for (long i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //FLOAT FLOAT FLOAT FLOAT FLOAT
            //
            else if (data instanceof float[]) {
                float[] dataArray = (float[]) data;
                for (float i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //DOUBLE DOUBLE DOUBLE DOUBLE DOUBLE
            //
            else if (data instanceof double[]) {
                double[] dataArray = (double[]) data;
                for (double i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //BOOLEAN BOOLEAN BOOLEAN BOOLEAN BOOLEAN
            //
            else if (data instanceof boolean[]) {
                boolean[] dataArray = (boolean[]) data;
                for (boolean i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //CHAR CHAR CHAR CHAR CHAR
            //
            else if (data instanceof char[]) {
                char[] dataArray = (char[]) data;
                for (char i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //SHORT SHORT SHORT SHORT SHORT
            //
            else if (data instanceof short[]) {
                short[] dataArray = (short[]) data;
                for (short i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //ELSE ELSE ELSE ELSE ELSE
            //
            else {
                //TEMP IS MEMORY LOCATION
//                System.out.println(temp);
                System.out.println(data);
            }
            temp = temp.getNext();
        }
    }
    
}
