package P3;

import P3.EJ1yEJ3yEJ5.GeneralTree;

import java.util.Iterator;


public class ej9remake {

    private class Minimo{
        private int val;

        public Minimo(int val){this.val = val;}
        public void setVal(int new_val){this.val = new_val;}
        public int getVal(){return this.val;}
    }

    public int min_tree(GeneralTree<Integer> tree){
        Minimo m1 = new Minimo(9999);
        if(tree != null && !tree.isEmpty()) find_min(tree, m1);
        return m1.getVal();
    }

    private void find_min(GeneralTree<Integer> node, Minimo m1){
        m1.setVal(Math.min(m1.getVal(), node.getData()));
        for(GeneralTree<Integer> ch : node.getChildren()){
            find_min(ch, m1);
        }
    }


    public static boolean esDeSeleccion(GeneralTree<Integer> tree){
        // que retornar si el arbol esta vacio / null
        return find_path(tree, tree.getData());
    }

    private static boolean find_path(GeneralTree<Integer> node, int father_value){
        boolean check = true;
        int node_value = node.getData();

        // 1ro me fijo si es el caso base
        // y este resultado tengo que propagarlo por el arbol
        if(node.isLeaf()){
            // va estar en true el chequeo hasta que demuestre lo contrario
            if(father_value > node_value) check = false;
        }

        // 3ro
        else{
            Iterator<GeneralTree<Integer>> it = node.getChildren().iterator();
            while(it.hasNext() && check){
                check = find_path(it.next(), node_value);
            }
        }

        // 2do
        return check;
    }

}
