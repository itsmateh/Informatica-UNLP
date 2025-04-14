program EJ3; 
const val_corte = 'zzz';
type 
   
   informacion = record 
      nombre_prov:string; 
      cant_personas:integer;
      total_encuestados:integer; 
   end;

   censo = record
      nombre_provincia: string;
      codigo_localidad: integer;
      alfabetizados: integer;
      encuestados: integer;
  end;

   archivoM = file of informacion; 

procedure leer(var detalle:text; var reg:censo);   // como se lee si mi archivo detalle es text? o sea que valor es el parametro var d1: ? 
begin 
   if not eof(detalle) then begin 
      with reg do readln(detalle, codigo_localidad, alfabetizados, encuestados, nombre_provincia); 
   end
   else reg.nombre_provincia := val_corte; 
end;

procedure minimo(var reg1,reg2,curr:censo; var d1,d2:text); 
begin 
   if(reg1.nombre_provincia <= reg2.nombre_provincia) then begin 
      curr := reg1; 
      leer(d1, reg1); 
   end 
   else begin 
      curr := reg2; 
      leer(d2, reg2);
   end;
end;

procedure generar_maestro(var d1,d2:Text; var ar_maestro:archivoM); 
var 
   inf:informacion; 
   cen1,cen2, curr:censo;
begin 
   Assign(ar_maestro, 'ArchivoMaestro'); Assign(d1, 'detalle1.txt'); Assign(d2, 'detalle2.txt');
   reset(d1); reset(d2); Rewrite(ar_maestro); 

   leer(d1, cen1); 
   leer(d2, cen2); 
   minimo(cen1, cen2, curr, d1, d2);
   while(curr.nombre_provincia <> val_corte) do begin  
      inf.nombre_prov := curr.nombre_provincia; 
      inf.cant_personas := 0; 
      inf.total_encuestados := 0; 
      while(curr.nombre_provincia = inf.nombre_prov) do begin 
         inf.cant_personas := inf.cant_personas + curr.alfabetizados;
         inf.total_encuestados := inf.total_encuestados + curr.encuestados; 
         minimo(cen1, cen2, curr, d1, d2);
      end;
      write(ar_maestro, inf);
   end;
   close(d1); close(d2); close(ar_maestro);
end;


//=============================
var 
   d1,d2:text; 
   ar_maestro:archivoM;
begin 
   generar_maestro(d1, d2, ar_maestro)
end.