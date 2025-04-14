program EJ4; 
uses SysUtils;
const val_corte = 999;
type 
   producto = record 
      codigo:integer; 
      nombre:string;
      desc:string;
      stock_dispo:integer; 
      stock_min:integer;
      precio:real;
   end;

   info_detalle = record 
      cod:integer;
      cant_vendida:integer;
   end;

   maestro = file of producto; 
   detalle = array[1..30] of file of info_detalle; 
   reg_detalle = array[1..30] of info_detalle; 
   t_detalle = file of info_detalle;

// creamos un maestro
procedure crear_maestro(var m: maestro);
var
  p: producto;
begin
  Assign(m, 'ArchivoMaestro.dat');
  Rewrite(m); 

  p.codigo := 1; p.nombre := 'Helado'; p.desc := 'Chocolate'; p.stock_dispo := 50; p.stock_min := 20; p.precio := 100;
  write(m, p);

  p.codigo := 2; p.nombre := 'Pizza'; p.desc := 'Muzzarella'; p.stock_dispo := 30; p.stock_min := 15; p.precio := 500;
  write(m, p);

  p.codigo := 3; p.nombre := 'Empanadas'; p.desc := 'Carne'; p.stock_dispo := 40; p.stock_min := 25; p.precio := 450;
  write(m, p);

  close(m);
end;


// ============

procedure leer(var a:t_detalle; reg:info_detalle); 
begin 
   if (not eof(a)) then read(a, reg)
   else reg.cod := val_corte; 

end;


procedure minimo(var vD:detalle; var registros:reg_detalle; var min:info_detalle); 
var 
   i, i_minimo:integer;
begin 
   min.cod := val_corte; 
   for i:=1 to 30 do begin 
      if(registros[i].cod <= min.cod) then begin 
         min := registros[i];
         i_minimo := i;
      end;
   end;   
   leer(vD[i_minimo], registros[i_minimo]);
end;


procedure actualizar_maestro(var ar_maestro:maestro; var vD:detalle); 
var 
   p:producto; 
   min:info_detalle; 
   i:integer; 
   registros:reg_detalle; 
   detalle_path:string;
   txt:text;
begin 
   // maestro
   Assign(ar_maestro, 'ArchivoMaestro.dat'); 
   Rewrite(ar_maestro); 
   detalle_path := 'path';
   // detalles 
   for i:= 1 to 30 do begin 
      Assign(vD[i], detalle_path + IntToStr(i)); 
      reset(vD[i]);
      leer(vD[i], registros[i]);
   end;

   // archivo txt para los prod de bajo stock 
   Assign(txt, 'stock_bajo.txt');
   Rewrite(txt); 

   // aca arranca lo complicado. Primero el minimo
   minimo(vD, registros, min); 
   while(min.cod <> val_corte) do begin 
      read(ar_maestro,p); 
      while(p.codigo < min.cod) do  
         read(ar_maestro, p);
      // acumulamos 
      while(p.codigo = min.cod) do begin 
         p.stock_dispo := p.stock_dispo - min.cant_vendida; 
         minimo(vD, registros, min); 
      end;
      
      seek(ar_maestro, filepos(ar_maestro)-1);
      write(ar_maestro, p);

      if (p.stock_dispo < p.stock_min) then begin
         WriteLn(txt, p.stock_dispo,' ', p.precio); 
         WriteLn(txt, p.nombre); 
         WriteLn(txt, p.desc);
      end;
   end;

   close(ar_maestro); close(txt);
   for i:= 1 to 30 do close(vD[i]);
end;

//=======================
var 
   ar_maestro:maestro; 
   vD:detalle;
begin 
   crear_maestro(ar_maestro);
   actualizar_maestro(ar_maestro, vD);
end.