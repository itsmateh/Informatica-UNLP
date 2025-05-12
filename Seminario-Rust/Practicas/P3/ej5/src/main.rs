struct Producto{
    nombre:String,
    precio_bruto:f32, 
    id:u32,
}

impl Producto{
    fn new(nombre:String, precio_bruto:f32, id:u32) -> Producto {
        return Producto{nombre, precio_bruto, id}
    }

    fn calcular_impuestos(&self, imp:f32) -> f32 {
        return self.precio_bruto*(imp/100.0);
    }

    fn aplicar_descuento(&self, desc:f32) -> f32 {
        return self.precio_bruto * (1.0-desc/100.0);
    }

    fn calcular_precio_total(&self, imp:f32, desc:f32) -> f32 {
        return self.precio_bruto + self.calcular_impuestos(imp) + self.aplicar_descuento(desc);
    }
}   

fn main() {
    println!("Hello, world!");
}
