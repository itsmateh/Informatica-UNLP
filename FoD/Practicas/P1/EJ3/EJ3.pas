program EJ3;
 
type 
   empleado = record
      nro:integer; 
      apellido:string; 
      nombre:string;
      edad:integer;
      dni:string;
   end;

   archivo = file of empleado; 


// A
procedure cargar_archivo(var ar_logico:archivo); 
   procedure leer(var e:empleado);
   begin 
      Writeln('Nro. del empleado: '); 
      readln(e.nro); 


      Writeln('Apellido del empleado: '); 
      readln(e.apellido); 



      Writeln('Nombre del empleado: '); 
      readln(e.nombre);


      
      Writeln('Edad del empleado: '); 
      readln(e.edad); 
      

      Writeln('DNI del empleado: '); 
      readln(e.dni); 

   end;

var 
   emp:empleado;
   ar_nombre:string[20];
begin 
   writeln('Nombre del archivo: ');
   readln(ar_nombre);
   Assign(ar_logico, ar_nombre);
   Rewrite(ar_logico);
   leer(emp);
   while(emp.apellido <> 'fin') do begin 
      write(ar_logico, emp);
      leer(emp);
   end;
   Close(ar_logico);

end;


// B
procedure menu_opciones(var ar_logico:archivo);

   procedure mostrar_opciones(); 
   begin 
      WriteLn('Op. 1 -> Listar en pantalla los datos de empleados que tengan un nombre o apellido determinado.');
      WriteLn('Op. 2 -> Listar en pantalla los empleados de a uno por linea.');
      WriteLn('Op. 3 -> Listar en pantalla los empleados mayores de 70 años, proximos a jubilarse.');
      WriteLn('Op. 0 -> Salir del programa.');
      WriteLn('Elija una opcion: ');
   end;

   // opcion 1
   procedure mostrar_empleados(e:empleado);
   begin 
      WriteLn('Nombre: ', e.nombre, ' Apellido: ', e.apellido, ' DNI: ', e.dni, ' Edad: ', e.edad, ' Nro. de empleado: ', e.nro);
   end;

   procedure datos_empleado(var ar_logico:archivo); 
   var 
      txt:string[20];
      emp:empleado;
   begin 
      Write('Nombre o apellido: ');
      read(txt);
      Reset(ar_logico);

      while(not EOF(ar_logico))do begin 
         read(ar_logico, emp);
         if(emp.nombre = txt) or (emp.apellido = txt) then mostrar_empleados(emp);
      end;

      Close(ar_logico);
   end;

   // Opcion 2
   procedure listado_empleados(var ar_logico:archivo);
   var emp:empleado;
   begin 
      Writeln('Listado Completo: ');
      Reset(ar_logico);
      while(not EOF(ar_logico)) do begin 
         read(ar_logico, emp);
         mostrar_empleados(emp);
      end;

      Close(ar_logico);
   end;

   // Opcion 3
   procedure listado_mayores70(var ar_logico:archivo);
   var emp:empleado; 
   begin 
      WriteLn('Empleados mayores de 70 años: '); 
      Reset(ar_logico);
      while(not EOF(ar_logico)) do begin 
         read(ar_logico, emp); 
         if(emp.edad > 70) then 
            mostrar_empleados(emp);
      end;

      Close(ar_logico);
   end;

var 
   op:integer;
begin 
   WriteLn('========== MENU DE OPCIONES =========='); 
   mostrar_opciones(); 
   readln(op); 
   while(op <> 0) do begin 
      case op of 
         1: begin datos_empleado(ar_logico); end;
         2: begin listado_empleados(ar_logico); end;
         3: begin listado_mayores70(ar_logico); end;
      else 
         Writeln('Eleccion no valida, intentelo de nuevo.');
      end;

      WriteLn;
      Writeln('====================');
      mostrar_opciones();
      readln(op);
   end;
end;


var 
   ar_logico:archivo;
begin
   cargar_archivo(ar_logico);   
   menu_opciones(ar_logico);
end.