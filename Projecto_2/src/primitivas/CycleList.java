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
public class CycleList<T> {
    //Atributos
    private MyNode<T> first;
    private MyNode<T> last;
    private MyNode<T> aux;
    private MyNode<T> next;
    private MyNode<T> prev;
    private MyNode<T> temp;
    private MyNode<T> loopStart;
    private MyNode<T> loopEnd;
    private int returnedPos;
    private int size;
    
    public CycleList(){
        this.first = null;
        this.last = null;
        this.size = 0;
        this.loopStart = null;
        this.loopEnd = null;
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
    this.loopStart = null;
    this.loopEnd = null;
    this.size = 0;
    }
    
    public void addStart(T data){
//        System.out.println("ARG");
//        System.out.println(loopStart);
        MyNode<T> newData = new MyNode(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }else{
            if (loopStart == first){
                newData.setData(data);
                newData.setNext(first);
                first.setPrev(newData);
                first = newData;
                loopStart = first;
            }else{
                newData.setData(data);
                newData.setNext(first);
                first.setPrev(newData);
                first = newData;
//            System.out.println("newData " + newData);
            }
        }
        if (loopStart == null){
//            System.out.println("NULL START");
            loopStart = first;
        }
        size++;
    }
    
    public void addEnd(T data){
        MyNode<T> newData = new MyNode(data);
//        System.out.println("Data "+ data);
//        System.out.println("NewData "+ newData);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }else{
            if (loopEnd == last){
                newData.setData(data);
                last.setNext(newData);
                newData.setPrev(last);
                last = newData;
                loopEnd = last;
            }else{
                newData.setData(data);
                last.setNext(newData);
                newData.setPrev(last);
                last = newData;
            }
        }
        if (loopEnd == null){
//            System.out.println("NULL END");
            loopEnd = last;
            }
        size++;
    }
    
    public void addPos(int pos,T data){
        MyNode<T> newData = new MyNode(data);
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
        MyNode<T> newData = new MyNode(data);
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
        MyNode<T> newData = new MyNode(data);
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
        MyNode<T> newData = new MyNode(data);
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
    
    public void startLoopStart(){
        if(this.isEmpty()){
            System.out.println("CycleList is empty");
        }else{
            loopStart = first;
        }
    }
    
    public void startLoopEnd(){
        if(this.isEmpty()){
            System.out.println("CycleList is empty");
        }else{
            loopStart = last;
        }
    }
    
    public void startLoopPos(int pos){
        if(this.isEmpty()){
            System.out.println("CycleList is empty");
        }
        else if (pos == 0){
            this.startLoopStart();
        }
        else if (pos == (this.isSize() - 1)){
            this.startLoopEnd();
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
            int i = 0;
            aux = first;
            while (i != pos){
                aux = aux.getNext();
                i++;
            }
            loopStart = aux;
        }
    }
    
    public void endLoopStart(){
        if(this.isEmpty()){
            System.out.println("CycleList is empty");
        }else{
            loopEnd = first;
        }
    }
    
    public void endLoopEnd(){
        if(this.isEmpty()){
            System.out.println("CycleList is empty");
        }else{
            loopEnd = last;
        }
    }
    
    public void endLoopPos(int pos){
        if(this.isEmpty()){
            System.out.println("CycleList is empty");
        }
        else if (pos == 0){
            this.endLoopStart();
        }
        else if (pos == (this.isSize() - 1)){
            this.endLoopEnd();
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
            int i = 0;
            aux = first;
            while (i != pos){
                aux = aux.getNext();
                i++;
            }
            loopEnd = aux;
        }
    }
    
    public int searchLoopStart(){
//        System.out.println(loopStart);
//        System.out.println(first);
        if(!this.isEmpty()){
            boolean stop = false;
            int i = 0;
            aux = first;
            while (stop != true){
                if (aux == loopStart){
//                    System.out.println("STOP");
                    stop = true;
                }
                else{
                    aux = aux.getNext();
//                    System.out.println(i);
//                    System.out.println("I");
                    i++;
                }
            }
            returnedPos = i;
            return returnedPos;
        }
        else{
            System.out.println("Error: List is empty");
            return -1;
        }
    }
    
    public int searchLoopEnd(){
        if(!this.isEmpty()){
            boolean stop = false;
            int i = 0;
            aux = first;
            while (stop != true){
                if (aux == loopEnd){
                    stop = true;
                }
                else{
                    aux = aux.getNext();
                    i++;
                }
            }
            returnedPos = i;
            return returnedPos;
        }
        else{
            System.out.println("Error: List is empty");
            return -1;
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
