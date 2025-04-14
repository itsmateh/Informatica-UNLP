use std::io;

fn main() {
    let arr: [&str; 5] = ["Universidad", "Nacional", "de", "La", "Plata"];
    
    let mut entrada = String::new(); 
    println!("Ingresa un texto: "); 
    io::stdin().read_line(&mut entrada).expect("msg");
    let entrada = entrada.trim(); // fuera salto de linea


    let mut check = false; 
    // [&str;5] guarda REFERENCIAS por lo que necestiamos acceder a los valores con *msj
    for msj in &arr{
        if *msj == entrada{
            check = true;
            break;
        } 
    }
    println!("{}", check);
}
