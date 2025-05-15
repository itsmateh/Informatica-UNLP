struct Rectangulo{
   longitud:u32, 
   ancho:u32,
}

impl Rectangulo{

   // tambien esta la alternativa con Result (es un enum con <T, E> siendo T -> res. exitoso y E -> res. erroneo)
   // fn new(longitud:u32, ancho:u32) -> Result<Rectangulo, &'static str>{
   //    if longitud == 0 || ancho == 0 {
   //       return Err("Los lados no pueden ser 0 !!!"); 
   //    }

   //    Ok(Rectangulo{longitud, ancho})
   // }

   pub fn new(longitud:u32, ancho:u32) -> Rectangulo {
      if longitud == 0 || ancho == 0 {
         panic!("Los lados no pueden ser 0 !!!");
      }
      
      return Rectangulo{longitud, ancho}
   }

   pub fn calcular_area(&self) -> u32 {
      return self.longitud * self.ancho;
   }

   pub fn calcular_perimetro(&self) -> u32 {
      return 2*(self.longitud + self.ancho);
   }

   pub fn es_cuadrado(&self) -> bool{
      return self.longitud == self.ancho
   }
}

#[cfg(test)]
mod test_rectangulo{
    use crate::Rectangulo;

   #[test]
   #[should_panic(expected = "Los lados no pueden ser 0 !!!")]
   fn test_new_rectangulo_con_lado_0(){
      let r1:Rectangulo = Rectangulo::new(0,2);
   }

   #[test]
   #[should_panic(expected = "Los lados no pueden ser 0 !!!")]
   fn test_new_rectangulo2_con_lado_0(){
      let r1:Rectangulo = Rectangulo::new(0,2);
   }


   #[test]
   fn test_area_y_perimetro() {
      let r:Rectangulo = Rectangulo::new(3, 4);
      assert_eq!(r.calcular_area(), 12);
      assert_eq!(r.calcular_perimetro(), 14);
   }

   #[test]
   fn test_es_cuadrado() {
       let r1:Rectangulo = Rectangulo::new(5, 5);
       assert!(r1.es_cuadrado());

       let r2:Rectangulo = Rectangulo::new(5, 4);
       assert!(!r2.es_cuadrado());
   }
}

fn main() {
    
}
