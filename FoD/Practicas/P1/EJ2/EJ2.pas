program EJ2; 

type  
   archivo = file of integer; 

procedure estadisticas_archivo(var ar_logico:archivo; var cant:integer; var prom:real);
var num:integer;
begin 
   while(not EOF(ar_logico)) do begin 
      read(ar_logico, num); 
      if(num < 1500) then cant+=1;   
      prom += num;
   end;
   prom := prom/FileSize(ar_logico);
end;

var 
   ar_logico:archivo; 
   ar_nombre:string; 
   cant:integer;
   prom:real;
begin 
   write('Nombre del archivo: '); 
   readln(ar_nombre); 
   Assign(ar_logico, ar_nombre); 
   reset(ar_logico);
   cant := 0; prom := 0;  
   estadisticas_archivo(ar_logico, cant, prom);
   writeln('Cantdad de numeros menores a 1500: ', cant);
   writeln('Promedio: ', prom);
end.

