package Parcial8;

import GT.GeneralTree;

import java.util.Iterator;

public class Parcial {
    public static boolean esDeSeleccion(GeneralTree<Integer> arbol){
        if(arbol != null && !arbol.isEmpty()) return find_ans(arbol, arbol.getData());
        return false;
    }
    private static boolean find_ans(GeneralTree<Integer> node, int father_value){
        // en problemas donde hay que cortar cuando ya no se cumple una condicion, usar un boolean en true
        boolean check = true;
        int curr_value = node.getData();

        // el menor de los hijos -> el valor de la menor hoja de su descendencia
        if(node.isLeaf()){
            if(curr_value < father_value){
                check = false;
                return check;
            }
            else{return check;}
        }
        else{
            Iterator<GeneralTree<Integer>> it = node.getChildren().iterator();
            while(check && it.hasNext()){
                check = find_ans(it.next(), curr_value);
            }
        }
        return check;
    }
}
