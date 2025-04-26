package P3.EJ7;
import P3.EJ1yEJ3yEJ5.GeneralTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Caminos {

    GeneralTree<Integer> root;
    public List<Integer> caminoAHojaMasLejana(){
        List<Integer> curr_path = new LinkedList<Integer>();
        List<Integer> longest_path = new LinkedList<Integer>();
        if(root != null && !root.isEmpty()) path(root, curr_path, longest_path);
        return longest_path;
    }
    // no retorno nada, solamente agrego a una lista
    private void path(GeneralTree<Integer> node, List<Integer> curr_path, List<Integer> longest_path){
        // agrego el nodo a la lista
        curr_path.add(node.getData());
        // veo si es el caso base. Si lo es -> comparo ambas listas y actualizo si es necesario
        if(node.isLeaf()){
            if(longest_path.size() < curr_path.size())
                longest_path.clear();
                longest_path.addAll(curr_path);
        }
        // si no es el caso base -> recorro el arbol normalmente
        else{
            for(GeneralTree<Integer> ch : node.getChildren()){
                path(ch, curr_path, longest_path);
            }
        }
        // backtracking: aca elimino los nodos de curr_path que no me sirven de forma recursiva
        curr_path.remove(curr_path.size() - 1);
    }

}
