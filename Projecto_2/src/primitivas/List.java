/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

//import java.util.HashSet;

/**
 *
 * @author RDG
 * @param <T>
 */
public class List<T>{
    //Atributos
    private Node<T> first;
    private Node<T> last;
    private Node<T> aux;
    private Node<T> next;
    private Node<T> prev;
    private Node<T> temp;
    private int size;
    
    public List(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public int isSize(){
        return size;
    }
    
    public void empty(){
        this.first = null;
        this.last = null;
        this.aux = null;
        this.next = null;
        this.prev = null;
        this.temp = null;
        this.size = 0;
    }
    
    public void addStart(T data){
        Node<T> newData = new Node(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }else{
            newData.setData(data);
            newData.setNext(first);
            first.setPrev(newData);
            first = newData;
//            System.out.println("newData " + newData);
        }
        size++;
    }
    
    public void addEnd(T data){
        Node<T> newData = new Node(data);
//        System.out.println("Data "+ data);
//        System.out.println("NewData "+ newData);
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
    
    public void addPos(int pos,T data){
        Node<T> newData = new Node(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }
        else if (pos == 0){
            this.addStart(data);
        }
        else if (pos == (this.isSize() - 1)){
            this.addEnd(data);
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            prev = aux;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                prev = aux;
                aux = aux.getNext();
                i++;
            }
            newData.setData(data);
//            System.out.println("Aux "+aux);
//            System.out.println("Prev "+prev);
            
            prev.setNext(newData);
//            System.out.println("Next de Prev "+prev.getNext());
            
            newData.setNext(aux);
//            System.out.println("Next de NewData "+newData.getNext());
            
            aux.setPrev(newData);
//            System.out.println("Prev de Aux "+aux.getPrev());
            
            newData.setPrev(prev);
//            System.out.println("Prev de NewData "+newData.getPrev());
            
            size++;
        }
    }
    
    public void delStart(){
        if(!this.isEmpty()){
            if (size == 1) {
                this.empty();
            }
            else{
                first = first.getNext();
                first.setPrev(null);
                
                size--;
            }
        }
    }
    
    public void delEnd(){
        if(!this.isEmpty()){
            if (size == 1){
                this.empty();
            }
            else{
                last = last.getPrev();
                last.setNext(null);
                size--;
            }
        }
    }
    
    public void delPos(int pos){
        if(this.isEmpty()){
            first = last = null;
        }
        else if (pos == 0){
            this.delStart();
        }
        else if (pos == (this.isSize() - 1)){
            this.delEnd();
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            prev = aux;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                prev = aux;
                aux = aux.getNext();
                i++;
            }
            prev.setNext(aux.getNext());
            aux.setPrev(null);
            temp = aux.getNext();
            aux.setNext(null);            
            temp.setPrev(prev);
            
            size--;
        }
    }
    
    public void replaceStart(T data){
        Node<T> newData = new Node(data);
        if(!this.isEmpty()){
            if (size == 1) {
                newData.setData(data);
                first = last = newData;
            }
            else{
                first.setData(data);
            }
        }
    }
    
    public void replaceEnd(T data){
        Node<T> newData = new Node(data);
        if(!this.isEmpty()){
            if (size == 1){
                newData.setData(data);
                first = last = newData;
            }
            else{
                last.setData(data);
            }
        }
    }
    
    public void replacePos(int pos, T data){
        Node<T> newData = new Node(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }
        else if (pos == 0){
            this.replaceStart(data);
        }
        else if (pos == (this.isSize() - 1)){
            this.replaceEnd(data);
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                aux = aux.getNext();
                i++;
            }
            aux.setData(data);
        }
    }
    
    public T searchStart(){
        if(!this.isEmpty()){
            T data = first.getData();
            return data;
        }
        else{
            System.out.println("Error: List is empty");
            return null;
        }
    }
    
    public T searchEnd(){
        if(!this.isEmpty()){
            T data = last.getData();
            return data;
        }
        else{
            System.out.println("Error: List is empty");
            return null;
        }
    }
    
    public T searchPos(int pos){
        T data = null;
        if(this.isEmpty()){
            System.out.println("Error: List is empty");
            return null;
        }
        else if (pos == 0){
            data = this.searchStart();
        }
        else if (pos == (this.isSize() - 1)){
            data = this.searchEnd();
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                aux = aux.getNext();
                i++;
            }
            data = aux.getData();
            return data;
        }
        return data;
    }

// DO NOT DELETE, JUST IN CASE
    
//    public void print(){
//        Node temp = first;
//        if(this.isEmpty()){
//            System.out.println("Error: list is empty.");
//        }
//        while(temp != null){
////            data = temp.getData();
////            for (int i : data){
//                System.out.println(temp);
//                System.out.println(temp.getData());
//                temp = temp.getNext();
//            }
//        }
    
    public void revert(){
        Node temp = first;
        Node aux = null;
        Node prev = null;
        if(this.isEmpty()){
            System.out.println("Error: list is empty.");
        }else{      
            System.out.println("PRE");
            System.out.println("TEMP " + temp);
            System.out.println("aux " + aux);
            while (temp != null){

//                    aux = temp;
//                    temp = temp.getNext();
//                    aux.setNext(temp);
//                    temp.setPrev(aux);
                    
                    aux = temp.getNext();
                    temp.setNext(prev);
                    prev = temp;
                    temp = aux;


                    System.out.println("POS2");
                    System.out.println("TEMP " + temp);
                    System.out.println("Aux " + aux);
            }
            first = prev;
        }
    }

    public void print(){
        Node temp = first;
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