// =============================================== Fecha
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
 
// =============================================== 
use std::{collections::HashMap, mem::discriminant};

#[derive(Clone)]
enum Genero {novela, infantil, tecnico, otros}

#[derive(Clone)]
enum Estado {devuelto, prestado}

#[derive(Clone)]
struct Libro {
// el isbn, el título, autor, número de páginas, género(novela, infantil, técnico, otros).
    isbn:u32,
    titulo:String,
    autor:String,
    paginas:u32,
    genero:Genero
}
impl Libro {
    fn new(isbn:u32, titulo:String, autor:String, paginas:u32, genero:Genero) -> Libro{
        Libro{
            isbn,
            titulo,
            autor,
            paginas,
            genero
        }
    }
}

#[derive(Clone)]
struct Prestamo{
//  Para registrar un préstamo se requiere el libro, el cliente, la fecha de vencimiento del préstamo, la fecha de devolución y el estado que puede ser devuelto o en préstamo.
    libro:Libro,
    cliente:Cliente,
    fecha_vencimiento_prestamo:Fecha,
    fecha_devolucion:Fecha,
    estado:Option<Estado>
}
impl Prestamo {
    pub fn new(libro:Libro, cliente:Cliente, fecha_vencimiento_prestamo:Fecha, fecha_devolucion:Fecha) -> Prestamo{
        Prestamo{
            libro,
            cliente,
            fecha_devolucion,
            fecha_vencimiento_prestamo,
            estado: Some(Estado::prestado)
        }
    }
}


#[derive(Clone)]
struct Cliente{
// Del cliente se conoce el nombre, teléfono y dirección de correo electrónico. 
    nombre:String, 
    telefono:u32,
    direccion:String,
    correo:String
}
impl Cliente{
    pub fn new(nombre:String, telefono:u32, direccion:String, correo:String) -> Cliente{
        Cliente { nombre, telefono, direccion, correo}
    }
}

#[derive(Clone)]
struct Biblioteca{
    nombre:String,
    direccion:String,
    copias_disposicion:HashMap<u32, u32>, // clave=isbn, valor = cant. de copias
    prestamos:Vec<Prestamo>
}


impl Biblioteca {
    pub fn new(nombre:String, direccion:String, copias_disposicion:HashMap<Libro, u32>) -> Biblioteca{
        Biblioteca{ 
            nombre,
            direccion,
            copias_disposicion: HashMap::new(),
            prestamos: Vec::new()
        }
    }

    // obtener cantidad de copias:
    pub fn obtener_cantidad_copias(&self, libro:&Libro) -> u32 {
       *self.copias_disposicion.get(&libro.isbn).unwrap_or(&0) // si no existe ese libro retorna 0 
    }

    // decrementar cantidad de copias a disposición
    pub fn decrementar_copias_libro(&mut self, lib:&Libro) -> bool {
        if let Some(cant) = self.copias_disposicion.get_mut(&lib.isbn){
            if *cant > 0{
                *cant-=1;
                return true;
            }
        }

        return false; 
    }

    // incrementar cantidad de copias a disposición:
    pub fn incrementar_copias_libro(&mut self, lib:&Libro) -> bool {
        if let Some(cant) = self.copias_disposicion.get_mut(&lib.isbn){
            if *cant > 0{
                *cant+=1;
                return true;
            }
        }

        return false; 
    }

    pub fn contar_prestamos_cliente(&self, cli:&Cliente) -> u32 {
        let mut cant_prestamos = 0;

        for p in &self.prestamos {
            if mismo_cliente(&p.cliente, cli) {
                match p.estado {
                    Some(Estado::prestado) => {
                        cant_prestamos += 1;
                    }
                    _ => {} // Si no está en préstamo, no contamos
                }
            }
        }

        return cant_prestamos;
    }

    pub fn prestamo_cliente(&mut self, cli:&Cliente, lib:&Libro, fecha_ven:&Fecha) -> bool {
        if self.contar_prestamos_cliente(&cli) > 5 || self.obtener_cantidad_copias(&lib) == 0
        {return false;}

        *self.copias_disposicion.get_mut(&lib.isbn).unwrap() -= 1;
        let p1 = Prestamo::new(lib.clone(), cli.clone(), fecha_ven.clone(), fecha_ven.clone());
        self.prestamos.push(p1);
        return true;
    }

