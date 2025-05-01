program EJ7; 
const val_corte = -1;
type 
   ave = record 
      codigo:integer;
      nombre:string;
      familia:string;
      descripcion:string;
      zona_geografica:string;
   end;

   archivo_aves = file of ave;


{ ========================================> MODULOS}
// creacion del archivo maestro 
procedure crear_archivo(var ar:archivo_aves);
var 
   ave:ave;
   i:integer;
begin 
   Assign(ar, 'ArchivoAves.dat');
   Rewrite(ar);
   
   // creamos la cabecera
   ave.codigo := 0; 
   ave.nombre := 'zzz';
   ave.familia := 'zzz';
   ave.descripcion := 'zzz';
   ave.zona_geografica := 'zzz'; 
   write(ar, ave);

   // creamos los demas datos
   for i:=1 to 10 do begin 
      ave.codigo:=i;
      ave.nombre:='Ave: '+IntToStr(i);
      ave.familia:='Familia: '+IntToStr(i);
      ave.descripcion:='Descripcion: '+IntToStr(i);
      ave.zona_geografica:='Zona: '+IntToStr(i);
      Write(ar, ave);
   end;

   close(ar);
end;


procedure baja_archivo_logica(var ar:archivo_aves; var cnt:integer); 
var 
   codigo:integer;
   cabecera,unAve:ave;
begin 
   reset(ar);
   
   WriteLn('Codigo a borrar: '); ReadLn(codigo);

   read(ar, cabecera);
   read(ar, unAve);
   while(not eof(ar) and (codigo <> unAve.codigo)) do read(unAve);
   
   cnt := cnt+1; // para saber cuantos borrados hay
   if(codigo = unAve.codigo) then begin 
      seek(ar, filepos(ar)-1); 
      write(ar, cabecera);
      cabecera.codigo := (FilePos(ar)-1) * -1;
      seek(ar, 0); 
      write(ar, cabecera);
   end 
   else WriteLn('No se encontro el codigo.');

   close(ar);
end;

procedure baja_archivo_fisica(var ar:archivo_aves; var cnt:integer); 
var 
   pos_borrar:integer;
   aux, unAve:ave;
begin 
   reset(ar); 
   
   read(ar, unAve); 
   pos_borrar := unAve.codigo; // aca leo lo del encabezado
   while(cnt>=0) do begin 
      // asumo que el dato a borrar no esta en la ultima posicion

      seek(ar, filepos(filesize(ar)-1));
      read(ar, aux); // voy a la ultima posicion y tomo el dato
      truncate(ar); // ahora elimino el ultimo elemento (acorto "la lista" en 1 elemento)

      seek(ar, filepos(pos_borrar)); // una vez tomado el dato de al ult. pos, voy a la posicion guarada para borrar
      read(ar, unAve); // antes de borrar, leo lo que q hay aca (me interesa el codigo que tiene la sig. pos a borrar)
      seek(ar, FilePos(ar)-1); // vuelvo para atras pq avance 1 con el read de arriba
      pos_borrar := unAve.codigo;  // me guardo la siguiente posicion a borrar
      write(ar, aux); // suplanto ahora si 

      cant := cant - 1;
   end;
   
   close(ar);
end;

procedure baja_archivo_fisica_1truncate(var ar:archivo_aves; var cnt:integer); 
var 
   desplazamiento, pos_borrar:integer;
   aux, unAve:ave;
begin 
   reset(ar); 
   
   read(ar, unAve); 
   pos_borrar := unAve.codigo; // aca leo lo del encabezado
   desplazamiento := 0;
   while(cnt>=0) do begin 
      // asumo que el dato a borrar no esta en la ultima posicion
      
      seek(ar, filepos(filesize(ar)-(1+desplazamiento)));
      read(ar, aux); 

      seek(ar, filepos(pos_borrar)); 
      read(ar, unAve);
      seek(ar, FilePos(ar)-1); 
      pos_borrar := unAve.codigo;  
      write(ar, aux); 

      cant := cant - 1;
      desplazamiento := desplazamiento + 1;
   end;

   seek(ar, FileSize(ar) - (1+desplazamiento)); 
   trucante(ar);
   
   close(ar);
end;


{ ========================================> PROGRAMA PRINCIPAL}
var 

begin 

   // vos confia anda todo perfecto (tengo que chequearlo en la practica) (pronto todo acabara)

end.