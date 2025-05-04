package P3.EJ10;

import P3.EJ1yEJ3yEJ5.GeneralTree;

import java.util.LinkedList;
import java.util.List;

public class ParcialArboles {
    public static List<Integer> resolver(GeneralTree<Integer> arbol){
        List<Integer> ans = new LinkedList<Integer>();
        List<Integer> curr_path = new LinkedList<Integer>();
        int level = 0;
        if(arbol != null && !arbol.isEmpty())  find_path(arbol, ans, curr_path, level, 0, 0 );
        return ans;
    }
    private static int find_path(GeneralTree<Integer> node, List<Integer> ans, List<Integer> curr_path,
                                 int level, int curr_sum, int max_sum)
    {
        // sumamos a curr_sum el valor de este nodo y agregamos a la curr_list
        curr_sum += (node.getData() * level);
        if(node.getData() == 1) curr_path.add(node.getData());

        // caso base -> llegar a una para ver si el camino desde root hasta leaf es el mejor
        if(node.isLeaf()){
            if(curr_sum > max_sum){
                max_sum = curr_sum;
                ans.clear();
                ans.addAll(curr_path);
            }
        }
        // si no es una hoja, buscamos el max_sum por los hijos de los nodos hasta encontralo.
        // recordemos que tenemos que ir actualizando el max_sum para saber cuando una lista es mejor que otra
        // por lo que tenemos que buscar eso, el max_sum , para poder ir pasando el parametro actualizado por los hijos
        // y que estos se encarguen (al llegar a una hoja) de actualizar la mejor lista si es necesario.
        else{
            for(GeneralTree<Integer> ch : node.getChildren()){
                max_sum = find_path(ch, ans, curr_path, level+1,curr_sum, max_sum);
            }
        }
        // si llegamos aca es porque estoy en un nodo interno (no hoja) y necesito propagar el max_sum para arriba
        // o porque termino la recursion (lo cual no nos importa ya que hemos actualizado el mejor camino antes de llegar aca)
        // ahora, si estamos aca porque es un nodo interno, quiere decir que estoy volviendo para arriba.
        // una analogia seria: estamos perdidos en un bosque con varios caminos y queremos encontrar la salida, por lo que vamos
        // poniendo piedras en el camino para saber por donde venimos. Cuando lleguemos al final (una hoja) tenemos que juntarlas
        // para llegar al inicio (root) y buscar por otro camino.
        // mucho texto loko basicamente tenemos que ir sacando del curr_path los nodos para agregar otros y despues cuando llegamos
        // a una hoja comparar con el max_sum
        curr_path.remove(curr_path.size()-1);
        return max_sum;
    }

}