    pub fn prestamos_a_vencer_en_x_dias(&self, dias:u32, dia_act:&Fecha) -> Vec<&Prestamo>{
        let mut v:Vec<&Prestamo> = Vec::new();

        for p in &self.prestamos{
            if(dia_act.dia - p.fecha_vencimiento_prestamo.dia < dias){v.push(p);}
        }

        return v;
    }

    pub fn prestamos_vencidos(&self, dia_act:&Fecha) -> Vec<&Prestamo> {
        let mut v:Vec<&Prestamo> = Vec::new();

        for p in &self.prestamos{
            if dia_act.dia < p.fecha_vencimiento_prestamo.dia {
                match p.estado{
                    Some(Estado::prestado) => {
                        v.push(p);
                    }
                    _ => {}
                }
            }
        }

        return v;
    }

    pub fn buscar_prestamo(&self, lib:&Libro, cli:&Cliente) -> Option<&Prestamo> {
        for p in &self.prestamos{
            if p.libro.isbn == lib.isbn && mismo_cliente(&p.cliente, cli){
                return Some(p);
            }
        }
        return None;
    }

    pub fn devolver_libro(&mut self, lib:&Libro, cli:&Cliente, f_act:&Fecha){
        for p in &mut self.prestamos{
            if mismo_cliente(&p.cliente, cli) && mismo_libro(&p.libro, lib){
                p.estado = Some(Estado::devuelto);
                p.fecha_devolucion = f_act.clone();
                if let Some(cant) = self.copias_disposicion.get_mut(&lib.isbn){
                    *cant += 1;
                }
            }
        }
    }

}

// =============================================== Funciones auxiliares
fn mismo_cliente(c1:&Cliente, c2:&Cliente) -> bool {
    c1.correo == c2.correo && c1.direccion == c2.direccion && 
    c1.nombre == c2.nombre && c1.telefono == c2.telefono
}

fn mismo_libro(l1:&Libro, l2:&Libro) -> bool {
    l1.autor == l2.autor && l1.isbn == l2.isbn && 
    l1.paginas == l2.paginas && l1.titulo == l2.titulo && 
    std::mem::discriminant(&l1.genero) == std::mem::discriminant(&l2.genero) 
}

