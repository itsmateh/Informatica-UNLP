use std::io; 

fn main() {
    let valor_base:f32 = 90.5;
    let mut entrada = String::new(); 
    println!("Ingresa un valor: "); 
    io::stdin().read_line(&mut entrada).expect("error"); 

    let valor_entrada:f32 = match entrada.trim().parse(){
        Ok(num) => num, 
        Err(_) => {
            println!("Entrada invalida. Ingresa un numero decimal valido.");
            return;
        }
    }; 


    println!("Suma: {}", valor_base + valor_entrada); 
    println!("Resta: {}", valor_base - valor_entrada); 
    println!("Multiplicacion: {}", valor_base * valor_entrada); 
    println!("Division: {}", valor_base / valor_entrada); 
}
