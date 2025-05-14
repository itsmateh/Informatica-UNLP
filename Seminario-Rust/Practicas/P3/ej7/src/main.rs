#[derive(Debug, Clone)]
enum Colores{
    Rojo,
    Verde,
    Azul,
    Amarillo,
    Blanco,
    Negro,
}

#[derive(Debug, Clone)]
struct Auto{
    marca:String,
    modelo:String,
    anio:u32,
    precio_bruto:f32,
    color: Colores,
}

#[derive(Debug, Clone)]
struct ConcesionarioAuto{
    nombre:String,
    direccion:String,
    capacidad:usize, // sirve para mas adelante para comparar la capacidad maxima con la del len() del vector de autos (.len() en vectores de rust es de tipo usize)
    autos:Vec<Auto>, // su .len() es de tipo usize
}


impl Auto{
    pub fn new(marca:String, modelo:String, anio:u32, precio_bruto:f32, color:Colores) -> Auto {
        return Auto {modelo, marca, anio, precio_bruto, color};
    }

    pub fn calcular_precio(&self) -> f32 {
        // o hacemos mutable (cambiamos el valor de precio_bruto) o hacaemos shadowing
        // o hacemos una variable local y retornamos eso
        let mut precio:f32 = self.precio_bruto;

        // metodo para saber si todo valor matchea con un patron -> matches!(valor, patron)
        if matches!(self.color, Colores::Rojo | Colores::Azul | Colores::Amarillo){
            precio *= 1.25;
        }
        else{
            precio *= 0.90; // -10%
        }

        // asumo que puede ser de marca bmw y tener un color primario, es decir que los descuentos/recargos son acumulativos
        if(self.marca.to_lowercase() == "bmw"){precio*=1.15;}
        if(self.anio < 2000){precio*=0.95;} // -5% 

        return precio;
    }
}


// funcion auxiliar de ayuda para ver si 2 autos son iguales 
fn son_iguales(a:&Auto, a2:&Auto) -> bool {
    if  a.marca == a2.marca && 
        a.anio == a2.anio && 
        a.modelo == a2.modelo && 
        a.precio_bruto == a2.precio_bruto &&
        //  se fija en las "variantes", si son la misma variante retorna true, si son diferentes retorna false !!!
        std::mem::discriminant(&a.color) == std::mem::discriminant(&a2.color){
        return true;
    }

    return false;
}


impl ConcesionarioAuto{
    pub fn new(nombre:String, direccion:String, capacidad:usize) -> ConcesionarioAuto {
        // asumimos que cumplen todos los campos y que capacidad > 0
        return ConcesionarioAuto{ nombre, direccion, capacidad, autos: Vec::new() }; 
    }

    pub fn agregar_auto(&mut self, auto_nuevo:&Auto) -> bool {
        if(self.autos.len() < self.capacidad){
            self.autos.push(auto_nuevo.clone());
            return true;
        }else{
            return false;
        }
    }

    pub fn eliminar_auto(&mut self, auto:&Auto) -> bool {
        let mut i = 0;
        for a in &self.autos{
            if(son_iguales(a, auto)){break;}
            i+=1;
        }

        // si i esta dentro del rango !!
        if(i<self.autos.len()){
            self.autos.remove(i);
            return true;
    }
        return false;
    } 

    pub fn buscar_auto(&self, auto:&Auto) -> Option<Auto> {
        for a in &self.autos {
            if son_iguales(a, auto) {
                return Some(a.clone());
            }             
        }
        return None;
    }
  

}

mod tests {
    use super::*;

    #[test]
    fn test_auto_precio_con_color_y_marca() {
        let auto = Auto::new(
            "BMW".to_string(),
            "Serie 3".to_string(),
            1995,
            10000.0,
            Colores::Rojo,
        );
    
        let precio = auto.calcular_precio();
        let esperado = 10000.0 * 1.25 * 1.15 * 0.95;
        assert!((precio - esperado).abs() < 0.01);
    }

    #[test]
    fn test_agregar_auto_exitoso() {
        let mut concesionario = ConcesionarioAuto::new(
            "AutoHouse".to_string(),
            "Calle Falsa 123".to_string(),
            2,
        );

        let auto = Auto::new(
            "Ford".to_string(),
            "Fiesta".to_string(),
            2010,
            5000.0,
            Colores::Verde,
        );

        assert!(concesionario.agregar_auto(&auto));
        assert_eq!(concesionario.autos.len(), 1);
    }

    #[test]
    fn test_agregar_auto_falla_por_capacidad() {
        let mut concesionario = ConcesionarioAuto::new(
            "Pequeño Auto".to_string(),
            "Av. Siempre Viva".to_string(),
            1,
        );

        let auto1 = Auto::new("Fiat".to_string(), "Uno".to_string(), 2005, 3000.0, Colores::Blanco);
        let auto2 = Auto::new("Chevrolet".to_string(), "Corsa".to_string(), 2008, 4000.0, Colores::Negro);

        assert!(concesionario.agregar_auto(&auto1));
        assert!(!concesionario.agregar_auto(&auto2)); // no hay lugar
    }

    #[test]
    fn test_eliminar_auto_existente() {
        let mut concesionario = ConcesionarioAuto::new(
            "EliminarAuto".to_string(),
            "Dirección 123".to_string(),
            5,
        );

        let auto = Auto::new("Honda".to_string(), "Civic".to_string(), 2020, 15000.0, Colores::Negro);
        
        concesionario.agregar_auto(&auto);
        concesionario.eliminar_auto(&auto);
        assert_eq!(concesionario.autos.len(), 0);
    }

    #[test]
    fn test_eliminar_auto_inexistente() {
        let mut concesionario = ConcesionarioAuto::new(
            "NoEliminar".to_string(),
            "Ruta 99".to_string(),
            5,
        );

        let auto_existente = Auto::new("Renault".to_string(), "Clio".to_string(), 2010, 8000.0, Colores::Blanco);
        let auto_no_existente = Auto::new("Peugeot".to_string(), "208".to_string(), 2022, 12000.0, Colores::Rojo);

        concesionario.agregar_auto(&auto_existente);

        assert!(!concesionario.eliminar_auto(&auto_no_existente));
        assert_eq!(concesionario.autos.len(), 1);
    }
}



fn main() {
    println!("Hello, world!");
}
