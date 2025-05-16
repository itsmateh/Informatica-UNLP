program EJ8; 
const val_corte = 9999;
type 
// nombre, año de lanzamiento, número de versión  del  kernel,  
// cantidad  de  desarrolladores  y  descripción.
   distribucion = record 
      nombre:string;
      lanzamiento:integer;
      version:integer;
      desarrolladores:integer;
      desc:string;
   end;

   archivo = file of distribucion;

// supongamos que tenemos el archivo ya armado 


procedure leer(var a:archivo; var d:distribucion);
begin 
   if(not eof(a)) then read(a,d)
   else d.lanzamiento := val_corte;
end;

// A
function BuscarDistribucion(var a:archivo; nombre:string):integer;
var 
   dis:distribucion;
   encontre:Boolean;
begin 
   reset(a);

   leer(a,dis);
   encontre := false;
   while(not eof(a) and not encontre) do begin 
      if(a.nombre = nombre) then begin 
       encontre = true;
       BuscarDistribucion := FilePos(a)-1;
      end;

      leer(a,d);
   end;

   if(encontre = false) then BuscarDistribucion := -1;
   
   close(a);
end;

// B
procedure AltaDistribucion(var a:archivo; dis:distribucion); 
var 
   cabecera:distribucion;
begin 
   reset(a);


   if(BuscarDistribucion(a, dis.nombre) = -1) then begin 
      leer(a, cabecera);
      if(cabecera.desarrolladores = 0) then begin 
         seek(a, filepos(a)); 
         write(a, dis);
      end

      else begin 
         seek(a, cabecera.desarrolladores * -1); 
         read(a, cabecera);
         seek(a, filepos(a)-1);
         write(a, dis);

         seek(a, 0);
         write(a, cabecera);
      end;

      WriteLn('agregado');
   end   
   else WriteLn('ya existe la distribucion');


   close(a);
end;


// C 
procedure BajaDistribucion(var a:archivo; nombre:String); 
var 
   cabecera,dis:distribucion;
begin 
   reset(a);

   read(a, cabecera);
   check := false;
   if(BuscarDistribucion(a, dis.nombre) = -1) then begin 
      leer(a, dis);
      while(not eof(a) and not check) do begin 
         if(dis.nombre = nombre) then begin 
            check := true;
            seek(a, filepos(a)-1);
            write(a, cabecera);
            

            cabecera.desarrolladores = (FilePos(a)-1) * -1;
            seek(a, 0);
            write(a, cabecera);
         end;
      end;
      WriteLn('eliminado')
   end
   else WriteLn('Distribución no existente');

   close(a);
end;

var 


begin 

end.