use std::io; 
fn main() {
    let valor_base:bool = true; 
    let mut entrada = String::new(); 

    println!("Ingrese un valor booleano: "); 
    io::stdin().read_line(&mut entrada).expect("error");

    // parseo 
    let valor_usuario:bool = match entrada.trim().parse(){
        Ok(val) => val,
        Err(_) => {
            println!("Error. Ingrese 'true' o 'false'"); 
            return;
        }
    }; 

    println!("Resultado con AND {}", valor_usuario && valor_base); 
    println!("Resultado con OR {}", valor_usuario || valor_base);
}
