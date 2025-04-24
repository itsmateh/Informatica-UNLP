package P3.EJ2;

import P3.EJ1yEJ3.GeneralTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RecorridosAG {

    // ================================
    private void odd_nums_pre(GeneralTree<Integer> tree, int n, List<Integer>ans){
        if(((tree.getData() & 1) != 0) && (tree.getData() > n)) ans.add(tree.getData());
        List<GeneralTree<Integer>> children = tree.getChildren();
        for(GeneralTree<Integer> ch : children) {
            odd_nums_pre(ch, n, ans);
        }
    }

    public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a, Integer n){
        List<Integer> ans = new ArrayList<Integer>();
        odd_nums_pre(a, n, ans);
        return ans;
    }
    // ================================

    private void odd_numbers_post(GeneralTree<Integer> tree, int n, List<Integer> ans) {
        List<GeneralTree<Integer>> children = tree.getChildren();
        for (GeneralTree<Integer> ch : children) {
            odd_numbers_post(ch, n, ans);
        }
        if (((tree.getData() & 1) != 0) && (tree.getData() > n)) ans.add(tree.getData());
    }
    public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n){
        List<Integer> ans = new ArrayList<Integer>();
        odd_numbers_post(a,n,ans);
        return ans;
    }

    // ================================

    private void odd_numbers_in(GeneralTree<Integer> tree, int n, List<Integer> ans){
        List<GeneralTree<Integer>> children = tree.getChildren();
        // obtengo el primer nodo que seria el mas izquierdo
        if(!children.isEmpty()) odd_numbers_in(children.get(0), n, ans);
        // ahora llego a la raiz
        if (((tree.getData() & 1) != 0) && (tree.getData() > n)) ans.add(tree.getData());
        // ahora al resto de los hijos arrancando por el 1 (el 0 fue procesado)
        for(int i=1; i<children.size(); i++){
            odd_numbers_in(children.get(i), n, ans);
        }
    }
    public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n){
        List<Integer> ans = new ArrayList<Integer>();
        odd_numbers_in(a,n,ans);
        return ans;
    }

    // ================================

    private void odd_numbers_lvl(GeneralTree<Integer> tree, int n, List<Integer>ans){
        GeneralTree<Integer> aux;
        Queue<GeneralTree<Integer>> queue = new LinkedList<GeneralTree<Integer>>();
        queue.add(tree);
        while(!queue.isEmpty()) {
            aux = queue.remove();
            if(((aux.getData() & 1) != 0) && (aux.getData() > n)) ans.add(aux.getData());
            // v1
            // queue.addAll(aux.getChildren());
            // v2
            for(GeneralTree<Integer> ch : aux.getChildren()){
                queue.add(ch);
            }
        }
     }
    public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n) {
        List<Integer> ans = new ArrayList<Integer>();
        odd_numbers_lvl(a,n,ans);
        return ans;
    }

    // ================================
    public static void main(String[] args) {

    }
}