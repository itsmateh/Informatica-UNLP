package P1.EJ7;

import java.util.ArrayList;
import java.util.Scanner;

public class ej7j {
    public static ArrayList<Integer> combinarOrdenado(ArrayList<Integer> lista1, ArrayList<Integer> lista2){
        ArrayList<Integer> merge = new ArrayList<>(lista1.size() + lista2.size());
        int ind1 = 0; int ind2 = 0;
        while(ind1 < lista1.size() || ind2 < lista2.size()){
            if(ind2 == lista2.size() || ind1 < lista1.size() && lista1.get(ind1) < lista2.get(ind2)){
                merge.add(ind1+ind2, lista1.get(ind1));
                ind1++;
            }else{
                merge.add(ind1+ind2, lista2.get(ind2));
                ind2++;
            }
        }
        return merge;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Tamaño lista 1: ");
        int n = s.nextInt();
        ArrayList<Integer> l1 = new ArrayList<>(n);

        System.out.println("Tamaño lista 2: ");
        int m = s.nextInt();
        ArrayList<Integer> l2 = new ArrayList<>(m);

        System.out.println("Escriba los elementos de la lista 1: ");
        for(int i=0; i<n; i++){
            l1.add(s.nextInt());
        }

        System.out.println("Escriba los elementos de la lista 2: ");
        for(int i=0; i<m; i++){
            l2.add(s.nextInt());
        }

        ArrayList<Integer>l3 = ej7j.combinarOrdenado(l1,l2);
        System.out.println("Elementos combinados: ");
        for(Integer e:l3){
            System.out.print(e + " ");
        }
    }
}
