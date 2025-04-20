program EJ3;

type 
   novelas = record 
      cod:integer;
      genero:string; 
      nombre:string;
      duracion:integer;
      director:string;
      precio:real;
   end;

   archivo = file of novelas;

procedure leer(var nov:novelas);
begin
  Writeln('Codigo Novela: '); ReadLn(nov.cod);
  if (nov.cod <> -1) then begin
      WriteLn('Genero: '); ReadLn(nov.genero);
      WriteLn('Nombre: '); ReadLn(nov.nombre);
      WriteLn('Duracion: '); ReadLn(nov.duracion);
      WriteLn('Director: '); ReadLn(nov.director);
      WriteLn('Precio: '); ReadLn(nov.precio);
   end;
end;

procedure crear_archivo(var a:archivo);
var 
   nov:novela;
   ar_fisico:string;
begin
   WriteLn('Nombre del archivo: ');
   readln(ar_fisico); 
   assign(a,ar_fisico);
   Rewrite(a);
   nov.cod = 0; 
   nov.genero = ' ';
   nov.nombre = ' ';
   nov.duracion = 0;
   nov.director = ' ';
   nov.precio = 0;
   write(ar, nov);
   leer(nov); 
   while(nov.cod <> -1) do begin 
      Write(a, nov);
      leer(nov);
   end;
   close(ar);
   WriteLn('Archivo creado');
end;

procedure alta_archivo(var a:archivo); 
var 
   nov, cabecera: novelas;
begin 
   reset(a); 
   leer(a, cabecera); 
   if(cabecera.cod = 0) then begin 
      seek(a, FileSize(a)); 
      write(a, nov);
   end 
   else begin
      // pararme en el prox. lugar libre
      seek(a, cabecera.cod * -1);
      // leo el dato de donde estoy parado -> "dato" es en realidad la proxima posicion libre
      read(a, cabecera);
      // como lei y avance, necesito volver para atras en 1
      seek(a, FilePos(a)-1);
      // sobreescribo en donde estoy ahora
      write(a, nov);
      // me voy al inicio para reescribir la cabecera
      seek(a, 0); 
      // escribo el dato
      Write(a, cabecera);
   end;   
   close(a);
end;

procedure editar_archivo(var ar:archivo);
var 
   aux, nov:novelas; 
begin 
   reset(ar);  
   leer(nov); 
   while(not eof(ar)) do begin 
      read(ar, aux); 
      if(aux.cod = nov.cod) then begin 
         nov.genero := aux.genero; 
         nov.nombre := aux.nombre; 
         nov.duracion := aux.duracion; 
         nov.director := aux.director; 
         nov.precio := aux.precio; 
      end;
   end;
   close(ar);
end;

procedure eliminar_novela(var ar:archivo); 
var 
   cabecera, nov:novelas;
begin 
   reset(ar);
   leer(ar, cabecera);

   // busqueda 
   WriteLn('Codigo a borrar: '); 
   readln(cod_eliminar);

   // tener cuidado con que no exista el codigo
   read(ar, nov);
   while(nov.cod <> cod_eliminar) do read(ar, nov);

   {
   una especie de "swap". 
   1- Voy a la pos a eliminar -> escribo el dato de cabecera
   2- Voy a la pos de cabecera (0) -> escribo el indice donde hay un lugar libre (que es donde acabo de eliminar)
   }

   if(nov.cod = cod_eliminar) then begin 
      // 1. CASO ESPECIAL porque pide pasar a un txt hasta las novelas eliminadas, por lo que, como se deberia modificar la posicion eliminada? solo el codigo/indice o toda la novela
      seek(ar, FilePos(a)-1);
      write(ar, cabecera);
      // nov.cod := cabecera.cod;
      // write(ar, nov);
      
      
      // 2.
      cabecera.cod := (FilePos(ar)-1) * -1;
      seek(ar, 0);
      write(a, cabecera); 
   end;
   close(ar);
end;

procedure exportar_a_txt(var ar:archivo);
var 
   ar_txt:text; 
   n:novelas;
begin 
   reset(ar);
   Rewrite(ar_txt);
   assign(ar_txt, 'novelas.txt');

   seek(ar, 1);
   while(not eof(ar)) do begin 
      read(ar, n); 
      if(n.cod < 1) then 
         write(txt, 'Se elimino esta novela');
       write(txt, ' Codigo=', n.codigo, ' Genero=', n.genero, 
                  ' Nombre=', n.nombre, ' Duracion=', n.duracion, 
                  ' Director=', n.director, ' Precio=', n.precio);
   end;

   WriteLn('txt creado!');
   close(ar_txt);
   close(ar);
end;


// procedure con el menu 


// main