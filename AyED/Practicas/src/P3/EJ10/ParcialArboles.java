package P3.EJ10;

import P3.EJ1yEJ3yEJ5.GeneralTree;

import java.util.LinkedList;
import java.util.List;

public class ParcialArboles {
    public static List<Integer> resolver(GeneralTree<Integer> arbol){
        List<Integer> ans = new LinkedList<Integer>();
        List<Integer> curr_path = new LinkedList<Integer>();
        int level = 0;
        if(arbol != null && !arbol.isEmpty())  find_path(arbol, ans, curr_path, level);
        return ans;
    }
    private static int find_path(GeneralTree<Integer> node, List<Integer> ans,
                                 List<Integer> curr_path,
                                 int nivel
                                 /* mas parametros*/)
    {
        return 0;
    }

}
