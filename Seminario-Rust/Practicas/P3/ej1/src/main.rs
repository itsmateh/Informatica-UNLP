pub struct Persona{
    nombre:String,
    edad:i32,
    direccion:Option<String>
}

impl Persona {
    pub fn new(nombre:String, edad:i32, direccion:Option<String>) -> Persona {
        Persona{
            nombre,
            edad, 
            direccion 
        }
    }

    pub fn to_string(&self) -> String{
        let txt:String = match &self.direccion{
            Some(direc) => format!("Nombre: {}, Edad: {}, Direccion: {}", self.nombre, self.edad, direc),
            None => format!("Nombre: {}, Edad: {}", self.nombre, self.edad),
        };
        return txt
    }

    pub fn obtener_edad(&self) -> i32 {
        self.edad
    }

    pub fn actualizar_direccion(&mut self, nueva_direccion:Option<String>){
        self.direccion = nueva_direccion;
    }

}

#[cfg(test)]
mod test_persona{
    use super::*; 

    #[test]
    fn test_new_persona_con_direccion(){
        let persona:Persona = Persona::new("mateo".to_string(), 777 , Some("vertex".to_string())); 

        assert_eq!(persona.nombre, "mateo".to_string());
        assert_eq!(persona.edad, 777);
        assert_eq!(persona.direccion, Some("vertex".to_string()));
    }

    #[test]
    fn test_new_persona_sin_direccion(){
        let persona:Persona = Persona::new("mateo".to_string(), 777, None);

        assert_eq!(persona.nombre, "mateo".to_string());
        assert_eq!(persona.edad, 777);
        assert_eq!(persona.direccion, None);
    } 


    #[test]
    fn test_to_string_con_direccion(){
        let persona:Persona = Persona::new("mateo".to_string(), 777, Some("vertex".to_string()));
        let res:String = persona.to_string();
        
        assert_eq!("Nombre: mateo, Edad: 777, Direccion: vertex", res);
    }

    #[test]
    fn test_to_string_sin_direccion(){
        let persona:Persona = Persona::new("mateo".to_string(), 777, None);
        let res:String = persona.to_string();
        
        assert_eq!("Nombre: mateo, Edad: 777", res);
    }

    #[test]
    fn test_obtener_edad(){
        let persona:Persona = Persona::new("mateo".to_string(), 777, None);
        let edad:i32 = persona.obtener_edad();
        
        assert_eq!(persona.edad, edad);
    }

    #[test]
    fn test_actualizar_direccion_sin_direc(){
        let mut persona:Persona = Persona::new("mateo".to_string(), 777, None);
        persona.actualizar_direccion(None);

        assert_eq!(persona.direccion, None);
    }
}
 
fn main() {

}
