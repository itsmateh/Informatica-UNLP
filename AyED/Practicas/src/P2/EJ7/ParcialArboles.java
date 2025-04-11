package P2.EJ7;

import P2.EJ1yEJ2.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
<<<<<<< HEAD
import java.util.Scanner;

public class ParcialArboles {

        private BinaryTree<Integer> root;

 // ============================= AUXILIARES =============================
=======

public class ParcialArboles {
    BinaryTree<Integer> root;

    // ==================================================================
>>>>>>> 244640d89b00df479a3e9c8d3d597d9f9b093834
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
<<<<<<< HEAD
            queue.add(this.root);
=======
            queue.add(root);
>>>>>>> 244640d89b00df479a3e9c8d3d597d9f9b093834
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
<<<<<<< HEAD
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
=======

    // ==================================================================


    private int childs(BinaryTree<Integer> node){
        int cant = 0;
        if(node.hasLeftChild()) cant += childs(node.getLeftChild());
        if(node.hasRightChild()) cant += childs(node.getRightChild());

        // condicion
        if(node.hasLeftChild() && !node.hasRightChild() || node.hasRightChild() && !node.hasLeftChild()) cant++;
        return cant;
    }


    private BinaryTree<Integer> findNode(BinaryTree<Integer> node, int val){
        if(node.getData() == val) return node;

        BinaryTree<Integer> nextnode = new BinaryTree<Integer>(); // esto es lo que retorno
        if(node.hasLeftChild()) nextnode = findNode(node.getLeftChild(), val);
        if(node.hasRightChild()) nextnode = findNode(node.getRightChild(), val);
        return nextnode;
    }

    public boolean isLeftTree(int n){
        BinaryTree<Integer> currNode = findNode(root, n); //una vez parados en el nodo y tomarlo de raiz

        // si tiene hijos el nodo actual (izq y der) los sumamos
        int cantL = 0; int cantR = 0;
        if(currNode.hasLeftChild()) cantL += childs(currNode.getLeftChild());
        if(currNode.hasRightChild()) cantR += childs(currNode.getRightChild());

        return cantL > cantR;
    }

    public static void main(String[] args) {
        ParcialArboles tree = new ParcialArboles();
        tree.crear_arbol();
        System.out.println(tree.isLeftTree(-5));
        tree.entreNiveles(0,10);
    }

>>>>>>> 244640d89b00df479a3e9c8d3d597d9f9b093834
}
