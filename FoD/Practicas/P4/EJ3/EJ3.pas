program EJ3; 
{Los árboles B+ representan una mejora sobre los árboles B dado que conservan la propiedad de 
acceso indexado a los registros del archivo de datos por alguna clave, pero permiten además un 
recorrido secuencial rápido. Al igual que en el ejercicio 2, considere que por un lado se tiene el 
archivo  que  contiene  la  información  de  los  alumnos  de  la  Facultad  de Informática (archivo de 
datos no ordenado) y por otro lado se tiene un índice al archivo de datos, pero en este caso el 
índice se  estructura como un arbol B+ que ofrece acceso indizado por DNI al archivo de alumnos. 
Resuelva los siguientes incisos:}

{ ================================================================================================================ }

{A - Cómo se organizan los elementos (claves) de un árbol B+? ¿Qué elementos se encuentran 
en los nodos internos y que elementos se encuentran en los nodos hojas?}

// las claves solamente estan en las hojas, los nodos internos contienen claves que sirven para mandarte hacia 
// una de las hojas

{ ================================================================================================================ }

{B - ¿Qué característica distintiva presentan los nodos hojas de un árbol B+? ¿Por qué?}

// los nodos hojas contienen las claves y ademas de tener un enlace (una especie de linked-list digamos) a la siguiente
// hoja de forma ascendente, por lo que hace la busqueda secuencial mas rapida

{ ================================================================================================================ }

{C - }

const M =  // orden del arbol 
type 
   alu = record 
      nombre:string; 
      apellido:string; 
      dni:string;
      legajo:string;
      ingreso:integer;
   end;
   data = file of alu; 

   list = ^nodo;
   nodo = record 
      cant_claves:integer;
      claves = array[1..M-1] of integer;
      links = array[1..M-1] of integer;
      hijos = array[1..M] of integer;  // y M descendientes/hijos
      next:list; 
   end; 

   tree = file of nodo; 
var 
   bplus_tree:tree;




{ ================================================================================================================ }

{D - Describa, con sus palabras, el proceso de búsqueda de un alumno con un DNI específico 
haciendo  uso  de  la  estructura  auxiliar  (índice)  que  se  organiza  como  un  árbol  B+.  ¿Qué 
diferencia encuentra respecto a la búsqueda en un índice estructurado como un árbol B?}

// el proceso es realmente muy parecido, solo que en un arbol B+ terminas encontrando el dato en una hoja,
// mientras que quizas en un arbol B lo terminabas encontrando en un nodo interno

{ ================================================================================================================ }

{E - Explique  con  sus  palabras  el  proceso  de  búsqueda  de  los  alumnos  que  tienen DNI en el 
rango entre 40000000 y 45000000, apoyando la búsqueda en un índice organizado como un 
árbol B+. ¿Qué ventajas encuentra respecto a este tipo de búsquedas en un árbol B?}

// para consultas en rango, es significativa la ventaja del arbol B+ sobre el B, ya que 
// una vez llegado a una hoja en el arbol B, te toca volver a la raiz y probar de nuevo
// mientras que en el B+, una vez llegado a una hoja, podes ir "saltando" en hoja en hoja
// o sea le mandas un bsearch entre las hojas y lo sacas al toque

// complejidad del b tree -> O(log n) para la 1ra hoja y desp O(k * log n) 
// complejidad del b+ tree -> O(log n) para la 1r hoja y desp O(k)

{ ================================================================================================================ }
