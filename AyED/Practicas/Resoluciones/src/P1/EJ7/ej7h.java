package P1.EJ7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ej7h {
    public static void invertirArrayList(ArrayList<Integer> lista){
        List<Integer> arr = new ArrayList<Integer>();
        int tam = lista.size()-1;
        for(int i=tam; i>=0; i--){
            arr.add(lista.get(i));
        }
        lista.clear(); lista.addAll(arr);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Tamaño lista: ");
        int N = s.nextInt();
        ArrayList<Integer> lista = new ArrayList<>();
        System.out.println("Escriba " + N + " numeros: ");
        for(int i=0; i<N; i++){
            lista.add(s.nextInt());
        }
        invertirArrayList(lista);
        System.out.println("Lista modificada!");
        for(Integer n:lista){
            System.out.print(n + " ");
        }
    }
}
