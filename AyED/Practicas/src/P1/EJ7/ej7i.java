package P1.EJ7;

import java.util.LinkedList;
import java.util.Scanner;

public class ej7i {
    public static int sumarLinkedList(LinkedList<Integer> lista){
        LinkedList<Integer> l = new LinkedList<Integer>(lista);
        return sumaRecursiva(l);
    }
    private static int sumaRecursiva(LinkedList<Integer> l){
        if(l.isEmpty()) return 0;
        return l.removeFirst() + sumaRecursiva(l);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Tamaño de la lista: ");
        int tam = s.nextInt();
        System.out.println("Escriba los elementos de la lista: ");
        LinkedList<Integer> lista = new LinkedList<Integer>();
        for(int i=0; i<tam; i++){
            lista.add(s.nextInt());
        }
        System.out.print("Su sumatoria " + ej7i.sumarLinkedList(lista));
    }
}
