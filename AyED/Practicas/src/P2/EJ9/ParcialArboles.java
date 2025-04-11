package P2.EJ9;

import P2.EJ1yEJ2.BinaryTree;

public class ParcialArboles {

    private void makeTree(BinaryTree<Integer> arbol, BinaryTree<Node> bt, int father, int sum){
        int currValue = arbol.getData();
        Node node = new Node(sum + currValue, currValue - father);
        bt.setData(node);

        if(arbol.hasLeftChild()){
            bt.addLeftChild(new BinaryTree<Node>()); // creamos el subarbol izq
            makeTree(arbol.getLeftChild(), bt.getLeftChild(), currValue, sum+currValue);
        }
        if(arbol.hasRightChild()){
            bt.addRightChild(new BinaryTree<Node>());
            makeTree(arbol.getRightChild(), bt.getRightChild(), currValue, sum+currValue);
        }
    }

    public BinaryTree<Node> sumAndDif(BinaryTree<Integer> root){
        BinaryTree<Node> bt = new BinaryTree<Node>();
        if(!root.isEmpty()){
            this.makeTree(root, bt, 0,0);
        }
        return bt;
    }

    public static void main(String[] args) {
        ParcialArboles ej9 = new ParcialArboles();

        BinaryTree<Integer> tree = new BinaryTree<>(20);

        tree.addLeftChild(new BinaryTree<Integer>(5));
        tree.addRightChild(new BinaryTree<Integer>(30));

        tree.getLeftChild().addLeftChild(new BinaryTree<Integer>(-5));
        tree.getLeftChild().addRightChild(new BinaryTree<Integer>(10));
        tree.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(1));

        tree.getRightChild().addLeftChild(new BinaryTree<Integer>(50));
        tree.getRightChild().addRightChild(new BinaryTree<Integer>(-9));
        tree.getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(4));
        tree.getRightChild().getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(6));

        System.out.println("Arbol original: "); tree.entreNiveles(0,4);
        System.out.println("Arbol SumAndDif: "); ej9.sumAndDif(tree).entreNiveles(0,4);

    }
}
