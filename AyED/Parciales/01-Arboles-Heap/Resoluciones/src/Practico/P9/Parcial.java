package Practico.P9;

import GT.GeneralTree;

import java.util.LinkedList;
import java.util.List;

public class Parcial {
    public List<Integer> Resolucion(GeneralTree<Integer> tree){
        List<Integer> ans = new LinkedList<Integer>();
        if(!tree.isEmpty()) find_path(tree, ans, new LinkedList<Integer>());
        return ans;
    }
    private void find_path(GeneralTree<Integer> node,List<Integer> ans, List<Integer> curr_path){
        // 1-> agregar al curr_path el valor del nodo actual, despues vemos si es util o no.
        curr_path.add(node.getData());
        // 2-> caso base: si llegue a una hoja, veo si tengo que actualizar el maximo camino (ans)
        if(node.isLeaf()){
            if(curr_path.size() > ans.size()){
                ans.clear();
                ans.addAll(curr_path);
            }
        }
        // 3-> si no fue el caso base, sigo recorriendo con los hijos
        else{
            for(GeneralTree<Integer> ch : node.getChildren()){
                find_path(ch, ans, curr_path);
            }
        }
        // 4-> backtracking, elimino el ultimo nodo que agregue.
        curr_path.remove(curr_path.size()-1);
    }

}
