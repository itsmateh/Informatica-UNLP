package Parcial9;

import GT.GeneralTree;
import GT.Queue;

public class ParcialArboles {
    public static boolean resolver(GeneralTree<Integer> arbol){
        if(arbol != null && !arbol.isEmpty()) return find_ans(arbol);
        return false;
    }
    private static boolean find_ans(GeneralTree<Integer> node){
        boolean check = true;
        Queue<GeneralTree<Integer>> queue = new Queue<GeneralTree<Integer>>;
        int nodos_nivel_anterior = 0;
        int nodos_nivel_actual = nodos_nivel_anterior + 1;

        queue.enqueue(node);
        while(!queue.isEmpty()){
            // de entrada verificamos si cumple o no
            if(nodos_nivel_actual - nodos_nivel_anterior != 1){
                check = false;
                return check;
            }
            for(int i=0; i<nodos_nivel_actual; i++){
                GeneralTree<Integer> curr_node = queue.dequeue();
                for(GeneralTree<Integer> ch : curr_node.getChildren()){
                    queue.enqueue(ch);
                }
            }
            // actualizamos para en la siguiente iteracion verificar si se cumple el if() de arriba del todo
            nodos_nivel_anterior = nodos_nivel_actual;
            nodos_nivel_actual = queue.size();
            /*
            ya que con el 1er for vamos sacando todos los nodos, y con el 2do encolamos, al finalizar nos deberia quedar
            la cantiadad de nodos, y esa cantidad de nodos de actual, se va a chequar en el 1er if si cumple o no
            */

        }
        return check;
    }
}
