fn main() {
    let mut arr: [i32; 6] = [2, 4, 6, 7, 8, 10];
    println!("Arreglo original: {:?}", arr);

    const FAC:i32 = 2; 

    for num in arr.iter_mut(){
        // num no es un valor directamente sino un ptr mutable 
        *num *= FAC; // desreferenceo la referencia mutable y obtengo el valor de num 
    }

    println!("Arreglo Modificado: {:?}", arr);
}
