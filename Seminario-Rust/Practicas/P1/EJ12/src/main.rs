fn main() {
    let informacion: (&str, [i32;3]) = ("Ejercicio 12", [1,2,3]);

    // extraemos los valores de la tupla
    let (msj, nums) = informacion; 

    let suma:i32 = nums.iter().sum();

    println!("Mensaje: {}", msj); 
    println!("Valor de la suma: {}", suma);

}
