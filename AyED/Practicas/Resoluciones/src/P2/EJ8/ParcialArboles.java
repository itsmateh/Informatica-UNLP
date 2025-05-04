package P2.EJ8;

import P2.EJ1yEJ2.BinaryTree;

import java.util.ArrayList;
import java.util.Scanner;

public class ParcialArboles {
    public BinaryTree<Integer> crear_arbol(Scanner sc) {
        int tam = sc.nextInt();
        ArrayList<Integer> posiciones = new ArrayList<>();
        ArrayList<Integer> valores = new ArrayList<>();
        int [] datos = new int[100];
        for(int i = 0; i < tam; i++) posiciones.add(sc.nextInt());
        for(int i = 0; i < tam; i++) valores.add(sc.nextInt());

        for(int i = 0; i < posiciones.size(); i++) {
            datos[posiciones.get(i)] = valores.get(i);
        }
        ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            lista.add(new BinaryTree<>(datos[i]));
        }
        for(int i = 0; i < lista.size(); i++) {
            if(2*i+1 < lista.size() && lista.get(2*i+1).getData() != 0) {
                lista.get(i).addLeftChild(lista.get(2*i+1));
            }
            if(2*i+2 < lista.size() && lista.get(2*i+2).getData() != 0) {
                lista.get(i).addRightChild(lista.get(2*i+2));
            }
        }
        return lista.get(0);
    }

    //====================================================================
    public boolean esPrefijo(BinaryTree<Integer> arbol1, BinaryTree<Integer> arbol2){
        if(arbol1 == null && arbol2 == null) return true;
        if(arbol1 != null && arbol2 == null) return false;
        // considerar si arb1 es null -> prefijo de cualq. cosa
        if(arbol1 == null) return true;
        if(arbol1.getData() != arbol2.getData()) return false;

        // ahora chequeamos tanto para el subarbol izq y der
        boolean arbolIzquierdo = esPrefijo(arbol1.getLeftChild(), arbol2.getLeftChild());
        boolean arbolDerecho = esPrefijo(arbol1.getRightChild(), arbol2.getRightChild());

        return arbolIzquierdo && arbolDerecho;
    }

    public static void main(String[] args) {
        ParcialArboles tree = new ParcialArboles();
        Scanner sc = new Scanner(System.in);
        BinaryTree<Integer> arb1 = tree.crear_arbol(sc);
        BinaryTree<Integer> arb2 = tree.crear_arbol(sc);
        sc.close();

        boolean ans = tree.esPrefijo(arb1, arb2);
        System.out.println(ans);

        /*
        3         // Primer árbol tiene 3 nodos
        0 1 2     // Posiciones de los nodos
        5 3 7     // Valores de los nodos
        4         // Segundo árbol tiene 4 nodos
        0 1 2 3   // Posiciones de los nodos
        5 3 7 9   // Valores de los nodos
        */
    }

}
