package Parcial4;

import GT.BinaryTree;

public class ParcialArbol {
    private BinaryTree<Integer> root;

    public BinaryTree<Integer> nuevoTree(){
        // incializar ans con la raiz de original !!!!
        BinaryTree<Integer> ans = new BinaryTree<Integer>(root.getData());
        if(root != null && !root.isEmpty()) make_tree(root, ans);
        return ans;
    }
    private void make_tree(BinaryTree<Integer> original, BinaryTree<Integer> copy){
        if(original.hasLeftChild()){
            copy.addLeftChild(new BinaryTree<Integer> (original.getLeftChild().getData() + original.getData()));
            make_tree(original.getLeftChild(), copy.getLeftChild());
        }
        if(original.hasRightChild()){
            copy.addRightChild(new BinaryTree<>(original.getRightChild().getData()));
            make_tree(original.getRightChild(), copy.getRightChild());
        }
    }
}
