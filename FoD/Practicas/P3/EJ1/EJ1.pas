program EJ4; 
type 
   empleado = record
      nro:integer; 
      apellido:string; 
      nombre:string;
      edad:integer;
      dni:string;
   end;

   archivo = file of empleado; 

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


procedure cargar_archivo(var ar_logico:archivo); 
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
      WriteLn('Op. 4 -> Añadir uno o más empleados al final del archivo.');
      WriteLn('Op. 5 -> Modificar la edad de un empleado dado.');
      WriteLn('Op. 6 -> Exportar  el  contenido.');
      WriteLn('Op. 7 -> Exportar a un archivo los empleados que no tengan cargado el DNI (DNI en 00).');
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

   // Opcion 4 - A
   function control_unicidad(var ar_logico:archivo; nro:integer):boolean; 
   var 
      check:boolean;
      emp:empleado;
   begin 
      check := true; 
      while(not EOF(ar_logico) and check) do begin 
         read(ar_logico, emp); 
         if(emp.nro = nro) then check := false;
      end;
      control_unicidad := check;
   end;

   procedure aniadir_empleados(var ar_logico:archivo);
   var 
      emp:empleado; 
   begin 
      reset(ar_logico); 
      leer(emp); 
      while(emp.apellido <> 'fin') do begin 
         if(control_unicidad(ar_logico, emp.nro)) then begin 
            seek(ar_logico, FilePos(ar_logico)); 
            write(ar_logico, emp);
         end;
         leer(emp);
      end;
      Close(ar_logico);
   end;

   // Opcion 5 - B
   procedure modificar_edad(var ar_logico:archivo); 
   var 
      emp:empleado; 
      edad:integer;
      nro:integer;
      check:boolean;
   begin 
      reset(ar_logico);
      Writeln('Nro. del empleado: '); 
      readln(nro); 
      check := false;
      while(not EOF(ar_logico) and (not check)) do begin 
         read(ar_logico, emp); 
         if(emp.nro = nro) then check := true;
         WriteLn('Edad nueva: '); 
         readln(edad);
         emp.edad := edad; 
      end;

      if(not check) then WriteLn('Nro. No encontrado')
      else WriteLn('Actualizado!');
      Close(ar_logico);
   end;

   // Opcion 6 - C
   procedure exportar_archivo(var ar_logico:archivo); 
   var 
      emp:empleado;
      ar_txt:Text;
   begin 
      Assign(ar_txt, 'todos_empleados.txt');
      Rewrite(ar_txt);
      reset(ar_logico);
      while(not EOF(ar_logico)) do begin 
         read(ar_logico, emp);
         with emp do WriteLn(ar_txt,' ', nro, ' ', apellido, ' ', nombre, ' ', edad, ' ', dni);
      end;
      WriteLn('Exportado!');
      Close(ar_txt);
      Close(ar_logico);
   end;

   // Opcion 7 - D
   procedure exportar_archivo_dni(var ar_logico:archivo);
   var 
      ar_txt_dni:Text; 
      emp:empleado;
   begin 
      Assign(ar_txt_dni, 'altaDNIEmpleado.txt');
      Rewrite(ar_txt_dni); 
      reset(ar_logico); 
      while(not EOF(ar_logico)) do begin 
         read(ar_logico, emp); 
         if(emp.dni = '00') then  
            with emp do WriteLn(ar_txt_dni,' ', nro, ' ', apellido, ' ', nombre, ' ', edad, ' ', dni);
      end;
      WriteLn('Exportado!');
      Close(ar_txt_dni);
      Close(ar_logico);
   end;

   // opcion 8
   procedure borrar_registro(var ar_logico:archivo);
   var 
      cod_eliminar:integer;
      emp:empleado;
      pos_cod_eliminar:integer;
   begin 
      reset(ar_logico);
      WriteLn('Numero de empleado a borrar: ', cod_eliminar);
      ReadLn(cod_eliminar);

      read(ar_logico, emp);
      while(not eof(ar_logico) and emp.nro <> cod_eliminar) do begin 
         read(ar_logico, emp);
         if(emp.cod_eliminar = cod_eliminar) then pos_cod_eliminar := FilePos(ar_logico);
      end;

      // me paro en la ult. pos del archivo
      FilePos(ar_logico, FileSize(ar_logico)-1);  
      // leo el empleado para guardarmelo 
      read(ar_logico, emp);
      // como al leer avanzo 1, retrocedo
      FilePos(ar_logico, FilePos(ar_logico)-1);
      // truncamos desde la posicio donde estoy parado
      Truncate(ar_logico);
      seek(ar_logico, pos_cod_eliminar);
      write(ar_logico, emp);

      // CERRAR ARCHIVOS !!!
      close(ar_logico);
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
         4: begin aniadir_empleados(ar_logico); end;
         5: begin modificar_edad(ar_logico); end;
         6: begin exportar_archivo(ar_logico); end;
         7: begin exportar_archivo_dni(ar_logico); end;
         8: begin borrar_registro(ar_logico); end;
      else 
         Writeln('Eleccion no valida, intentelo de nuevo.');
      end;

      WriteLn;
      WriteLn('========== MENU DE OPCIONES =========='); 
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