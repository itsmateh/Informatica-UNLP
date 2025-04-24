package P3.EJ1;
import javax.management.QueryEval;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GeneralTree<T>{

	private T data;
	private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>(); 

	public GeneralTree() {
		
	}
	public GeneralTree(T data) {
		this.data = data;
	}

	public GeneralTree(T data, List<GeneralTree<T>> children) {
		this(data);
		this.children = children;
	}	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<GeneralTree<T>> getChildren() {
		return this.children;
	}
	
	public void setChildren(List<GeneralTree<T>> children) {
		if (children != null)
			this.children = children;
	}
	
	public void addChild(GeneralTree<T> child) {
		this.getChildren().add(child);
	}

	public boolean isLeaf() {
		return !this.hasChildren();
	}
	
	public boolean hasChildren() {
		return !this.children.isEmpty();
	}
	
	public boolean isEmpty() {
		return this.data == null && !this.hasChildren();
	}

	public void removeChild(GeneralTree<T> child) {
		if (this.hasChildren())
			children.remove(child);
	}

	// ================================= EJ3 =================================
	public int altura() {
		// caso base -> si estoy en una hoja devuelvo 0. Enfoque bottom-up
		if(this.isLeaf()) return 0; // ahora que llegue a una hoja, retorno 0 y empiezo el backtracking para arriba
		// inicializo el max. depth
		int m_depth = 0;
		List<GeneralTree<T>> childs = this.getChildren();
		for(GeneralTree<T> ch : childs){
			m_depth = Math.max(m_depth, ch.altura());
		}
		// despues de haber llegado a una hoja y devuelto 0, el max(depth, ch.altura()) me va a dar 0 y le sumo 1
		// asi recurisvamente, entonces en la siguiente va retornar m_depth (que era 1) + 1 (el nivel acutal)
		// asi hasta terminar con las llamadas recursivas del for (que estan los hijos de root)
		return m_depth + 1;
	}

	public int nivel(T dato){
		// caso base -> encontre el dato
		if(this.data.equals(dato)) return 0;

		List<GeneralTree<T>> childs = this.getChildren();
		for(GeneralTree<T> ch : childs){
			if(ch.nivel(dato) != -1){
				return ch.nivel(dato) + 1;
			}
		}
		// si salgo del for es porque no lo encontre
		return -1;
	}

	public int ancho(){
		Queue<GeneralTree<T>> queue = new LinkedList<>();
		queue.add(this);

		int m_width = 0;
		while(!queue.isEmpty()){
			int lvl_size = queue.size();
			m_width = Math.max(m_width, lvl_size);

			List<GeneralTree<T>> childs = this.getChildren();
			for(GeneralTree<T> ch : childs){
				GeneralTree<T> nodo = queue.remove();
				queue.addAll(nodo.getChildren());
			}
		}

		return m_width;
	}
}