package P2.EJ5;

import P2.EJ1yEJ2.BinaryTree;

import java.util.*;

public class ProfundidadDeArbolBinario {
    BinaryTree<Integer> bt;

    public int sumaElementosProfundidad(int p){
        if(bt == null || bt.isEmpty()) return 0;
        Queue<BinaryTree<Integer>> queue = new LinkedList<>();
        queue.add(bt);
        int nivel = 0;
        int suma = 0;
        while(!queue.isEmpty()){
            int cantNodosNivel = queue.size();
            for(int i=0; i<cantNodosNivel; i++){
                BinaryTree<Integer> nodoAct = queue.remove();
                if(nivel == p) suma+= nodoAct.getData();
                else{
                    if(nodoAct.hasLeftChild()) queue.add(nodoAct.getLeftChild());
                    if(nodoAct.hasRightChild()) queue.add(nodoAct.getRightChild());
                }
            }
            // si ya tenemos la suma retornamos de una
            if(nivel == p) return suma;

            nivel++;
        }
        return 0;
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
        this.bt = lista.get(0);
    }

    public static void main(String[] args) {
        ProfundidadDeArbolBinario btP = new ProfundidadDeArbolBinario();
        btP.crear_arbol();

        System.out.println("Indicar un nivel de profundida: ");
        Scanner s = new Scanner(System.in);
        int p = s.nextInt();
        System.out.println(btP.sumaElementosProfundidad(p));
    }

}
