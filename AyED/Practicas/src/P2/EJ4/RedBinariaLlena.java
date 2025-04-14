package P2.EJ4;

import P2.EJ1yEJ2.BinaryTree;
import java.util.ArrayList;
import java.util.Arrays;

public class RedBinariaLlena {
    BinaryTree<Integer> root;

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



    public int retardoReenvio(){
        if(root == null || root.isEmpty()) return 0;

        return retardoReenvio(root);
    }

    private int retardoReenvio(BinaryTree<Integer> bt){
        int maximo = bt.getData();

        int maxLeftChild = (bt.hasLeftChild() ? retardoReenvio(bt.getLeftChild()) : 0);
        int maxRightChild = (bt.hasRightChild() ? retardoReenvio(bt.getRightChild()) : 0);

        maximo += Math.max(maxLeftChild, maxRightChild);
        return maximo;
    }

    public static void main(String[] args) {
        RedBinariaLlena red = new RedBinariaLlena();
        red.crear_arbol();
        System.out.println(red.retardoReenvio(red.root));
    }

}