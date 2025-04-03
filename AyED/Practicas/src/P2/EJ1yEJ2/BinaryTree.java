package P2.EJ1yEJ2;
import java.util.*;

public class BinaryTree <T> {
    private T data;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree() {
        super(); 
    }

    public BinaryTree(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    /**
     * Preguntar antes de invocar si hasLeftChild()
     * @return
     */
    public BinaryTree<T> getLeftChild() {
        return leftChild;
    }
    /**
     * Preguntar antes de invocar si hasRightChild()
     * @return
     */
    public BinaryTree<T> getRightChild() {
        return this.rightChild;
    }

    public void addLeftChild(BinaryTree<T> child) {
        this.leftChild = child;
    }

    public void addRightChild(BinaryTree<T> child) {
        this.rightChild = child;
    }

    public void removeLeftChild() {
        this.leftChild = null;
    }

    public void removeRightChild() {
        this.rightChild = null;
    }

    public boolean isEmpty(){
        return (this.isLeaf() && this.getData() == null);
    }

    public boolean isLeaf() {
        return (!this.hasLeftChild() && !this.hasRightChild());

    }

    public boolean hasLeftChild() {
        return this.leftChild!=null;
    }

    public boolean hasRightChild() {
        return this.rightChild!=null;
    }
    @Override
    public String toString() {
        return this.getData().toString();
    }

    public int contarHojas() {
        if(isLeaf()) return 1; // caso base
        else {
           int ans = 0;
           if(hasLeftChild()) ans += getLeftChild().contarHojas();
           if(hasRightChild()) ans+= getRightChild().contarHojas();
           return ans;
        }
    }

    public BinaryTree<T> espejo(){
        BinaryTree<T> mirrorTree = new BinaryTree<T>(this.getData());
        if(this.hasLeftChild()) mirrorTree.addRightChild(getLeftChild().espejo());
        if(this.hasRightChild()) mirrorTree.addLeftChild(getRightChild().espejo());
        return mirrorTree;
    }

    // 0<=n<=m
    public void entreNiveles(int n, int m){
        // es medio un bfs
        if(n < 0 || m < n || this.isEmpty()) return;
        Queue<BinaryTree<T>> queue = new LinkedList<>();
        queue.add(this);
        int nivel = 0;
        while(!queue.isEmpty()){
          int cantNodosNivel = queue.size();
          if(nivel >= n && nivel <= m){
              for(int i=0; i<cantNodosNivel; i++){
                  BinaryTree<T> nodoAct = queue.remove();
                  System.out.print( " | "  + nodoAct.getData() +  " | ");
                  if(nodoAct.hasLeftChild()) queue.add(nodoAct.getLeftChild());
                  if(nodoAct.hasRightChild()) queue.add(nodoAct.getRightChild());
              }
              System.out.println(); // salto despues de imprimir el nivel
          }
          else{
              BinaryTree<T> nodoAct = queue.remove();
              if(nodoAct.hasLeftChild()) queue.add(nodoAct.getLeftChild());
              if(nodoAct.hasRightChild()) queue.add(nodoAct.getRightChild());
          }
          nivel++;
        }
    }

    public void imprimir(){
        if(this.hasLeftChild()) this.getLeftChild().imprimir();
        System.out.println(this.getData()+" ");
        if(this.hasRightChild()) this.getRightChild().imprimir();
    }


    // ================================================================
    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<Integer>(10);

        bt.addLeftChild(new BinaryTree<Integer>(5));
        bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(3));
        bt.getLeftChild().addRightChild(new BinaryTree<Integer>(4));

        bt.addRightChild(new BinaryTree<Integer>(15));
        bt.getRightChild().addLeftChild(new BinaryTree<Integer>(20));
        bt.getRightChild().addRightChild(new BinaryTree<Integer>(12));

        // test
        System.out.println(bt.contarHojas()); // a

        System.out.println("Original: "); // b
        bt.imprimir();
        System.out.println("Espejo");
        BinaryTree<Integer> btMirror = bt.espejo();
        btMirror.imprimir();

        int n,m;
        System.out.println("Niveles a buscar: ");
        Scanner s = new Scanner(System.in);
        n = s.nextInt(); m = s.nextInt();

        bt.entreNiveles(n,m);

    }

}

