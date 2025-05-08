package P3.EJ9;
import P3.EJ1yEJ3yEJ5.GeneralTree;

import java.util.Iterator;

public class ParcialArboles {

    public static boolean esDeSeleccion(GeneralTree<Integer> arbol){
        int min_value = arbol.getData();
        boolean ans = solve(arbol, min_value);
        return ans;
    }

   /* private static boolean solve(GeneralTree<Integer> node, int father_min){
        boolean check = false;
        int curr_min = node.getData();
        if(node.isLeaf()){
            if(curr_min  father_min){
                check = false;
            }
        }
        else{
            for(GeneralTree<Integer> ch : node.getChildren()){
                check = solve(ch, curr_min);
            }
        }
        return check;
    } */

    private boolean find_path(GeneralTree<Integer> node, int father_value){
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
