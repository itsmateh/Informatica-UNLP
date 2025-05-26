{Una mejora respecto a la solución propuesta en el ejercicio 1 sería mantener por un lado el archivo 
que contiene la información de los alumnos de la Facultad de Informática (archivo de datos no 
ordenado) y por otro lado mantener un índice al archivo de datos que se estructura como un árbol 
B que ofrece acceso indizado por DNI de los alumnos}

{ ================================================================================================================ }

{A - Defina en Pascal las estructuras de datos correspondientes para el archivo de alumnos y su 
índice}

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
      indice = array[1..M-1] of integer; // acceso por dni 
      hijos = array[1..M] of integer;  // y M descendientes/hijos
   end; 

   tree = file of nodo; // arbol en el cual podemos buscar por indice (dni)
   data = file of alumno; // aca guardamos los alumnos asi nomas, sin orden
var 
   b_tree:tree;
   data_file:data;

{ ================================================================================================================ }

{C - ¿Qué implica que el orden del árbol B sea mayor que en el caso del ejercicio 1?}

// implica que el arbol va ser mas "ancho", es decir que la cantidad de elementos de un nodo es mayor ademas de mas hijos, por lo que
// el arbol a medida que su orden aumenta (M), se hace menos profundo

{ ================================================================================================================ }

{D - Describa con sus palabras el proceso para buscar el alumno con el DNI 12345678 usando el 
índice definido en este punto}

// aprovechando el criterio de orden, voy moviendome a la izquierda si es menor o a la derecha si es mayor, una vez
// encontrado, uso el NRR que esta en el enlace para ir al archivo de datos y buscarlo ahi.

{ ================================================================================================================ }
