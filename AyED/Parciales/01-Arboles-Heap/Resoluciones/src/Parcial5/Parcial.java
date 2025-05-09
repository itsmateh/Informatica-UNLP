package Parcial5;

import GT.BinaryTree;

import java.util.LinkedList;
import java.util.List;

public class Parcial {
    public List<Integer> get_list(BinaryTree<Integer> tree, int min){
        List<Integer> ans = new LinkedList<Integer>();
        if(tree != null && !tree.isEmpty()) find_path(tree, ans, new LinkedList<Integer>(), 0, min);
        return ans;
    }

    private boolean find_path(BinaryTree<Integer> tree, List<Integer> ans, List<Integer> curr_path, int cant, int min) {
        // agregamos el nodo a la lista current
        curr_path.add(tree.getData());
        // si fue un nodo par -> cant+1
        if ((tree.getData() & 1) == 0) cant++;
        // ahora puede pasar que este en una hoja o como puede que no. Si estoy en una hoja me interesa saber si encontre un camino
        if (tree.isLeaf()) {
            if (cant >= min) {
                ans.addAll(curr_path);
                return true; // retornamos true y salimos de la iteracion por haber encontrado el camino
            }
        }
        // ahora si no estamos en una hoja, seguimos en los nodos internos por lo que quedan caminos por explorar
        else {
            // si encontramos un camino, retornamos true
            if (tree.hasLeftChild()) {
                if (find_path(tree.getLeftChild(), ans, curr_path, cant, min)) return true;
            }
            if (tree.hasRightChild()) {
                if (find_path(tree.getRightChild(), ans, curr_path, cant, min)) return true;
            }
        }
        // ahora el backtracking, tenemos que sacar el nodo agregado y retornar que no encotramos el camino
        curr_path.remove(curr_path.size() - 1);
        return false;
    }
    //  ver. sin comentarios
    private boolean find_path2(BinaryTree<Integer> tree, List<Integer> ans, List<Integer> curr_path, int cant, int min) {
        curr_path.add(tree.getData());
        if ((tree.getData() & 1) == 0) cant++;
        if (tree.isLeaf()) {
            if (cant >= min) {
                ans.addAll(curr_path);
                return true;
            }
        }
        else {
            if (tree.hasLeftChild()) {
                if (find_path2(tree.getLeftChild(), ans, curr_path, cant, min)) return true;
            }
            if (tree.hasRightChild()) {
                if (find_path2(tree.getRightChild(), ans, curr_path, cant, min)) return true;
            }
        }
        curr_path.remove(curr_path.size() - 1);
        return false;
    }
}
