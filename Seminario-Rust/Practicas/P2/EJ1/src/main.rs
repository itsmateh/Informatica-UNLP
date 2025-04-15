use std::io;

fn es_par(num:i32) -> bool{return num%2 == 0}
fn main() {
    let mut input = String::new(); 
    println!("Numero: ");
    io::stdin().read_line(&mut input).expect("msg");

    let input = input.trim(); 
    let num:i32 = match input.parse(){
        Ok(n) => n,
        Err(_) => return
    };

    println!("Es par? -> {}", es_par(num));
}
