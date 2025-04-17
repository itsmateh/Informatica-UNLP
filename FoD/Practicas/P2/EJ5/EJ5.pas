program EJ5;
const
  val_corte = 999;
  df = 5;

type
  sesion = record
    cod_usuario: integer;
    fecha: string[10]; // formato yyyy-mm-dd
    tiempo_sesion: integer; // minutos
  end;

  ar_mae = file of sesion;
  ar_det = file of sesion;

  vSesiones = array[1..df] of ar_det;
  vRegistros = array[1..df] of sesion;

// ===================== PROCEDIMIENTO: crear archivos binarios =====================
procedure crear_detalles_binarios();
var
  ar_txt: Text;
  ar_binario: ar_det;
  registro: sesion;
  i: integer;
  nombre_txt, nombre_binario: string;
begin
  for i := 1 to df do
  begin
   // creamos los nombres
    str(i, nombre_txt);
    nombre_txt := 'detalles/detalle' + nombre_txt + '.txt';
    nombre_binario := 'detalles/detalle' + chr(48 + i) + '.dat'; 

   // abrimos los archivos
    assign(ar_txt, nombre_txt); reset(ar_txt); 
    assign(ar_binario, nombre_binario); rewrite(ar_binario);

    while not eof(ar_txt) do
    begin
      readln(ar_txt, registro.cod_usuario, registro.tiempo_sesion, registro.fecha);
      write(ar_binario, registro);
    end;

    close(ar_txt);
    close(ar_binario);
  end;

  writeln('Conversion de archivos txt a binarios realizada !!!!');
end;

// ===================== PROCEDIMIENTO: crear archivo maestro =====================
procedure crear_archivo_maestro(var maestro: ar_mae; var vSes: vSesiones);
var
  registros: vRegistros;
  reg_aux, min: sesion;
  i, pos: integer;

  procedure leer(var archivo: ar_det; var reg: sesion);
  begin
    if not eof(archivo) then
      read(archivo, reg)
    else
      reg.cod_usuario := val_corte;
  end;

  procedure minimo(var registros: vRegistros; var min: sesion; var vSes: vSesiones; var pos: integer);
  var
    i: integer;
  begin
    min.cod_usuario := val_corte;
    min.fecha := 'ZZZZZZZZZZ';
    for i := 1 to df do
    begin
      if (registros[i].cod_usuario < min.cod_usuario) or 
         ((registros[i].cod_usuario = min.cod_usuario) and (registros[i].fecha < min.fecha)) then
      begin
        min := registros[i];
        pos := i;
      end;
    end;

    if min.cod_usuario <> val_corte then
      leer(vSes[pos], registros[pos]);
  end;

begin
  assign(maestro, 'ArchivoMaestro.dat');
  rewrite(maestro);

  // abrir todos los archivos detalle
  for i := 1 to df do
  begin
    assign(vSes[i], 'detalles/detalle' + chr(48 + i) + '.dat');
    reset(vSes[i]);
    leer(vSes[i], registros[i]);
  end;

  minimo(registros, min, vSes, pos);
  while min.cod_usuario <> val_corte do
  begin
    reg_aux.cod_usuario := min.cod_usuario;

    while min.cod_usuario = reg_aux.cod_usuario do
    begin
      reg_aux.fecha := min.fecha;
      reg_aux.tiempo_sesion := 0;

      while (min.cod_usuario = reg_aux.cod_usuario) and (min.fecha = reg_aux.fecha) do
      begin
        reg_aux.tiempo_sesion := reg_aux.tiempo_sesion + min.tiempo_sesion;
        minimo(registros, min, vSes, pos);
      end;

      write(maestro, reg_aux);
    end;
  end;

  close(maestro);
  for i := 1 to df do
    close(vSes[i]);
end;

// ===================== PROGRAMA PRINCIPAL =====================
var
  maestro: ar_mae;
  detalles: vSesiones;
begin
  crear_detalles_binarios();
  crear_archivo_maestro(maestro, detalles);
  writeln('listo capo se creo el Archivo Maestro pasa al siguiente problema');
end.
