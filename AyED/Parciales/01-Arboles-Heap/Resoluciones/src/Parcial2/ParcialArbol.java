package Parcial2;
import GT.GeneralTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ParcialArbol{
    GeneralTree<Integer> tree;

    public List<Integer> camino(int num){
        List<Integer> ans = new LinkedList<Integer>();
        if(tree != null && !tree.isEmpty() && tree.getChildren().size() >= num) find_path(tree, ans, new LinkedList<Integer>(), num);
        return ans;
    }

    private void find_path(GeneralTree<Integer> node, List<Integer> ans, List<Integer> curr_path, int num){
        curr_path.add(node.getData());
        // caso base
        if(node.isLeaf()){ans.addAll(curr_path);}
        // si no llegue a una hoja (!caso base)
        else{
            // pregunto si cumple para mandar la recursion por esos hijos
            Iterator<GeneralTree<Integer>> it = node.getChildren().iterator();
            // [8, 42, 5]
            while(it.hasNext() && ans.isEmpty()){
                // aca agarro el 8 despues el 42 y despues el 5
                GeneralTree<Integer> ch = it.next();
                if(ch.getChildren().size() >= num || ch.isLeaf()){find_path(ch, ans, curr_path, num);}
            }
        }
        // backtracking
        curr_path.remove(curr_path.size()-1);
    }

}
