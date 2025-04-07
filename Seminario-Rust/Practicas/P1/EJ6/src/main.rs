use std::io;

fn main() {
    let valor_base:u32 = 10; 

    println!("Escriba un numero: "); 

    let mut entrada = String::new(); 
    io::stdin().read_line(&mut entrada).expect("msg"); 


    // conversion 
    let valor_ingresado:i32 = match entrada.trim().parse(){
        Ok(val) => val, 
        Err(_) => {
            println!("Entrada no valida"); 
            return; 
        }
    };

    let suma_valores = valor_base as i32 + valor_ingresado; 
    let ans = suma_valores.pow(2);
    println!("Resultado: {}", ans);


}