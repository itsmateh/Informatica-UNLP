program EJ1; 

type 
   archivo = file of integer; 

procedure cargar_archivo(var archivo_logico : archivo); 
var 
   num : integer; 
begin 
   readln(num); 
   while(num <> 3000) do begin 
      write(archivo_logico, num);
      readln(num);
   end;
   Close(archivo_logico);
end;


var 
   archivo_fisico:string[30];
   archivo_logico:archivo;
begin 
   write('Nombre del archivo: '); 
   readln(archivo_fisico);
   Assign(archivo_logico, archivo_fisico);
   Rewrite(archivo_logico);
   cargar_archivo(archivo_logico);
end.

