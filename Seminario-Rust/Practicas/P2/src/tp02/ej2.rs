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

#[test]
fn test_es_primo(){
    assert_eq!(es_primo(2), true); 
    assert_eq!(es_primo(3), true); 
    assert_eq!(es_primo(104729), true); 
}