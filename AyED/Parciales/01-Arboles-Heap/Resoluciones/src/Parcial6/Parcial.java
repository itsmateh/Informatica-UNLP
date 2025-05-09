package Parcial6;

import GT.BinaryTree;

public class Parcial {
    BinaryTree<Integer> tree;
    public Parcial(BinaryTree<Integer> arbol){
        this.tree = arbol;
    }

    public boolean resolver(int k){
        if(tree != null && !tree.isEmpty()) return ans(this.tree, k, 0);
        return false;
    }
    private boolean ans(BinaryTree<Integer>node, int k, int curr_sum){
        boolean check = true;
        curr_sum+=node.getData();
        if(node.isLeaf()){
            if(curr_sum != k) {
                check = false;
                return check;
            }
            else {return check;}
        }
        else{
            if(curr_sum > k){
                check = false;
                return check;
            }
            if(check){
                if(check && node.hasLeftChild()) check = ans(node.getLeftChild(), k, curr_sum);
                if(check && node.hasRightChild()) check = ans(node.getRightChild(), k, curr_sum);
            }
        }
        return check;
    }
    public static void main(String args[]) {
        BinaryTree<Integer> ab = new BinaryTree<Integer>(2);

        ab.addLeftChild(new BinaryTree<Integer>(1));
        ab.addRightChild(new BinaryTree<Integer>(2));

        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(4));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(2));

        ab.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(1));

        ab.getRightChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(3));
        ab.getRightChild().getRightChild().addLeftChild(new BinaryTree<Integer>(2));


        Parcial a = new Parcial(ab);
        System.out.println(a.resolver(8));
    }
}

