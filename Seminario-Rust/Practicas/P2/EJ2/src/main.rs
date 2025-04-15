use std::{io, vec};

fn criba_eratostenes(lim:usize) -> Vec<bool>{
    // usando macro vec!
    let mut primos = vec![true; lim+1];
    primos[0] = false; 
    primos[1] = false;

    for i in 2..=lim{
        if primos[i]{
            let mut j = i*2; 
            while j < lim{
                primos[j] = false;
                j += i;
            }
        }
    }

    return primos
}

fn es_primo(n:usize) -> bool{
    let criba = criba_eratostenes(n); 
    criba[n]
}
fn main() {
    
    println!("Numero: "); 
    let mut input = String::new(); 
    io::stdin().read_line(&mut input).expect("msg"); 


    let input = input.trim();
    let num:usize = match input.parse(){
        Ok(n) => n, 
        Err(_) => return
    };


    println!("Es primo? -> {}", es_primo(num));
}
