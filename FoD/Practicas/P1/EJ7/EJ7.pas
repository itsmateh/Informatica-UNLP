program EJ7; 

type 
   novela = record 
      cod:integer; 
      nombre:string; 
      genero:string;
      precio:real; 
   end;

   archivo = file of novela; 


procedure mostrar_opciones(); 
begin 
   WriteLn('Op. 1 -> Agregar  una  novela.');
   WriteLn('Op. 2 -> Modificar una existente');   
   WriteLn('Op. 0 -> Salir.');
   WriteLn('Elija una opcion: ');
end;


// A 
procedure cargar_informacion(var ar_logico:archivo; var ar_txt:Text); 
var 
   nov:novela; 
   ar_fisico:string;
begin 
   WriteLn('Nombre del archivo a crear: '); 
   readln(ar_fisico); 
   Assign(ar_logico, ar_fisico); 
   Rewrite(ar_logico); 
   reset(ar_txt); 
   while(not eof(ar_txt)) do begin 
      with nov do begin 
         ReadLn(ar_txt, cod, precio, genero); 
         ReadLn(ar_txt, nombre);
         write(ar_logico, nov);
      end;  
   end;
   WriteLn('Realizado!');
   close(ar_txt);
   close(ar_logico); 
end;

// B - Op.1
procedure mas_datos(var n:novela); 
begin 
   WriteLn('Nombre: '); 
   ReadLn(n.nombre);

   WriteLn('Genero: '); 
   ReadLn(n.genero);

   WriteLn('Precio: '); 
   ReadLn(n.precio);
end;

procedure leer_novela(var n:novela); 
begin 
   WriteLn('Codigo: '); 
   ReadLn(n.cod);

   if(n.cod <> -1) then begin 
      mas_datos(n);
   end;
end;

procedure agregar_novela(var ar_logico:archivo); 
var 
   nov:novela; 
begin 
   reset(ar_logico);
   leer_novela(nov); 
   seek(ar_logico, FileSize(ar_logico)); 
   while(nov.cod <> -1) do begin 
      write(ar_logico, nov); 
      leer_novela(nov);
   end;
   close(ar_logico); 
end;

// B - Op.2
procedure modificar_novela(var ar_logico:archivo); 
var 
   nov:novela; 
   cod:integer;
   check:boolean;
begin 
   reset(ar_logico); 
   WriteLn('Codigo de novela a modificar: '); 
   ReadLn(cod); 
   check := false;
   while(not eof(ar_logico) and not(check)) do begin 
      read(ar_logico, nov); 
      if(nov.cod = cod) then begin
         check := true;
         mas_datos(nov);
         seek(ar_logico, FilePos(ar_logico)-1);
         write(ar_logico, nov);
         WriteLn('Realizado!');
      end;
   end;
   close(ar_logico);
end;

procedure menu_novela(var ar_logico:archivo); 
var 
   op:integer; 
   ar_txt:Text;
begin 
   Assign(ar_txt, 'novelas.txt');
   cargar_informacion(ar_logico, ar_txt); 
   WriteLn('MENU'); 
   mostrar_opciones(); 
   readln(op); 
   while(op <> 0) do begin 
      case op of 
         1: agregar_novela(ar_logico); 
         2: modificar_novela(ar_logico);  
      else WriteLn('Opcion no valida');
      end;
      mostrar_opciones(); 
      readln(op); 
   end; 
end;

var 
   ar_logico:archivo;
begin 
   menu_novela(ar_logico);   
end.