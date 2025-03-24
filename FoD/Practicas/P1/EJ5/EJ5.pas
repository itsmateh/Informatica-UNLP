program EJ5; 

type 
   celular = record 
      cod:integer; 
      nombre:string; 
      desc:string; 
      marca:string; 
      precio:real;
      stock_min:integer;
      stock:integer;
   end;

   archivo = file of celular; 




   procedure mostrar_opciones(); 
   begin 
      WriteLn('Op. 1 -> Crear un archivo de registros no ordenados de celulares.');
      WriteLn('Op. 2 -> Listar en pantalla los datos de aquellos celulares que tengan un stock menor al stock mínimo.');
      WriteLn('Op. 3 -> Listar  en  pantalla  los  celulares  del  archivo  cuya  descripción  contenga  una cadena de caracteres proporcionada por el usuario.');
      WriteLn('Op. 4 -> Exportar el archivo creado en la Op. 1.');
      WriteLn('Op. 0 -> Salir del programa.');
      WriteLn('Elija una opcion: ');
   end;

   // A
   procedure crear_archivo(var ar_logico:archivo; var cel:Text); 
   var 
      c:celular;
      nombre_ar:string;
   begin 
      WriteLn('Nombre del archivo: '); 
      readln(nombre_ar); 
      Assign(ar_logico, nombre_ar); 
      Reset(cel); 
      Rewrite(ar_logico); 
      while(not eof(cel)) do begin 
         with c do begin 
            readln(cel, cod, precio, marca);
            readln(cel, stock, stock_min, desc);
            readln(cel, nombre);
            write(ar_logico, c);
         end;
      end;
      WriteLn('Archivo hecho!');
      Close(ar_logico);
      Close(cel);
   end;

   // B
   procedure mostrar_celular(c:celular); 
   begin 
      WriteLn('Codigo: ', c.cod, ' Nombre: ', c.nombre, ' Descripcion: ', 
      c.desc, ' Marca: ', c.marca, ' Precio: ', c.precio, 'S tock: ', c.stock);
   end;

   procedure listar_min_stock(var ar_logico:archivo); 
   var c:celular;
   begin 
      reset(ar_logico); 
      while(not eof(ar_logico)) do begin 
         read(ar_logico,c); 
         if(c.stock_min > c.stock) then mostrar_celular(c);
      end;
      close(ar_logico); 
   end;

   // C
   procedure listar_desc(var ar_logico:archivo); 
   var 
      txt:string; 
      c:celular;

   begin 
      Writeln('Descripcion: '); 
      reset(ar_logico);
      readln(txt); 
      while(not eof(ar_logico)) do begin 
         read(ar_logico, c); 
         if(c.desc = txt) then mostrar_celular(c);
      end;
      close(ar_logico);
   end;

   // D
   procedure exportar_archivo_texto(var ar_logico:archivo; var cel:Text); 
   var 
      c:celular; 
   begin 
      Reset(ar_logico); Rewrite(cel); 
      while(not eof(ar_logico)) do begin 
         read(ar_logico, c); 
         with c do 
            writeln(cel, cod, ' ', 
                    precio, ' ', marca,' ', stock, ' ', 
                    stock_min, ' ', desc, ' ',nombre);

      end;
      Close(ar_logico);
      Close(cel);
   end;

procedure menu_opciones(var ar_logico:archivo; var cel:Text);
var 
   op:integer;
begin
   WriteLn('========== MENU DE OPCIONES =========='); 
   mostrar_opciones(); 
   readln(op);
   while(op <> 0) do begin 
         case op of  
         1: crear_archivo(ar_logico, cel); 
         2: listar_min_stock(ar_logico);
         3: listar_desc(ar_logico);
         4: exportar_archivo_texto(ar_logico, cel); 
      else 
         WriteLn('Opcion no disponible'); 
      end;
      WriteLn('========== MENU DE OPCIONES =========='); 
      mostrar_opciones();
      readln(op);
   end;
end;

var 
   ar_logico:archivo;
   cel:Text; 
begin 
   Assign(cel, 'celulares.txt');
   menu_opciones(ar_logico, cel); 
end.
