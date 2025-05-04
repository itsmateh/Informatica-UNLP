package P3.EJ11;


import P3.EJ1yEJ3yEJ5.GeneralTree;
import java.util.LinkedList;
import P1.EJ8.Queue;

public class ParcialArboles {

    public static boolean resolver(GeneralTree<Integer> arbol){
        boolean ans = solve(arbol);
        return ans;
    }

    private static boolean solve(GeneralTree<Integer> root){
        int curr_nodes = 1;
        int prev_nodes = 0;
        boolean ans = true;
        // la propiedad queue.size() te aligera el problema bastante
        Queue<GeneralTree<Integer>> queue = new Queue<GeneralTree<Integer>>();
        queue.enqueue(root);
        while(!queue.isEmpty()){
            // primero evaluamos si se cumple la condicion, ya que si no se cumple cortamos de una
            if(curr_nodes != prev_nodes+1){
                ans = false;
                break;
            }
            // si cumple, agregamos los hijos del nodo actual a la queue
            else{
                GeneralTree<Integer> node = queue.dequeue();
                for(GeneralTree<Integer> ch : node.getChildren()){
                    queue.enqueue(ch);
                }
            }

            // y no nos olvidemos de actualizar ya que paso la condicion de corte
            prev_nodes = curr_nodes;
            curr_nodes = queue.size();
        }
        return ans;
    }
}
