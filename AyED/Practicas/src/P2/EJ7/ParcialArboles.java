package P2.EJ7;

import P2.EJ1yEJ2.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ParcialArboles {

        private BinaryTree<Integer> root;

 // ============================= AUXILIARES =============================
        public void crear_arbol() {
            int [] datos = new int[100];
            int [] posiciones = {0, 1, 2, 3, 4, 5, 7, 9, 10, 12, 25};
            int [] valores = {2, 7, -5, 23, 6, 19, -3, 55, 11, 4, 18};
            for(int i = 0; i < posiciones.length; i++) {
                datos[posiciones[i]] = valores[i];
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
            root = lista.get(0);
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
 // ==========================================================================

        private int countChilds(BinaryTree<Integer> currNode){
            int count = (currNode.hasLeftChild() ^ currNode.hasRightChild()) ? 1 : 0;
            if(currNode.hasLeftChild()) count  = count + countChilds(currNode.getLeftChild());
            if(currNode.hasRightChild()) count = count + countChilds(currNode.getRightChild());

            return 0;
        }

        private BinaryTree<Integer> findNode(BinaryTree<Integer> node, int val){
            if(node == null) return null;
            if(node.getData() == val) return node;

            BinaryTree<Integer> currNode;
            currNode = findNode(node.getLeftChild(), val);

            if(currNode != null) return currNode;

            currNode = findNode(node.getRightChild(), val);
            return currNode;
        }

        public boolean isLeftTree(int num){
            BinaryTree<Integer> currNode = findNode(root, num);
            if(currNode == null) return false;

            // contadores y respuesta
            int countLeft = -1; int countRight = -1;
            if(currNode.hasLeftChild()){
                countLeft = countChilds(currNode.getLeftChild());
            }
            if(currNode.hasRightChild()) {
                countRight = countChilds(currNode.getRightChild());
            }

            return countLeft > countRight;
        }

    public static void main(String[] args) {
        ParcialArboles bt = new ParcialArboles();
        bt.crear_arbol();

        bt.entreNiveles(0, 100);

        System.out.println("Nodo a revisar: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        System.out.println(bt.isLeftTree(n));

    }
}
