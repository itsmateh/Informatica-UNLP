// ============================================ Fecha
#[derive(Clone)]
struct Fecha{
    dia:u32, 
    mes:u32,
    anio:u32,
 }
 
 impl Fecha {
    pub fn new(dia:u32, mes:u32, anio:u32) -> Fecha {
        return Fecha{dia, mes, anio} 
    }
 
    pub fn es_bisiesto(&self) -> bool {
        let ans:bool = (self.anio % 4 == 0 && self.anio % 100 != 0)  || (self.anio % 400 == 0);
        return ans
    }
 
    pub fn es_fecha_valida(&self) -> bool {
        let meses_30_dias:[u32; 4] = [4,6,9,11];
        if self.dia < 1 || self.dia > 31 || self.mes < 1 || self.mes > 12 || self.anio < 1 {return false}
        if self.dia > 28 && self.mes == 2 && !self.es_bisiesto() {return false}
        if meses_30_dias.contains(&self.mes) && self.dia > 30 {return false}
 
        return true
    }   
 
    // asumimos que quiere agregar +dias al mes, es decir que si agrega 90 dias, no se le suman 3 meses
    pub fn sumar_dias(&mut self, dias:u32){
        let mes_30_dias:[u32; 4] = [4,6,9,11];
        let mes_31_dias:[u32; 7] = [1,3,5,7,8,10,12];
        // caso 1: meses con 31 dias
        // caso 2: meses con 30 dias
        // caso 3: febrero -> p1. si es bisiesto. p2. si no es bisiesto
        if mes_31_dias.contains(&self.mes) && self.dia + dias <= 31 {self.dia += dias;}
        else if mes_30_dias.contains(&self.mes) && self.dia + dias <= 30{self.dia += dias;}
        else if self.mes == 2 && self.es_bisiesto() && self.dia + dias <= 29 {self.dia += dias;}
        else if self.mes == 2 && !self.es_bisiesto() && self.dia + dias <= 28 {self.dia += dias;}
    }
 
    pub fn restar_dias(&mut self, dias:u32){
        if(self.dia > dias) {self.dia -= dias;}
    }
 
    pub fn es_mayor(&self, fecha:Fecha) -> bool {
       return (self.anio, self.mes, self.dia) > (fecha.anio, fecha.mes, fecha.dia)
    }
 }
 

// ============================================

// ============================================
use std::{collections::VecDeque, mem::Discriminant};

#[derive(Clone)]
enum Animal{perro, gato, caballo, otro,}

#[derive(Clone)]
struct Mascota{
    // nombre, la edad, el tipo de animal(perro, gato, caballo, otros) y su dueño
    nombre:String, 
    edad:u32, 
    animal:Animal,
    duenio:Duenio,
}

#[derive(Clone)]
struct Duenio{
    // Del dueño se conoce el nombre, la dirección y un teléfono de contacto
    nombre:String, 
    direccion:String, 
    telefono:u32,
}

#[derive(Clone)]
struct Atencion{
    //datos de la mascota, el diagnóstico final, tratamiento y fecha de la próxima visita si es que se requiere
    mascota:Mascota,
    diagnostico_final:String,
    tratamiento:String,
    fecha:Option<Fecha>,
}

#[derive(Clone)]
struct Veterinaria{
    nombre:String,direccion:String,id:u32,
    atencion:VecDeque<Mascota>, // una queue con los animales a atender asi lo interpreto yo
    registros:Vec<Atencion>, // registro con las atenciones ya hechas 
}

impl Veterinaria{
    pub fn new(nombre:String, direccion:String, id:u32) -> Veterinaria{
        Veterinaria { 
            nombre, 
            direccion, 
            id, 
            atencion: VecDeque::new(), 
            registros: Vec::new(),
        }
    }

    pub fn agregar_mascota(&mut self, m:Mascota){
        self.atencion.push_back(m);
    }

    pub fn agregar_mascota_prioritaria(&mut self, m:Mascota){
        self.atencion.push_front(m);
    }

    // atender imagino que se refiere a devolver la prox. mascota
    pub fn atender_mascota(&mut self){
        self.atencion.pop_front();
    }

    pub fn eliminar_especifica(&mut self, m:&Mascota) -> bool {
        let mut indice = None;

        for (i, mascota) in self.atencion.iter().enumerate(){
            if(es_igual(mascota, m)){indice = Some(i);}
        }

        if indice.is_some(){
            self.atencion.remove(indice.unwrap());
            return true;
        }
        else{
            return false;
        }
    }

    pub fn registrar_atencion(&mut self, ate:Atencion){
        self.registros.push(ate);
    }

    pub fn buscar_atencion(&self, nombre_mascota:String, nombre_due:String, telefono:u32) -> Option<Atencion>{
        for atencion in &self.registros{
            if atencion.mascota.nombre == nombre_mascota && atencion.mascota.duenio.nombre == nombre_due && atencion.mascota.duenio.telefono == telefono {
                return Some(atencion.clone());
            }
        }
        None 
    }

    pub fn modificar_diagnostico(&mut self, atencion:&Atencion, nuevo_diag:&String) {
        for a in &mut self.registros{
            if mismo_registro(a,atencion){
                a.diagnostico_final = nuevo_diag.clone();
            }
        }
    }

    pub fn modificar_fecha(&mut self, atencion:&Atencion, nueva_fecha:&Option<Fecha>) {
        for a in &mut self.registros{
            if mismo_registro(a, atencion){
                a.fecha = nueva_fecha.clone();
            }
        }
    }

