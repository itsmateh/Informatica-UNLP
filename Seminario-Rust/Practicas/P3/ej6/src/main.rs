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
    pub fn new(materia:String, nota:f32) -> Examen {
        return Examen{ materia, nota };
    }
}

impl Estudiante{
    pub fn new(nombre:String, id:u32, examenes:Vec<Examen>) -> Estudiante{
        return Estudiante { nombre, id, examenes };
    }

    pub fn obtener_promedio(&self) -> f32 {
        let mut sum_notas:f32 = 0.0;
        let cant_examenes:f32 = self.examenes.len() as f32;


        for ex in &self.examenes{
            sum_notas += ex.nota;
        }

        return sum_notas / cant_examenes;

    }

    pub fn obtener_calificacion_mas_alta(&self) -> f32 {
        let mut maximo:f32 = -1111.0;
        for ex in &self.examenes{
            if ex.nota > maximo{
                maximo = ex.nota;
            }
        }
        return maximo;
    }

    pub fn obtener_calificacion_mas_baja(&self) -> f32 {
        let mut minimo:f32 = 9999.0;
        for ex in &self.examenes{
            if ex.nota < minimo{
                minimo = ex.nota;
            }
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
