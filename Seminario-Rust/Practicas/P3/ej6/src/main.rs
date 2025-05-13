struct Examen{
    materia:String,
    nota:f32,
}
struct Estudiante{
    nombre:String,
    id:u32,
    examenes: Vec<Examen>,
}

impl Examen{
    fn new(materia:String, nota:f32) -> Examen {
        return Examen{ materia, nota };
    }
}

impl Estudiante{
    fn new(nombre:String, id:u32, examenes:Vec<Examen>) -> Estudiante{
        return Estudiante { nombre, id, examenes };
    }

    fn obtener_promedio(&self) -> f32 {
        let suma_notas:f32 = self.examenes.iter().map(|ex| ex.nota).sum(); // gracias programacion funcional sos la cabra
        return suma_notas/self.examenes.len() as f32; // importante el pareseo !!!! 
    }

    fn obtener_calificacion_mas_alta(&self) -> f32 {
        // el iter().max() devuelve un Result por lo que aca no hay programacion funcional que me zafe
        let mut maximo:f32 = -11111.0;
        for ex in &self.examenes{ 
            maximo = f32::max(ex.nota, maximo);            
        }
        return maximo;
    }

    fn obtener_calificacion_mas_baja(&self) -> f32 {
        let mut minimo:f32 = 9999.0;
        for ex in &self.examenes {
            minimo = f32::min(ex.nota, minimo);
        }
        return minimo;
    }
}

#[cfg(test)]
mod test{
   use super::*;

    #[test]
    fn test_obtener_promedio(){
        let exam = vec![
            Examen::new("Matematica".to_string(), 10.0), 
            Examen::new("Fisica".to_string(), 10.0),
            Examen::new("Computacion".to_string(), 10.0),
        ];
        let est = Estudiante::new("Mateo".to_string(),1, exam);   
        assert_eq!(est.obtener_promedio(), 10.0);
    }

    #[test]
    fn test_obtener_calificacion_mas_alta(){
        let exam = vec![
            Examen::new("Matematica".to_string(), 10.0), 
            Examen::new("Fisica".to_string(), 9.0),
            Examen::new("Computacion".to_string(), 8.0),
        ];
        let est:Estudiante = Estudiante::new("Mateo".to_string(),1, exam);   
        assert_eq!(est.obtener_calificacion_mas_alta(), 10.0);
    }

    #[test]
    fn test_obtener_calificacion_mas_baja(){
        let exam = vec![
            Examen::new("Matematica".to_string(), 10.0), 
            Examen::new("Fisica".to_string(), 9.0),
            Examen::new("Computacion".to_string(), 8.0),            
        ];
        let est:Estudiante = Estudiante::new("Mateo".to_string(),1, exam);   
        assert_eq!(est.obtener_calificacion_mas_baja(), 8.0);
    }
}

fn main() {
    println!("Hello, world!");
}
