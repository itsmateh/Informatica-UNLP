package P2.EJ3;

import P2.EJ1yEJ2.BinaryTree;
import java.util.ArrayList;
import java.util.List;


public class ContadorArbol {
    private BinaryTree<Integer> bt;

    public ContadorArbol(BinaryTree<Integer> bt) {
        this.bt = bt;
    }

    // A
    public List<Integer> evenNumbersInorder(){
        List<Integer> list = new ArrayList<>();
        listEvenNumbersInorder(bt, list);
        return list;
    }
    private void listEvenNumbersInorder(BinaryTree<Integer> bt, List<Integer>list){
        if(bt == null || bt.isEmpty()) return; // caso base;
        // inorder -> 1- izq. 2- NODO. 3- der
        listEvenNumbersInorder(bt.getLeftChild(), list); // 1
        if(bt.getData() % 2 == 0) list.add(bt.getData()); // 2
        listEvenNumbersInorder(bt.getRightChild(), list); // 3
    }

    // B
    public List<Integer> evenNumbersPostorder(){
        List<Integer> list = new ArrayList<>();
        listEvenNumbersPostorder(bt, list);
        return list;
    }
    private void listEvenNumbersPostorder(BinaryTree<Integer> bt, List<Integer>list){
        if(bt == null || bt.isEmpty()) return; // caso base;
        // postorder -> 1- izq. 2- der. 3- NODO
        listEvenNumbersPostorder(bt.getLeftChild(), list); // 1
        listEvenNumbersPostorder(bt.getRightChild(), list); // 2
        if(bt.getData() % 2 == 0) list.add(bt.getData()); // 3
    }


    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<Integer>(10);

        bt.addLeftChild(new BinaryTree<Integer>(5));
        bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(3));
        bt.getLeftChild().addRightChild(new BinaryTree<Integer>(4));

        bt.addRightChild(new BinaryTree<Integer>(15));
        bt.getRightChild().addLeftChild(new BinaryTree<Integer>(12));
        bt.getRightChild().addRightChild(new BinaryTree<Integer>(20));

        ContadorArbol contArbol = new ContadorArbol(bt);

        // test
        List<Integer> v1 = contArbol.evenNumbersInorder();
        List<Integer> v2 = contArbol.evenNumbersPostorder();

        for(Integer v : v1) System.out.println(v + " ");
        System.out.println("----");
        for(Integer v : v2) System.out.println(v + " ");
    }
}
