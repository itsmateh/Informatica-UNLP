program EJ1j;

{Considere que desea almacenar en un archivo la información correspondiente a los alumnos de la 
Facultad de Informática de la UNLP. De los mismos deberá guardarse nombre y apellido, DNI, legajo 
y año de ingreso. Suponga que dicho archivo se organiza como un árbol B de orden M.}


{ ================================================================================================================ }

{A - Defina en Pascal las estructuras de datos necesarias para organizar el archivo de alumnos 
como un árbol B de orden M.}

const M =  // orden del arbol 
type 
   alu = record 
      nombre:string; 
      apellido:string; 
      dni:string;
      legajo:string;
      ingreso:integer;
   end;

   nodo = record 
      cant_claves:integer;
      claves = array[1..M-1] of alumno; // recordemos que podemos tener M-1 elementos 
      hijos = array[1..M] of integer;  // y M descendientes/hijos
   end; 

   tree = file of nodo; 
var 
   b_tree:tree;


{ ================================================================================================================ }

{B - Suponga  que  la  estructura  de  datos  que  representa  una  persona  (registro  de  persona) 
ocupa 64 bytes, que cada nodo del árbol B tiene un tamaño de 512 bytes y que los números 
enteros  ocupan  4  bytes,  
¿cuántos registros de persona entrarían en un nodo del árbol B? 
¿Cuál sería el orden del árbol B en este caso (el valor de M)? 

Para resolver este inciso, puede utilizar la fórmula N = (M-1) * A + M * B + C, donde N es el tamaño del nodo (en bytes), A es el 
tamaño de un registro (en bytes), B es el tamaño de cada enlace a un hijo y C es el tamaño 
que  ocupa  el  campo  referido  a  la  cantidad  de  claves.  El  objetivo  es  reemplazar  estas 
variables  con  los  valores  dados  y  obtener  el  valor  de  M  (M  debe  ser  un número entero, 
ignorar la parte decimal).}


// nodo = cant_claves (4) + (registro (64) * claves) + (4 * hijos) 
// 512 = 4+(64*M-1) + (4*M) => M = 8,4. 
// por lo que al ser de orden M=8, entrarian M-1 registros, o sea 7.


{ ================================================================================================================ }

{C - ¿Qué impacto tiene sobre el valor de M organizar el archivo con toda la información de los 
alumnos como un árbol B?}

// Siendo que M es el orden de un arbol, determinando los limites de claves (M-1) e hijos (M), 
// un mayor M resultará en nodos más amplios, por lo que el arbol seria mas "ancho" pero sacrificando profundidad. 
// por el otro lado, a menor valor de M la profundidad aumenta sacrificando el tamaño de los nodos. 

{ ================================================================================================================ }

{D - ¿Qué  dato  seleccionaría  como  clave  de  identificación  para  organizar  los  elementos 
(alumnos) en el árbol B? ¿Hay más de una opción?}

// El legajo o DNI, ya que son datos unicos de cada estudiante

{ ================================================================================================================ }

{E - Describa  el  proceso  de  búsqueda  de  un  alumno  por  el  criterio  de  ordenamiento 
especificado en el punto previo. ¿Cuántas lecturas de nodos se necesitan para encontrar un 
alumno por su clave de identificación en el peor y en el mejor de los casos? ¿Cuáles serían 
estos casos?}

// peor caso: H (altura del arbol) lecturas, descenso completo hasta la hoja mas baja
// mejor caso: 1 la clave está en la raíz, primera pos

{ ================================================================================================================ }

{F - ¿Qué ocurre si desea buscar un alumno por un criterio diferente? ¿Cuántas lecturas serían 
necesarias en el peor de los casos?}

// no vas a poder aprovechar el criterio de ordenamiento, por lo que seria lineal O(n)
