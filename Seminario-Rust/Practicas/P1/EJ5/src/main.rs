use std::io::{self, Read}; 

fn main() {
    let mut valor_base = String::from("Hola soy ");
    
    println!("Ingresa tu nombre: "); 
    let mut entrada = String::new(); 
    io::stdin().read_line(&mut entrada).expect("Error"); 
    
    let nombre = entrada.trim(); // saco el salto de linea
    valor_base.push_str(nombre);
    println!("{}", valor_base.to_uppercase());
}
