program EJ2;

const 
  val_corte = -1;

type 
  producto = record 
    codigo: integer; 
    nombre: string; 
    precio: real;
    stock_actual: integer; 
    stock_minimo: integer;
  end; 

  venta = record 
    codigo: integer; 
    cantidad: integer; 
  end;

  archivo = file of producto; 
  archivo_detalle = file of venta; 

// ===================
procedure leer(var p: producto); 
begin 
  WriteLn('Codigo: '); 
  ReadLn(p.codigo); 
  if (p.codigo <> -1) then 
  begin 
    WriteLn('Nombre: '); ReadLn(p.nombre); 
    WriteLn('Precio: '); ReadLn(p.precio); 
    WriteLn('Stock: '); ReadLn(p.stock_actual); 
    WriteLn('Stock minimo: '); ReadLn(p.stock_minimo); 
  end;
end;

procedure crear_archivo_maestro(var ar_maestro: archivo); 
var 
  prod: producto; 
begin 
  Assign(ar_maestro, 'ArchivoMaestro');
  Rewrite(ar_maestro); 
  leer(prod); 
  while (prod.codigo <> val_corte) do 
  begin 
    Write(ar_maestro, prod); 
    leer(prod); 
  end; 
  Close(ar_maestro);
end;

// ===================
procedure mostrar_menu(); 
begin 
  WriteLn('==== MENU ====');
  WriteLn('Op. 0 -> Salir');
  WriteLn('Op. 1 -> Crear Archivo Detalle');
  WriteLn('Op. 2 -> Actualizar Archivo Maestro');
  WriteLn('Op. 3 -> Listar productos con stock bajo');
end;

procedure crear_archivo_detalle(var ar_detalle: archivo_detalle); 
var 
  v: venta;
begin 
  Assign(ar_detalle, 'ArchivoDetalle');
  Rewrite(ar_detalle); 

  WriteLn('Codigo de producto: '); 
  ReadLn(v.codigo); 
  while (v.codigo <> val_corte) do 
  begin 
    WriteLn('Cantidad vendida: '); 
    ReadLn(v.cantidad);  
    Write(ar_detalle, v); 
    WriteLn('Codigo de producto: '); 
    ReadLn(v.codigo); 
  end;

  Close(ar_detalle);
end;

procedure leer_detalle(var ar_detalle: archivo_detalle; var v: venta); 
begin 
  if (not eof(ar_detalle)) then 
    Read(ar_detalle, v) 
  else 
    v.codigo := val_corte; 
end;

procedure actualizar_archivo_maestro(var ar_detalle:archivo_detalle; var ar_maestro:archivo); 
var 
  v:venta; 
  p:producto; 
  begin 
    reset(ar_detalle); reset(ar_maestro); 
    leer_detalle(ar_detalle, v); 
    while(v.codigo <> val_corte) do begin 
      read(ar_maestro, p); 
      while(p.codigo <> v.codigo) do read(ar_maestro, p); // el read hace que avance 1 pos 
      while(p.codigo = v.codigo) do begin 
      // modificamos el producto que despues vamos a insertar en maestro
        if(v.cantidad >= p.stock_actual) then p.stock_actual := 0 
        else p.stock_actual := p.stock_actual - v.cantidad; 
          leer_detalle(ar_detalle, v); 
      end;
      // si sale es porque cambio el codigo por lo que actualizamos el maestro 
      seek(ar_maestro, filepos(ar_maestro)-1); 
      write(ar_maestro, p); 
    end;    
    close(ar_detalle); close(ar_maestro);
  end;

procedure listar_productos_stock(var ar_maestro: archivo); 
var 
  p: producto; 
  ar_txt: Text;
begin 
  Assign(ar_maestro, 'ArchivoMaestro');
  Reset(ar_maestro);
  Assign(ar_txt, 'stock_minimo.txt'); 
  Rewrite(ar_txt); 
  while (not eof(ar_maestro)) do 
  begin 
    Read(ar_maestro, p); 
    if (p.stock_actual < p.stock_minimo) then 
      Writeln(ar_txt, 'Codigo=', p.codigo, ' Nombre=', p.nombre, ' Precio=', p.precio:0:2, 
              ' StockActual=', p.stock_actual, ' StockMin=', p.stock_minimo);
  end;
  Close(ar_maestro); 
  Close(ar_txt); 
end;

// =================== Menú de opciones
procedure menu_opciones();
var 
  op: integer;
  ar_detalle: archivo_detalle; 
  ar_maestro: archivo; 
begin 
  mostrar_menu(); 
  ReadLn(op); 
  while (op <> 0) do 
  begin 
    case op of 
      1: crear_archivo_detalle(ar_detalle);
      2: actualizar_archivo_maestro(ar_detalle, ar_maestro); 
      3: listar_productos_stock(ar_maestro); 
    else 
      WriteLn('Opcion no valida.'); 
    end;
    mostrar_menu();
    ReadLn(op);
  end;
end;

// =================== Programa Principal
var 
  ar_maestro: archivo; 
begin 
  crear_archivo_maestro(ar_maestro); 
  menu_opciones(); 
end.
