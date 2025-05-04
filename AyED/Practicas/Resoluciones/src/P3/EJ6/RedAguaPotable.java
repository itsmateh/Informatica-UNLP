package P3.EJ6;

import P3.EJ1yEJ3yEJ5.GeneralTree;

import java.util.LinkedList;
import java.util.List;

public class RedAguaPotable {
    private GeneralTree<Character> root;

    public RedAguaPotable(GeneralTree<Character> root){
        this.root = root;
    }
    public double minimoCaudal(double caudal){
        // acordarse siempre de que root puede ser null
        if(root == null) return 0;
        return minimoCaudalRec(root, caudal);
    }
    private double minimoCaudalRec(GeneralTree<Character> tree, double caudal){
        // si es una hoja (casa) devuelvo el caudal que me pasaron de mas arriba en la rec.
        if(tree.isLeaf()) return caudal;
        // ahora si no es una casa, preparo los datos (dividir el monto)
        int cnt_hijos = tree.getChildren().size();
        double nue_caudal = caudal / cnt_hijos;
        double minimo = 999999.0;
        // para cada hijo calculo cual es el menor entre todos sus subarboles
        for(GeneralTree<Character> ch : tree.getChildren()){
            minimo = Math.min(minimo, minimoCaudalRec(ch, nue_caudal));
        }
        // cuando terminamos de evaluar el minimo con todos sus hijos, retorno el tipo del dato del metodo
        return minimo;
    }

    public static void main(String[] args) {
        GeneralTree<Character> ab1 = new GeneralTree<Character>('B');

        List<GeneralTree<Character>> subChildren1 = new LinkedList<GeneralTree<Character>>();
        subChildren1.add(new GeneralTree<Character>('L'));
        GeneralTree<Character> subAb1 = new GeneralTree<Character>('G', subChildren1);
        List<GeneralTree<Character>> subChildren2 = new LinkedList<GeneralTree<Character>>();
        subChildren2.add(new GeneralTree<Character>('F'));
        subChildren2.add(subAb1);
        GeneralTree<Character> ab2 = new GeneralTree<Character>('C', subChildren2);

        List<GeneralTree<Character>> subChildren3 = new LinkedList<GeneralTree<Character>>();
        subChildren3.add(new GeneralTree<Character>('M'));
        subChildren3.add(new GeneralTree<Character>('N'));
        GeneralTree<Character> subAb2 = new GeneralTree<Character>('J', subChildren3);
        List<GeneralTree<Character>> subChildren4 = new LinkedList<GeneralTree<Character>>();
        subChildren4.add(new GeneralTree<Character>('H'));
        subChildren4.add(new GeneralTree<Character>('I'));
        subChildren4.add(subAb2);
        subChildren4.add(new GeneralTree<Character>('K'));
        subChildren4.add(new GeneralTree<Character>('P'));
        GeneralTree<Character> ab3 = new GeneralTree<Character>('D', subChildren4);

        GeneralTree<Character> ab4 = new GeneralTree<Character>('E');

        List<GeneralTree<Character>> arbol = new LinkedList<GeneralTree<Character>>();
        arbol.add(ab1); arbol.add(ab2); arbol.add(ab3); arbol.add(ab4);

        GeneralTree<Character> tree = new GeneralTree<Character>('A', arbol);
        RedAguaPotable red = new RedAguaPotable(tree);
        System.out.println("Minimo: " + red.minimoCaudal(1000.0));
    }

}
