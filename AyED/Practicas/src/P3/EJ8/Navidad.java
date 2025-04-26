package P3.EJ8;

import P3.EJ1yEJ3yEJ5.GeneralTree;

public class Navidad {
    GeneralTree<Integer> root;

    public String esAbetoNavidenio(){
        boolean ans = abeto(root);
        return ans ? "yes" : "no";
    }
    private boolean abeto(GeneralTree<Integer> node) {
        int nodos = 0;
        for(GeneralTree<Integer> ch : node.getChildren()) {
            if(ch.isLeaf()) nodos++;
            else if(!abeto(ch)) return false;
        }
        return nodos >= 3;
    }
}
