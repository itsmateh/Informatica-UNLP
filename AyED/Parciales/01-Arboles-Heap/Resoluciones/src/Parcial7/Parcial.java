package Parcial7;
import GT.GeneralTree;

class Resultados{
    private int total, positivos, negativos;
    public Resultados(){
        this.total = 0;
        this.positivos = 0;
        this.negativos = 0;
    }
    public int getTotal(){return this.total;}
    public int getPositivos(){return this.positivos;}
    public int getNegativos(){return this.negativos;}

    public void incTotal(int value){this.total+= value;}
    public void incPositivos(int value){this.positivos+= value;}
    public void incNegativos(int value){this.negativos+= value;}
}

public class Parcial {
    public int resolver(GeneralTree<Integer> tree){
        if(tree != null && !tree.isEmpty()){
            Resultados res = new Resultados();
            find_ans(tree, res);
            if((res.getTotal() & 1) == 0) return res.getPositivos();
            return res.getNegativos();
        }
        return 0;
    }

    private void find_ans(GeneralTree<Integer> node, Resultados res){
        // postorder !!! por eso primero vemos todos los hijos y despues evaluamos
        for(GeneralTree<Integer> ch : node.getChildren()){
            find_ans(ch, res);
        }
        res.incTotal(node.getData());
        if(node.getData() > 0) res.incPositivos(node.getData());
        else if(node.getData() < 0) res.incNegativos(node.getData());
    }
}
