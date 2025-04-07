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
      WriteLn('Op. 5 -> Añadir  uno  o  más  celulares  al final del archivo con sus datos ingresados por teclado.');
      WriteLn('Op. 6 -> Modificar el stock de un celular dado.');     
      WriteLn('Op. 7 -> Exportar el contenido del archivo binario a un archivo de texto denominado: ”SinStock.txt”, con aquellos celulares que tengan stock 0.');
      WriteLn('Op. 0 -> Salir.');
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

   // A - EJ6
   procedure pedir_celular(var c:celular); 
   begin 
      with c do begin
         WriteLn('Codigo: '); ReadLn(cod);
         WriteLn('Nombre: '); ReadLn(nombre);
         WriteLn('Descripcion: '); ReadLn(desc);
         WriteLn('Marca: '); ReadLn(Marca);
         WriteLn('Precio: '); ReadLn(Precio);
         WriteLn('Stock Minimo: '); ReadLn(stock_min);
         WriteLn('Stock: '); ReadLn(stock);
      end; 
   end;

   procedure aniadir_celular(var ar_logico:archivo); 
   var 
      c:celular; 
      check:boolean;
      txt:string;
   begin 
      WriteLn('Ingrese los datos del celular: '); 
      reset(ar_logico); 
      seek(ar_logico, FileSize(ar_logico)); 
      pedir_celular(c); 
      check := true; 
      while(check) do begin 
         write(ar_logico, c); 
         WriteLn('A: agregar otro');
         WriteLn('S: salir'); 
         readln(txt); 
         if(txt='S') then check := false
         else pedir_celular(c);
      end;
      Close(ar_logico); 
   end;

   // B - EJ6
   procedure modificar_stock(var ar_logico:archivo); 
   var 
      c:celular;
      check:boolean; 
      nombre_cel:string;
      stock_nuevo:integer; 
   begin 
      reset(ar_logico);
      WriteLn('Ingrese el nombre del celular a modificar: '); 
      readln(nombre_cel); 
      check := false;
      while(not eof(ar_logico) and (not check)) do begin 
         read(ar_logico, c); 
         if(c.nombre = nombre_cel) then begin
            check := true; 
            WriteLn('Nuevo stock: '); 
            ReadLn(stock_nuevo); 
            seek(ar_logico, FilePos(ar_logico)-1); 
            c.stock := stock_nuevo; 
            Write(ar_logico, c); 
            WriteLn('Realizado!'); 
         end;
      end;
      Close(ar_logico); 
      if(not check) then Write('No se encontro el celular con ese nomnbre.');
   end;

   // C - EJ6
   procedure exportar_archivo(var ar_logico:archivo);
   var 
      ar_txt:Text; 
      c:celular; 
   begin 
      reset(ar_logico); 
      assign(ar_txt,'inStock.txt' ); 
      rewrite(ar_txt); 
      while(not eof(ar_logico)) do begin 
         read(ar_logico, c); 
         if(c.cod = 0) then begin 
            with c do begin 
               writeln(ar_txt, cod, ' ', precio, ' ', marca);
               writeln(ar_txt, stock, ' ', stock_min, ' ', desc);
               writeln(ar_txt, nombre);
            end;
         end;
      end;
      Close(ar_txt); 
      Close(ar_logico); 
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
         5: aniadir_celular(ar_logico); 
         6: modificar_stock(ar_logico); 
         7: exportar_archivo(ar_logico); 
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
