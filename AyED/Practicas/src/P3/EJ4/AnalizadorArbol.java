package P3.EJ4;

import P3.EJ1yEJ3.GeneralTree;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class AnalizadorArbol {
   public double devolverMaximoPromedio(GeneralTree<AreaEmpresa> arbol){
       // 3 casos. 1-> esta vacio, 2-> contiene nodos, 3-> solo tiene 1 nodo
       if(arbol.isEmpty()) return 0;
       else return !arbol.isLeaf() ?  devolverMaximoPromedioRec(arbol) :  arbol.getData().getTardanza();
   }
   private double devolverMaximoPromedioRec(GeneralTree<AreaEmpresa> tree){
        double max = 0;
        double total = 0;
        int cant_nodos = 0;
        Queue<GeneralTree<AreaEmpresa>> q = new LinkedList<>();
        GeneralTree<AreaEmpresa> bt;
        q.add(tree);
        q.add(null);
        while(!q.isEmpty()){
            bt = q.remove();
            // si no llego el corte de nivel (null): aumento la canitdad de nodos, sumo el total y mando a la queue los hijos del arbol actual
            if(bt != null){
                cant_nodos++;
                total += bt.getData().getTardanza();
                List<GeneralTree<AreaEmpresa>> children = bt.getChildren(); // !!!!!! seria lo mismo hacer for(GeneralTree<AreaEmpresa> ch : bt.getChildren){q.add(ch);} ?
                for(GeneralTree<AreaEmpresa> ch : children){
                    q.add(ch);
                }
                // otra opcion al for: q.addAll(ch);
            }
            // si entra aca, es bt = null lo quiere decir que en el nivel no hay mas nodos, por lo que debo procesar los datos acumulados
            else if(!q.isEmpty()){
                double curr_mean = total / cant_nodos;
                max = Math.max(max, curr_mean);
                total = 0; // reiniciamos todo para el siguiente nivel
                cant_nodos = 0; // idem here
                q.add(null); // antes en el if pushee todos hijos pero no el null, ahora mando el null en señal de que una vez se procesen todos los hijos, viene el null para cortar el nivel
            }
        }
        return max;
   }
    public static void main(String[] args) {
        List<GeneralTree<AreaEmpresa>> children1 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children1.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("A",4)));
        children1.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("B",7)));
        children1.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("C",5)));
        GeneralTree<AreaEmpresa> a1 = new GeneralTree<AreaEmpresa>(new AreaEmpresa("J",13), children1);

        List<GeneralTree<AreaEmpresa>> children2 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children2.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("D",6)));
        children2.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("E",10)));
        children2.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("F",18)));
        GeneralTree<AreaEmpresa> a2 = new GeneralTree<AreaEmpresa>(new AreaEmpresa("K",25), children2);

        List<GeneralTree<AreaEmpresa>> children3 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children3.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("G",9)));
        children3.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("H",12)));
        children3.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("I",19)));
        GeneralTree<AreaEmpresa> a3 = new GeneralTree<AreaEmpresa>(new AreaEmpresa("L",10), children3);

        List<GeneralTree<AreaEmpresa>> children4 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children4.add(a1);
        children4.add(a2);
        children4.add(a3);
        GeneralTree<AreaEmpresa> a = new GeneralTree<AreaEmpresa>(new AreaEmpresa("M",14), children4);

        AnalizadorArbol arb = new AnalizadorArbol();
        System.out.println("El mayor promedio entre todos los valores promedios de los niveles es: " + arb.devolverMaximoPromedio(a));
    }
}
