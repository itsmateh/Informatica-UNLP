procedure EJ2; 
const val_corte = 999;
type 
   asistente = record 
      nro:integer; 
      apellido:string;
      nombre:string;
      email:string;
      telefono:integer;
      dni:string;
   end;

   archivo = file of asistente; 

procedure pedir_datos(var a:asistente);
begin 
   WriteLn('Nro: '); ReadLn(a.nro);
   WriteLn('Apellido: '); ReadLn(a.apellido);
   WriteLn('Nombre: '); ReadLn(a.nombre);
   WriteLn('Email: '); ReadLn(a.email);
   WriteLn('Telefono: '); ReadLn(a.telefono);
   WriteLn('DNI: '); ReadLn(a.dni);
end;

procedure generar_archivos(var ar:archivo);
var   
   asis:asistente; 
begin 
   Assign(ar, 'archivo_asistente.dat');
   Rewrite(ar);
   pedir_datos(asis);
   while(asis.nro <> val_corte) do begin 
      write(ar,asis);
      pedir_datos(asis);
   end;
   close(ar);
end;

procedure generar_bajas(var ar:archivo);
var 
   inf:integer;
   asis:asistente;
   check:string;
begin 
   inf := 1000;
   reset(ar);
   leer(ar, asis);
   while(not eof(ar)) do begin
      if(asis.nro < inf) then begin 
         asis.apellido := '###' + asis.apellido;
         seek(ar, FilePos(ar_logico)-1);
         Write(ar, asis);
      end; 
      leer(ar, asis);
   end;
    
   // CERRAR ARCHIVOS !!!!!
   close(ar);
end;

begin 
   generar_archivos(ar);
   generar_bajas(ar);
end;