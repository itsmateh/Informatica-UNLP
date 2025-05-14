use std::collections::HashSet;

struct Triangulo{
    lado_1:u32,
    lado_2:u32,
    lado_3:u32,
}

impl Triangulo{
    pub fn new(lado_1:u32, lado_2:u32, lado_3:u32) -> Result<Triangulo, & 'static str> {
        if lado_1 < 0 || lado_2 < 0 || lado_3 < 0 {
            return Err("Los lados no pueden ser negativos !!!");
        }
        return Ok(Triangulo {lado_1, lado_2, lado_3})
    }
    
    pub fn determinar_tipo(&self) -> String { 
        let mut set:HashSet<u32> = HashSet::new();
        set.insert(self.lado_1); 
        set.insert(self.lado_2);
        set.insert(self.lado_3); 

        match set.len() {
            1 => return "Equilatero".to_string(), 
            2 => return "Isoceles".to_string(), 
            3 => return "Escaleno".to_string(),
            _ => return "Error".to_string(),
        }
    }

    pub fn calcular_area(&self) -> f32 {
        // formula heron: S = (a+b+c)/2,  Area = sqrt(S*(S-a)*(S-b)*(S-c))
        let a:f32 = self.lado_1 as f32;
        let b:f32 = self.lado_2 as f32;
        let c:f32 = self.lado_3 as f32;
        let s:f32 = (a+b+c)/2.0;
        let area:f32 = (s*(s-a)*(s-b)*(s-c)).sqrt();
        return area
    }

    pub fn calcular_perimetro(&self) -> u32 {
        return self.lado_1 + self.lado_2 + self.lado_3;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_triangulo_valido() {
        let t = Triangulo::new(3, 4, 5);
        assert!(t.is_ok());
    }

    #[test]
    fn test_tipo_equilatero() {
        let t = Triangulo::new(5, 5, 5).unwrap();
        assert_eq!(t.determinar_tipo(), "Equilatero");
    }

    #[test]
    fn test_tipo_isoceles() {
        let t = Triangulo::new(5, 5, 3).unwrap();
        assert_eq!(t.determinar_tipo(), "Isoceles");
    }

    #[test]
    fn test_tipo_escaleno() {
        let t = Triangulo::new(3, 4, 5).unwrap();
        assert_eq!(t.determinar_tipo(), "Escaleno");
    }

    #[test]
    fn test_perimetro() {
        let t = Triangulo::new(3, 4, 5).unwrap();
        assert_eq!(t.calcular_perimetro(), 12);
    }

    #[test]
    fn test_area() {
        let t = Triangulo::new(3, 4, 5).unwrap();
        let area = t.calcular_area();
        assert!((area - 6.0).abs() < 0.001);
    }
}


fn main() {
    println!("Hello, world!");
}