    pub fn eliminar_atencion(&mut self, atencion:&Atencion) -> bool {
        let mut indice = None; 
        
        for (i, ate) in self.registros.iter().enumerate(){
            if mismo_registro(ate, atencion){
                indice = Some(i);
            }
        }

        if indice.is_some(){
            self.registros.remove(indice.unwrap());
            return true;
        }
        return false;
    }


}
// ============================================


// ============================================ funciones auxiliares
fn es_igual(m1:&Mascota, m2:&Mascota) -> bool {
    m1.edad == m2.edad && m1.nombre == m2.nombre && mismo_duenio(&m1.duenio, &m2.duenio) &&
    std::mem::discriminant(&m1.animal) == std::mem::discriminant(&m2.animal)
}

fn mismo_duenio(d1:&Duenio, d2:&Duenio) -> bool{
    d1.direccion == d2.direccion && d1.nombre == d2.nombre && d1.telefono == d2.telefono
}

fn mismo_registro(a1:&Atencion, a2:&Atencion) -> bool{
    a1.diagnostico_final == a2.diagnostico_final && 
    a1.tratamiento == a2.tratamiento && 
    es_igual(&a1.mascota, &a2.mascota) &&
    misma_fecha_op(&a1.fecha, &a2.fecha)

}

fn misma_fecha_op(f1: &Option<Fecha>, f2: &Option<Fecha>) -> bool {
    match (f1, f2) {
        (Some(fecha1), Some(fecha2)) => misma_fecha(fecha1, fecha2),
        (None, None) => true,
        _ => false,
    }
}

fn misma_fecha(f1:&Fecha, f2:&Fecha) -> bool{
    f1.anio == f2.anio && 
    f1.dia == f2.dia && 
    f1.mes == f2.mes
}

// ============================================

// ============================================ Unit testing
#[cfg(test)]
mod tests {
    use super::*;

    fn crear_mascota(nombre: &str, duenio_nombre: &str) -> Mascota {
        Mascota {
            nombre: nombre.to_string(),
            edad: 5,
            animal: Animal::perro,
            duenio: Duenio {
                nombre: duenio_nombre.to_string(),
                direccion: "Calle Falsa 123".to_string(),
                telefono: 12345678,
            },
        }
    }

    fn crear_atencion(mascota: Mascota) -> Atencion {
        Atencion {
            mascota,
            diagnostico_final: "Gripe".to_string(),
            tratamiento: "Descanso".to_string(),
            fecha: None,
        }
    }

    #[test]
    fn test_agregar_y_atender() {
        let mut vet = Veterinaria::new("Vet1".to_string(), "Calle 1".to_string(), 1);
        let m1 = crear_mascota("Fido", "Juan");
        let m2 = crear_mascota("Michi", "Ana");

        vet.agregar_mascota(m1.clone());
        vet.agregar_mascota_prioritaria(m2.clone());

        // Como m2 se agregó prioritario, debe salir primero
        assert_eq!(vet.atencion.front().unwrap().nombre, "Michi");
        
        vet.atender_mascota();
        assert_eq!(vet.atencion.front().unwrap().nombre, "Fido");
    }

    #[test]
    fn test_registrar_y_buscar_atencion() {
        let mut vet = Veterinaria::new("Vet1".to_string(), "Calle 1".to_string(), 1);
        let m1 = crear_mascota("Fido", "Juan");
        let atencion = crear_atencion(m1.clone());

        vet.registrar_atencion(atencion.clone());

        let resultado = vet.buscar_atencion("Fido".to_string(), "Juan".to_string(), 12345678);
        assert!(resultado.is_some());
        assert_eq!(resultado.unwrap().diagnostico_final, "Gripe");
    }

    #[test]
    fn test_modificar_diagnostico() {
        let mut vet = Veterinaria::new("Vet1".to_string(), "Calle 1".to_string(), 1);
        let m1 = crear_mascota("Fido", "Juan");
        let mut atencion = crear_atencion(m1.clone());

        vet.registrar_atencion(atencion.clone());

        let nuevo_diag = "Resfriado".to_string();
        vet.modificar_diagnostico(&atencion, &nuevo_diag);

        let resultado = vet.buscar_atencion("Fido".to_string(), "Juan".to_string(), 12345678).unwrap();
        assert_eq!(resultado.diagnostico_final, "Resfriado");
    }

    #[test]
    fn test_eliminar_mascota_especifica() {
        let mut vet = Veterinaria::new("Vet1".to_string(), "Calle 1".to_string(), 1);
        let m1 = crear_mascota("Fido", "Juan");
        let m2 = crear_mascota("Michi", "Ana");

        vet.agregar_mascota(m1.clone());
        vet.agregar_mascota(m2.clone());

        let eliminado = vet.eliminar_especifica(&m1);
        assert!(eliminado);
        assert_eq!(vet.atencion.len(), 1);
        assert_eq!(vet.atencion.front().unwrap().nombre, "Michi");
    }

    #[test]
    fn test_eliminar_atencion() {
        let mut vet = Veterinaria::new("Vet1".to_string(), "Calle 1".to_string(), 1);
        let m1 = crear_mascota("Fido", "Juan");
        let atencion = crear_atencion(m1.clone());

        vet.registrar_atencion(atencion.clone());
        let eliminado = vet.eliminar_atencion(&atencion);
        assert!(eliminado);
        assert_eq!(vet.registros.len(), 0);
    }
}
// ============================================

fn main() {
    println!("Hello, world!");
}