// =============================================== Unit testing
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_contar_prestamos_cliente() {
        let mut bib = Biblioteca {
            nombre: "Bibli".to_string(),
            direccion: "Dir".to_string(),
            copias_disposicion: std::collections::HashMap::new(),
            prestamos: vec![],
        };

        let libro = Libro::new(111, "Titulo".to_string(), "Autor".to_string(), 100, Genero::novela);
        let cliente = Cliente::new("Juan".to_string(), 1234, "Calle".to_string(), "juan@mail.com".to_string());
        let fecha = Fecha::new(1, 5, 2025);
        
        let prestamo = Prestamo::new(libro.clone(), cliente.clone(), fecha.clone(), fecha.clone());
        bib.prestamos.push(prestamo);

        assert_eq!(bib.contar_prestamos_cliente(&cliente), 1);
    }

    #[test]
    fn test_prestamo_cliente() {
        let mut bib = Biblioteca {
            nombre: "Bibli".to_string(),
            direccion: "Dir".to_string(),
            copias_disposicion: std::collections::HashMap::new(),
            prestamos: vec![],
        };

        let libro = Libro::new(222, "Libro2".to_string(), "Autor2".to_string(), 200, Genero::tecnico);
        let cliente = Cliente::new("Ana".to_string(), 5678, "Calle2".to_string(), "ana@mail.com".to_string());
        let fecha = Fecha::new(5, 6, 2025);

        bib.copias_disposicion.insert(libro.isbn, 1);

        let resultado = bib.prestamo_cliente(&cliente, &libro, &fecha);
        assert!(resultado);
        assert_eq!(bib.contar_prestamos_cliente(&cliente), 1);
    }

    #[test]
    fn test_devolver_libro() {
        let mut bib = Biblioteca {
            nombre: "Bibli".to_string(),
            direccion: "Dir".to_string(),
            copias_disposicion: std::collections::HashMap::new(),
            prestamos: vec![],
        };

        let libro = Libro::new(333, "Libro3".to_string(), "Autor3".to_string(), 300, Genero::otros);
        let cliente = Cliente::new("Luis".to_string(), 9101, "Calle3".to_string(), "luis@mail.com".to_string());
        let fecha_prestamo = Fecha::new(1, 7, 2025);
        let fecha_devolucion = Fecha::new(15, 7, 2025);

        bib.copias_disposicion.insert(libro.isbn, 0);

        let prestamo = Prestamo::new(libro.clone(), cliente.clone(), fecha_prestamo.clone(), fecha_prestamo.clone());
        bib.prestamos.push(prestamo);

        bib.devolver_libro(&libro, &cliente, &fecha_devolucion);

        let p = bib.buscar_prestamo(&libro, &cliente).unwrap();
        assert_eq!(p.fecha_devolucion.dia, 15);
    } #[test]
    fn test_obtener_cantidad_copias() {
        let mut bib = Biblioteca {
            nombre: "Bibli".to_string(),
            direccion: "Dir".to_string(),
            copias_disposicion: HashMap::new(),
            prestamos: vec![],
        };

        let libro = Libro::new(444, "Libro4".to_string(), "Autor4".to_string(), 400, Genero::novela);

        assert_eq!(bib.obtener_cantidad_copias(&libro), 0);

        bib.copias_disposicion.insert(libro.isbn, 3);
        assert_eq!(bib.obtener_cantidad_copias(&libro), 3);
    }

    #[test]
    fn test_decrementar_copias_libro() {
        let mut bib = Biblioteca {
            nombre: "Bibli".to_string(),
            direccion: "Dir".to_string(),
            copias_disposicion: HashMap::new(),
            prestamos: vec![],
        };

        let libro = Libro::new(555, "Libro5".to_string(), "Autor5".to_string(), 500, Genero::infantil);
        bib.copias_disposicion.insert(libro.isbn, 2);

        let resultado = bib.decrementar_copias_libro(&libro);
        assert!(resultado);
        assert_eq!(bib.copias_disposicion.get(&libro.isbn), Some(&1));

        let libro2 = Libro::new(556, "Libro6".to_string(), "Autor6".to_string(), 600, Genero::tecnico);
        bib.copias_disposicion.insert(libro2.isbn, 0);
        let resultado2 = bib.decrementar_copias_libro(&libro2);
        assert!(!resultado2);
        assert_eq!(bib.copias_disposicion.get(&libro2.isbn), Some(&0));
    }

    #[test]
    fn test_incrementar_copias_libro() {
        let mut bib = Biblioteca {
            nombre: "Bibli".to_string(),
            direccion: "Dir".to_string(),
            copias_disposicion: HashMap::new(),
            prestamos: vec![],
        };

        let libro = Libro::new(666, "Libro7".to_string(), "Autor7".to_string(), 700, Genero::novela);
        bib.copias_disposicion.insert(libro.isbn, 1);

        let resultado = bib.incrementar_copias_libro(&libro);
        assert!(resultado);
        assert_eq!(bib.copias_disposicion.get(&libro.isbn), Some(&2));

        let libro_no_existente = Libro::new(777, "Libro8".to_string(), "Autor8".to_string(), 800, Genero::otros);
        let resultado2 = bib.incrementar_copias_libro(&libro_no_existente);
        assert!(!resultado2);
    }

    #[test]
    fn test_buscar_prestamo() {
        let mut bib = Biblioteca {
            nombre: "Bibli".to_string(),
            direccion: "Dir".to_string(),
            copias_disposicion: HashMap::new(),
            prestamos: vec![],
        };

        let libro = Libro::new(888, "Libro9".to_string(), "Autor9".to_string(), 900, Genero::infantil);
        let cliente = Cliente::new("Maria".to_string(), 1111, "Dir1".to_string(), "maria@mail.com".to_string());
        let fecha = Fecha::new(10, 10, 2025);

        let prestamo = Prestamo::new(libro.clone(), cliente.clone(), fecha.clone(), fecha.clone());
        bib.prestamos.push(prestamo);

        let encontrado = bib.buscar_prestamo(&libro, &cliente);
        assert!(encontrado.is_some());

        let cliente_no_prestamo = Cliente::new("Pedro".to_string(), 2222, "Dir2".to_string(), "pedro@mail.com".to_string());
        let no_encontrado = bib.buscar_prestamo(&libro, &cliente_no_prestamo);
        assert!(no_encontrado.is_none());
    }

}



fn main() {

}
