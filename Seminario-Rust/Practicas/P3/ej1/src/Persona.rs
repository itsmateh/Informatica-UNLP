pub struct Persona{
   pub nombre:String,
   pub edad:i32,
   pub direccion:Option<String>
}

impl Persona {
   // fundamental el tipo de retorno -> Persona
   pub fn new(nombre:String, edad:i32, direccion:Option<String>) -> Persona{
      Persona{
         nombre,
         edad, 
         direccion 
      }
   }
   
   pub fn to_string(&self) -> String{
      let string = match &self.direccion {
         Some(dir) => format!("Nombre: {} Edad: {} Direccion: {}", self.nombre, self.edad, dir),
         None => format!("Nombre: {} Edad: {}", self.nombre, self.edad)
      };    
      // retorno
      string
   } 
   
   // sin ; !!!!
   pub fn obtener_edad(&self) -> i32 {
      self.edad
   }

   // si vamos a cambiar un dato -> &mut self
   pub fn actualizar_direccion(&mut self, nueva_direccion:Option<String>){
      self.direccion = nueva_direccion;
   }

}

// test cases
// ver video de Unit Testing