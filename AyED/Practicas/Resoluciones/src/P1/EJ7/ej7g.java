package P1.EJ7;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ej7g {
    public static List<Integer> sucesionCollatz(int n){
        List<Integer> lista = new ArrayList<Integer>();
        sucesionRecursiva(lista, n);
        return lista;
    }

    private static void sucesionRecursiva(List<Integer> l, int n){
        l.add(n);
        if(n>1){
            if(n%2 == 0){sucesionRecursiva(l, n/2);}
            else{sucesionRecursiva(l, n*3+1);}
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Numero N:  ");
        int N = s.nextInt();
        List<Integer> sucesion = ej7g.sucesionCollatz(N);
        System.out.println("Su sucesion de Collatz: ");
        System.out.println(sucesion.size());
        for(Integer n:sucesion){
            System.out.print(n + " ");
        }
    }
}
