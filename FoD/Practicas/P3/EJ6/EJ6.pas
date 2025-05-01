program EJ6; 

const val_corte = -777;
type 
   venta = record 
      cod_prenda:integer; 
      descripcion:string;
      color:string;
      prenda:string;
      stock:integer;
      precio:real;
   end;

   archivo_ventas = file of venta;
   archivo_codigo = file of integer;


{ =============================================================> MODULOS }


   // primero creamos el archivo maestro con las ventas
procedure leer(var v:venta); 
begin 
   WriteLn('Codigo de prenda: '); 
   ReadLn(v.cod_prenda);

   WriteLn('Descripcion: '); 
   ReadLn(v.descripcion); 

   WriteLn('Color: '); 
   ReadLn(v.color);

   WriteLn('Prenda: '); 
   ReadLn(v.prenda);

   WriteLn('Stock: '); 
   ReadLn(v.stock);

   WriteLn('Precio: '); 
   ReadLn(v.precio);

end;

procedure crear_archivo_maestro(var ar_m:archivo_ventas); 
var 
   cabecera, v:venta;
begin 
   Assign(ar_m, 'ArchvioMaestro.dat');
   Rewrite(ar_m);
   
   // inicializo cabecera. Para ser mas didactico lo hago con una variable "cabecera" poro se podria usar "v"
   cabecera.cod_prenda := 0; 
   cabecera.descripcion := 'zzz';
   cabecera.color := 'zzz';
   cabecera.prenda := 'zzz';
   cabecera.stock := -1;
   cabecera.precio := -1;
   write(ar_m, cabecera);

   leer(v);
   while(v.cod_prenda <> val_corte) do begin 
      write(ar, v);
      leer(v);
   end;
   close(ar);
   WriteLn('Archivo Maestro con las ventas creado.');
end;

   // ahora creamos el archivo con los codigos a eliminar
procedure crear_archivo_codigos(var ar_c:archivo_codigo);
var
   cod:integer;
begin 
   Assign(ar_c, 'ArchivoCodigos');
   Rewrite(ar_c);

   WriteLn('Codigo de venta a eliminar: '); 
   ReadLn(cod);
   while(cod <> val_corte) do begin 
      write(ar_c, cod);
      read(cod);
   end;

   close(ar_c);
end;

   // ahora con todo el setup empezamos con el problema. 
   // 1. Hacer bajas logicas en el archivo maestro con los codigos del archivo ArchivoCodigos
procedure baja_archivo(var ar_maestro:archvio_ventas; var ar_codigo:archivo_codigo); 
var
   ven,cabecera:venta;
   cod:integer;
begin
   reset(ar_maestro); reset(ar_codigo);

   read(ar_codigo, cod); 
   read(ar_maestro, cabecera);
   read(ar_maestro, ven);
   
   while(not eof(ar_codigo)) do begin 
      while(not eof(ar_maestro) and (cod <> ven.cod_prenda)) do read(ar_maestro, ven); 
      
      if(cod = ven.cod_prenda) then begin 
         seek(ar_maestro, filepos(ar_maestro)-1);
         write(ar_maestro, cabecera); 

         cabecera.cod_prenda := (FilePos(ar_maestro)-1) * -1;
         seek(ar_maestro, 0); 
         write(ar_maestro, cabecera);
         WriteLn('Eliminado'); 
      end
      else WriteLn('No se encuentra la venta con ese codigo de prenda.'); 

      // como tengo que eliminar multiples codigos, tengo que seguir leyendo del archivo codigos
      read(ar_codigo, cod);
   end;

   close(ar_maestro); close(ar_codigo);
end;

   // 2. Ahora con el archivo maestro actualizado, lo pasamos a otro archivo maestro sin los registros borrados logicamente
procedure pasar_archivo_maestro(var ar_maestro_original:archivo_ventas; var ar_maestro_nuevo:archivo_ventas);
var 
   v:venta;
begin 
   Assign(ar_maestro_nuevo, 'ArchivoMaestro2'); 
   Rewrite(ar_maestro_nuevo); 
   reset(ar_maestro_original);

   read(ar_maestro_original, v);
   while(not eof(ar_maestro_original)) do begin 
      read(ar_maestro_original, v); // leemos nuevamente porque el 1er elemento del maeestro orig. es la cabecera
      if(v.cod_prenda > 0) then write(ar_maestro_nuevo, v);
   end;

   close(ar_maestro_original); 
   close(ar_maestro_nuevo);
   erase(ar_maestro_original);
   rename(ar_maestro_nuevo, 'ArchivoMaestro.dat');
end;

{ ============================================================> PROGRAMA PRINCIPAL }
var 

begin 

   // vos confia loko ta todo perfecto si tenes dudas compilalo en tu cabeza

end.