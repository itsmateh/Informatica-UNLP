program ej1; 
const valorCorte = -1; 
type 
   empleado = record 
      codigo:integer; 
      nombre:string; 
      monto:real;
   end;

   archivo = file of empleado; 


// seccion -> crear archivo InformacionEmpleados

procedure leer_empleado(var e:empleado); 
begin 
   WriteLn('Codigo: '); ReadLn(e.codigo);
   WriteLn('Nombre: '); ReadLn(e.nombre);
   WriteLn('Monto: '); ReadLn(e.monto);
end;

procedure crearArchivo(var ar:archivo); 
var 
   emp:empleado; 
begin  
   Assign(ar,'InformacionEmpleados'); 
   Rewrite(ar); 
   leer_empleado(emp); 
   while(emp.codigo <> -777) do begin 
      write(ar, emp);
      leer_empleado(emp); 
   end;
   WriteLn('Archivo binario realizado!'); 
   close(ar);
end;


// seccion -> crear archivo InformacionEmpleadosCompactada
procedure leer(var ar:archivo; var emp:empleado); 
begin 
   if(not eof(ar)) then read(ar, emp)
   else emp.codigo := valorCorte; 

end;

procedure compactarArchivo(var ar:archivo; var ar_maestro:archivo); 
var
   emp, emple:empleado;
   montoTotal:real; 
begin 
   Assign(ar_maestro, 'InformacionEmpleadoCompactada');
   reset(ar); Rewrite(ar_maestro); 
   leer(ar, emp); 
   while(emp.codigo <> valorCorte) do begin 
      emple := emp; 
      montoTotal := 0; 
      while(emp.codigo = emple.codigo) do begin 
         montoTotal := montoTotal + emple.monto; 
         leer(ar, emp);
      end;
      emple.monto := montoTotal; 
      write(ar_maestro, emple);
   end;
   close(ar_maestro); 
   close(ar);
end;

var 
   ar, ar_maestro:archivo; 

begin 
   crearArchivo(ar); 
   compactarArchivo(ar, ar_maestro);
end.