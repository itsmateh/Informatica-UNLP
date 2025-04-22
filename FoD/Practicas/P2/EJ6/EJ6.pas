program EJ6; 
const val_corte = 999;
const df = 10;
type 
   info_archivos_municipio = record 
      cod_localidad:integer; 
      cod_cepa:integer;
      casos_activos:integer; 
      casos_nuevos:integer; 
      casos_recuperados:integer; 
      casos_fallecidos:integer; 
   end;

   info_archivo_maestro = record 
      codigo_localidad:integer;
      nombre_localidad:integer;  
      cod_cepa:integer;
      nombre_cepa:integer; 
      casos_activos:integer; 
      casos_nuevos:integer; 
      casos_recuperados:integer;  
      casos_fallecidos:integer;
   end;

   ar_maestro = file of info_archivo_maestro; 
   ar_detalle = file of info_archivos_municipio;
   vDetalles = array[1..df] of ar_detalle;  
   registros =  array[1..df] of info_archivos_municipio;




procedure actualizar_maestro(var maestro:ar_maestro; vD:vDetalles); 
   procedure leer(var det:ar_detalle; var reg:info_archivos_municipio); 
   begin 
      if(not eof(det)) then 
         read(det, reg)
      else reg.cod_localidad := val_corte;
   end;
   
   procedure minimo(min:info_archivos_municipio; vD:vDetalles; regs:registros); // minimo(min, vD, regs)
   var 
      i,pos : integer; 
   begin 
      min.cod_localidad := val_corte;
      for i:= 1 to df do begin 
         if(regs[i].cod_localidad < min.cod_localidad) then
            min.cod_localidad := regs[i].cod_localidad; 
            pos := i;
      end;
      min := regs[pos]; 
      // avanzar a la siguiente posicion 
      leer(vD[pos], regs[pos])
   end;

var 
   regs:registros
   aux,min:info_archivos_municipio
   registro_maestro:info_archivo_maestro;
   acumulador, cant:integer;
begin 

   assign(maestro, 'ArchivoMaestro.dat'); reset(ar_maestro); 
   // abrir todos los archivos detalle
   for i := 1 to df do begin
      assign(vD[i], 'detalles/detalle' + chr(48 + i) + '.dat');
      reset(vD[i]);
      leer(vD[i], regs[i]);
   end;
   

   minimo(min, vD, regs); 
   cant := 0;
   while(min.cod_localidad <> val_corte) do begin 
      aux.cod_localidad := min.cod_localidad;  

      while(aux.cod_localidad = min.cod_localidad) do begin 
         aux.cod_cepa := min.cod_cepa; 
         acumulador := 0;

         while(aux.cod_cepa := min.cod_cepa) do begin 
            aux.casos_fallecidos := aux.casos_fallecidos + min.casos_fallecidos; 
            aux.casos_recuperados := aux.casos_recuperados + min.casos_recuperados; 
            aux.casos_activos := min.casos_activos; 

            acumulador := acumulador+min.casos_activos;

            aux.casos_nuevos := min.casos_nuevos; 
            minimo(min, vD, regs);
         end;  

         // actualizar maestro
         read(mestro, registro_maestro); 
         // busco en el maestro el codigo de localidad
         while(registro_maestro.codigo_localidad <> aux.cod_localidad) do read(maestro, registro_maestro);
         // una vez parado en el codigo de localidad busco la cepa
         while(registo_maestro.cod_cepa <> aux.cod_cepa) do read(maestro, registro_maestro);
         
         // estoy parado en la misma cepa que actualice arriba 
         registro_maestro.casos_fallecidos := aux.casos_fallecidos; 
         registro_maestro.casos_recuperados := aux.casos_recuperados; 
         registro_maestro.casos_activos := aux.casos_activos; 
         registro_maestro.casos_nuevos := aux.casos_nuevos; 
         
         if(acumulador > 50) then cant := cant+1;

         seek(maestro, filepos(maestro)-1);
         write(maestro, registro_maestro);
      end;
   end;
   WriteLn('cantidad de localidades con más de 50 casos activos: ', cant);
end;


begin 


end.