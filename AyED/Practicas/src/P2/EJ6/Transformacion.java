package P2.EJ6;

import P2.EJ1yEJ2.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Transformacion {
    BinaryTree<Integer> root;

    public BinaryTree<Integer> suma(){
        sumaR(root);
        return root;
    }
    private int sumaR(BinaryTree<Integer> root){
        // caso base -> null
        if(root == null) return 0;

        int leftSum = sumaR(root.getLeftChild());
        int rightSum = sumaR(root.getRightChild());

        int actValor = root.getData();
        root.setData(leftSum + rightSum);
        return root.getData() + actValor;
    }

    public void crear_arbol() {
        ArrayList<Integer> datos = new ArrayList<>(Arrays.asList(10, 2, 3, 5, 4, 9, 8, 7, 8, 5, 6, 12, 8, 2, 1));
        ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
        for (Integer x : datos) {
            lista.add(new BinaryTree<>(x));
        }
        for (int i = 0; i < lista.size(); i++) {
            if (2 * i + 1 < lista.size()) {
                lista.get(i).addLeftChild(lista.get(2 * i + 1));
            }
            if (2 * i + 2 < lista.size()) {
                lista.get(i).addRightChild(lista.get(2 * i + 2));
            }
        }
        this.root = lista.get(0);
    }

    public void entreNiveles(int n, int m){
        // es medio un bfs
        if(n < 0 || m < n || root.isEmpty()) return;
        Queue<BinaryTree<Integer>> queue = new LinkedList<>();
        queue.add(this.root);
        int nivel = 0;
        while(!queue.isEmpty()){
            int cantNodosNivel = queue.size();
            for(int i=0; i<cantNodosNivel; i++){
                BinaryTree<Integer> nodoAct = queue.remove();
                if(nivel >= n && nivel <= m){
                    System.out.print( " | "  + nodoAct.getData() +  " | ");
                    if(nodoAct.hasLeftChild()) queue.add(nodoAct.getLeftChild());
                    if(nodoAct.hasRightChild()) queue.add(nodoAct.getRightChild());
                }
                else {
                    if (nodoAct.hasLeftChild()) queue.add(nodoAct.getLeftChild());
                    if (nodoAct.hasRightChild()) queue.add(nodoAct.getRightChild());
                }
            }
            System.out.println(); // salto despues de imprimir el nivel
            nivel++;
        }
    }


    public static void main(String[] args) {
        Transformacion t = new Transformacion();
        t.root = new BinaryTree<Integer>();
        t.crear_arbol();

        t.suma();
        t.entreNiveles(0, 4);


    }
}
