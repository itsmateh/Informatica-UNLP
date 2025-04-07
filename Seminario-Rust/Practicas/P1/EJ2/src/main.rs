fn main() {
   let valor:u32 = 11; 
   println!("El valor {}  en Hexadecimal: ", valor);  
   println!("{:x}", valor); // minusculas
   println!("{:X}", valor); // mayusculas

   let valor_hexa_min = format!("{:x}", valor);
   let valor_hexa_may = format!("{:X}", valor);  
   println!("{}", valor_hexa_min); 
   println!("{}", valor_hexa_may); 
   
}
