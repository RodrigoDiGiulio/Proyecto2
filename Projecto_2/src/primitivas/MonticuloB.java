/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivas;

/**
 *
 * @author Ignacio
 */
public class MonticuloB {
    private Documento[] heap;
    private int size;
    private int capacity;

    public MonticuloB(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Documento[capacity + 1];
    }

    private int padre(int pos) {
        return pos / 2;
    }

    private int hijoIzquierdo(int pos) {
        return 2 * pos;
    }

    private int hijoDerecho(int pos) {
        return (2 * pos) + 1;
    }

    private boolean esHoja(int pos) {
        return pos > size / 2 && pos <= size;
    }

    private void intercambiar(int pos1, int pos2) {
        Documento temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    public void insertar(Documento documento) {
        if (size >= capacity) {
            System.out.println("El montículo está lleno.");
            return;
        }
        size++;
        heap[size] = documento;

        int actual = size;

        while (actual > 1 && heap[actual].getPrioridad().compareTo(heap[padre(actual)].getPrioridad()) < 0) {
            intercambiar(actual, padre(actual));
            actual = padre(actual);
        }
    }

    public Documento eliminarMin() {
        if (size == 0) {
            System.out.println("El montículo está vacío.");
            return null;
        }

        Documento raiz = heap[1];
        heap[1] = heap[size];
        size--;
        mantenerMonticuloMinimo(1);

        return raiz;
    }

    private void mantenerMonticuloMinimo(int pos) {
        if (!esHoja(pos)) {
            int hijoIzq = hijoIzquierdo(pos);
            int hijoDer = hijoDerecho(pos);
            int min = pos;

            if (hijoIzq <= size && heap[hijoIzq].getPrioridad().compareTo(heap[min].getPrioridad()) < 0) {
                min = hijoIzq;
            }

            if (hijoDer <= size && heap[hijoDer].getPrioridad().compareTo(heap[min].getPrioridad()) < 0) {
                min = hijoDer;
            }

            if (min != pos) {
                intercambiar(pos, min);
                mantenerMonticuloMinimo(min);
            }
        }
    }
}
