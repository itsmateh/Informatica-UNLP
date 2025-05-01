procedure baja_archivo(var ar:tArchFlores; f:reg_flor); 
// var   
//    flor, cabecera:reg_flor; 
// begin 
//    reset(ar);
//    read(ar, cabecera);

//    // busco en el archivo la flor
//    read(ar, flor);
//    while(not eof(ar) and (flor.nombre <> f.nombre)) do read(ar, flor);    

//    if(f.codigo = flor.codigo) then begin 
//       seek(ar, FilePos(ar)-1); // retrocedo porque estoy en +1 pos adelantado por el read
//       write(ar, cabecera); // lo de cabecera lo escribo aca
      
//       cabecera.codigo := (FilePos(ar)-1) * -1; // me guardo la posicion para despues ponerla en cabecera
//       seek(ar, 0); // voy a la pos de cabecera
//       write(ar, cabecera); // escribo en cabecera lo que me guarde antes (-Pos_Borrada);
      
//       WriteLn('Flor eliminada');
//    end
//    else WriteLn('No se encontro la flor'); 
   
//    close(ar);
// end;