

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

pub fn es_primo(n:usize) -> bool{
    let criba = criba_eratostenes(n); 
    criba[n]
}