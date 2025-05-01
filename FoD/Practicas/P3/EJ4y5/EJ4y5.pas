program EJ4y5; 

type 
   reg_flor = record 
      nombre: String[45]; 
      codigo: integer; 
   end; 
 
   tArchFlores = file of reg_flor;



// ============== Modulos
procedure crear_archivo(var arc: tArchFlores);
var
    f: reg_flor;
    i: integer;
    nombres: array[1..5] of string[20];
begin
    assign(arc, 'ArchivoFlores');
    rewrite(arc);

    f.codigo := 0;
    f.nombre := 'Cabecera';
    write(arc, f);

    nombres[1] := 'Rosa';
    nombres[2] := 'Tulipan';
    nombres[3] := 'Margarita';
    nombres[4] := 'Girasol';
    nombres[5] := 'Lirio';

    for i := 1 to 5 do
    begin
        f.codigo := i;
        f.nombre := nombres[i];
        write(arc, f);
    end;

    close(arc);
end;


// EJ4 
procedure alta_archivo(var ar:tArchFlores; nombre:string; codigo:integer);
var
   flor, cabecera:reg_flor;
begin

   reset(ar); // abro archivo que se supone que esta creado
   read(ar, cabecera);
   flor.nombre := nombre; 
   flor.codigo := codigo;
   if(cabecera.codigo = 0) then // quiere decir que no hay espacios para ser usados en la "lista"
   begin
      seek(ar, FilePos(ar)); // me voy al final del archivo
      write(ar, flor);   // escribo
   end
    
   else begin // si no, quiere decir que hay una posicion libre y podemoos poner nuestro dato ahi
      seek(ar, cabecera.codigo * -1); // voy a la pos que hay guardado en cabecera
      read(ar, cabecera); // leo lo que tengo en esa pos
      seek(ar, FilePos(ar)-1); // vuelvo para atras pq lei
      write(ar, flor); // pongo el valor 
      seek(ar, 0); // voy a la pos 0 donde esta cabecera
      write(ar, cabecera); // actualizo cabecera 
   end;
   close(ar);
end;

// EJ4b
procedure listar_datos(var ar:tArchFlores); 

var 
   f:reg_flor; 
begin
   reset(ar);
   read(ar, f);
   while(not eof(ar)) do begin 
      if(f.codigo > 0) then WriteLn('Nombre: ', f.nombre, ' Codigo: ', f.codigo);
      read(ar, f);
   end;
   close(ar);
end;


// EJ5
// procedure baja_archivo(var ar:tArchFlores; f:reg_flor); 
// var   
//    flor, cabecera:reg_flor; 
// begin 
//    reset(ar);
//    read(ar, cabecera);

//    // busco en el archivo la flor
//    read(ar, flor);
//    while(not eof(ar) and (flor.nombre <> f.nombre)) do read(ar, flor);    

//    if(f.codigo = flor.codigo) then begin 
//       seek(ar, FilePos(ar)-1); // retrocedo porque estoy en +1 pos adelantado por el read
//       write(ar, cabecera); // lo de cabecera lo escribo aca
      
//       cabecera.codigo := (FilePos(ar)-1) * -1; // me guardo la posicion para despues ponerla en cabecera
//       seek(ar, 0); // voy a la pos de cabecera
//       write(ar, cabecera); // escribo en cabecera lo que me guarde antes (-Pos_Borrada);
      
//       WriteLn('Flor eliminada');
//    end
//    else WriteLn('No se encontro la flor'); 
   
//    close(ar);
// end;

procedure baja_archivo(var ar:tArchFlores; f:reg_flor); 
var   
   flor, cabecera:reg_flor; 
   check:boolean;
begin 
   reset(ar);
   read(ar, cabecera);
   check := false;
   // busco en el archivo la flor
   while(not eof(ar) and (not check)) do begin 
      read(ar, flor);    
      if(f.codigo = flor.codigo) then begin 
         check := true;
         seek(ar, FilePos(ar)-1); // retrocedo porque estoy en +1 pos adelantado por el read
         write(ar, cabecera); // lo de cabecera lo escribo aca
         
         cabecera.codigo := (FilePos(ar)-1) * -1; // me guardo la posicion para despues ponerla en cabecera
         seek(ar, 0); // voy a la pos de cabecera
         write(ar, cabecera); // escribo en cabecera lo que me guarde antes (-Pos_Borrada);
      end;
   end;
   if(check) then WriteLn('Flor eliminada')
   else WriteLn('No se encontro la flor');
   close(ar);
end;

// ============== Programa Principal 
var 
   ar:tArchFlores; 
   flor:reg_flor;
begin 
   crear_archivo(ar);
   listar_datos(ar);
   
   WriteLn('=============================');
   // alta_archivo(ar, 'Guinda Japonesa', 6);
   alta_archivo(ar, 'Clavel', 7);
   // alta_archivo(ar, 'Flor de loto', 8);
   listar_datos(ar);

   WriteLn('=============================');
   flor.nombre := 'Clavel'; flor.codigo := 7;
   baja_archivo(ar, flor);
   listar_datos(ar);
end.