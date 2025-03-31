use std::io;


fn main() {
    // slice string pq el string no cambia
    const TXT:&str = "Universidad Nacional de La Plata"; 
    println!("Cadena: {}", TXT); 

    // leer caracter
    let mut entrada = String::new(); 
    println!("Ingrese un caracter para ver su ocurrencia: ");
    io::stdin().read_line(&mut entrada).expect("msg");
    
    // conversion string -> char
    let caracter = entrada.chars().next().expect("string vacio!!");

    let mut contador = 0;
    for c in TXT.chars(){
        if c == caracter{
            contador+=1;
        }
    }
    println!("Se repitio el caracter {} veces",contador);
}
