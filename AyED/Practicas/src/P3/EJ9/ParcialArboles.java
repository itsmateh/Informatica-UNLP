package P3.EJ9;

import P3.EJ1yEJ3yEJ5.GeneralTree;

public class ParcialArboles {

    public static boolean esDeSeleccion(GeneralTree<Integer> arbol){
        int min_value = arbol.getData();
        boolean ans = solve(arbol, min_value);
        return ans;
    }

    private static boolean solve(GeneralTree<Integer> node, int father_min){
        boolean check = true;
        int curr_min = node.getData();
        if(node.isLeaf()){
            if(curr_min < father_min){
                check = false;
            }
        }
        else{
            for(GeneralTree<Integer> ch : node.getChildren()){
                check = solve(ch, curr_min);
            }
        }
        return check;
    }
}
