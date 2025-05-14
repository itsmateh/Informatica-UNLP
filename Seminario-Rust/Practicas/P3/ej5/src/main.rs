struct Producto{
    nombre:String,
    precio_bruto:f32, 
    id:u32,
}

impl Producto{
    pub fn new(nombre:String, precio_bruto:f32, id:u32) -> Producto {
        return Producto{nombre, precio_bruto, id}
    }

    pub fn calcular_impuestos(&self, imp:f32) -> f32 {
        return self.precio_bruto*(imp/100.0);
    }

    pub fn aplicar_descuento(&self, desc:f32) -> f32 {
        return self.precio_bruto * (1.0-desc/100.0);
    }

    pub fn calcular_precio_total(&self, imp:f32, desc:f32) -> f32 {
        let precio_desc:f32 = self.aplicar_descuento(desc);
        let imp_totales:f32 = self.calcular_impuestos(imp);
        return precio_desc + imp_totales    
    }
}   
#[cfg(test)]
mod test{
    use super::*;

    #[test]
    fn test_new_producto() {
        let p = Producto::new("Mate".to_string(), 100.0, 1);
        assert_eq!(p.nombre, "Mate");
        assert_eq!(p.precio_bruto, 100.0);
        assert_eq!(p.id, 1);
    }

    #[test]
    fn test_calcular_impuestos() {
        let p = Producto::new("Yerba".to_string(), 100.0, 2);
        let impuesto = p.calcular_impuestos(10.0);
        assert_eq!(impuesto, 10.0);
    }

    #[test]
    fn test_aplicar_descuento() {
        let p = Producto::new("Azúcar".to_string(), 100.0, 3);
        let con_descuento = p.aplicar_descuento(10.0);
        assert_eq!(con_descuento, 90.0);
    }

    #[test]
    fn test_calcular_precio_total() {
        let p = Producto::new("Pan".to_string(), 100.0, 1);
        let total = p.calcular_precio_total(10.0, 0.0);
        assert_eq!(total, 110.0);
    }

}

fn main() {
    println!("Hello, world!");
}
