package Parcial1;

import GT.GeneralTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParcialArboles {
    GeneralTree<Integer> tree;

    public List<Integer> nivel(int num){
        List<Integer> ans = new LinkedList<Integer>();
        if(tree != null && !tree.isEmpty()) find_path(tree, ans, num);
        return ans;
    }

    private void find_path(GeneralTree<Integer> node, List<Integer> ans, int cant) {
        Queue<GeneralTree<Integer>> q = new LinkedList<>();
        q.add(node);
        q.add(null);

        boolean si_cumple = false;
        boolean cumple_en_nivel = true;

        while (!q.isEmpty() && !si_cumple) {
            GeneralTree<Integer> ab = q.remove();
            if (ab != null) {
                if (ab.getChildren().size() < cant)
                    cumple_en_nivel = false;
                // agrego la "raiz" digamos -> el dato no los hijos !!!!
                ans.add(ab.getData()); // da igual donde lo agrego porqu en el else voy a eliminarlo si no cumple

                // q.addAll(ab.getChildren());
                for (GeneralTree<Integer> ch : ab.getChildren()) {
                    q.add(ch);
                }
            } else {
                if (cumple_en_nivel) {
                    si_cumple = true;
                } else {
                    ans.clear();
                }
                cumple_en_nivel = true;
                // IMPORTANTE PREGUNTAR EN LA QUEUE PARA NO ENTRAR EN BUCLE
                if (!q.isEmpty()) q.add(null);
            }
        }
    }
}
