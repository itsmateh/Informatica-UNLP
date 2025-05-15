#[derive(Clone)]
enum Genero{
    rock,
    jazz,
    pop,
    rap,
    otro(String),
}

#[derive(Clone)]
struct Cancion{
    titulo:String,
    artista:String,
    genero:Genero,
}


#[derive(Clone)]
struct Playlist{
    nombre:String,
    canciones:Vec<Cancion>,
}


// funciones auxiliares
fn son_iguales(c1:&Cancion, c2:&Cancion) -> bool {
    c1.artista == c2.artista && c1.titulo == c2.titulo && 
    std::mem::discriminant(&c1.genero) == std::mem::discriminant(&c2.genero)
}


impl Playlist{
    pub fn agregar_cancion(&mut self, c:Cancion){
        self.canciones.push(c);
    }


    pub fn eliminar_cancion(&mut self, c:&Cancion){
        // self.canciones.retain(|cancion| cancion.titulo != c.titulo); clousures todavia no se puede usar mepa
        let mut indice = None; 
        for (i,can) in self.canciones.iter().enumerate() {
            if(son_iguales(can, c)){
                indice = Some(i);
                break;
            }
        }
        
        if indice.is_some(){
            self.canciones.remove(indice.unwrap());
        }
        
    }

    pub fn mover_cancion(&mut self, cancion:&Cancion, pos:usize){
        // chequear si es una pos valida
        if(pos-1 > self.canciones.len()){return;}

        let mut indice = None;
        for (i,c) in self.canciones.iter().enumerate() {
            if(son_iguales(c, cancion)){
                indice = Some(i);
                break;
            }   
        }

        if indice.is_some(){
            self.canciones.swap(pos-1, indice.unwrap());
        }
    }

    pub fn buscar_cancion_nombre_por_nombre(&self, nombre:String) -> Option<Cancion> {
        for c in &self.canciones{
            if(c.titulo == nombre){
                return Some(c.clone());
            }
        }
        // si no la encontro
        return None;
    }

    pub fn obtener_canciones_genero(&self, genero:Genero) -> Vec<Cancion> {
        let mut canciones_genero = Vec::new();

        for cancion in &self.canciones{
            if(std::mem::discriminant(&cancion.genero) == std::mem::discriminant(&genero)){
                canciones_genero.push(cancion.clone());
            }
        }
        return canciones_genero;
    }

    pub fn obtener_canciones_artista(&self, artista:String) -> Vec<Cancion> {
        let mut canciones_artista = Vec::new();

        for cancion in &self.canciones{
            if cancion.artista == artista{
                canciones_artista.push(cancion.clone());
            }
        }
        return canciones_artista;
    }

    pub fn modificar_titulo(&mut self, titulo_nuevo:String){
        self.nombre = titulo_nuevo;
    }

    pub fn eliminar_todas_canciones(&mut self){
        self.canciones.clear();
    }
}


#[cfg(test)]
mod tests {
    use super::*;

    fn cancion_ejemplo() -> Cancion {
        Cancion {
            titulo: "Imagine".to_string(),
            artista: "John Lennon".to_string(),
            genero: Genero::rock,
        }
    }

    #[test]
    fn test_agregar_cancion() {
        let mut playlist = Playlist {
            nombre: "mi playlist".to_string(),
            canciones: vec![],
        };

        let cancion = cancion_ejemplo();
        playlist.agregar_cancion(cancion.clone());

        assert_eq!(playlist.canciones.len(), 1);
        assert_eq!(playlist.canciones[0].titulo, "Imagine");
    }

    #[test]
    fn test_eliminar_cancion() {
        let mut playlist = Playlist {
            nombre: "Test".to_string(),
            canciones: vec![cancion_ejemplo()],
        };

        let cancion = cancion_ejemplo();
        playlist.eliminar_cancion(&cancion);
        assert_eq!(playlist.canciones.len(), 0);
    }

    #[test]
    fn test_mover_cancion() {
        let c1 = Cancion {
            titulo: "A".to_string(),
            artista: "X".to_string(),
            genero: Genero::pop,
        };
        let c2 = Cancion {
            titulo: "B".to_string(),
            artista: "Y".to_string(),
            genero: Genero::jazz,
        };

        let mut playlist = Playlist {
            nombre: "Mover".to_string(),
            canciones: vec![c1.clone(), c2.clone()],
        };

        playlist.mover_cancion(&c2, 1);
        assert_eq!(playlist.canciones[0].titulo, "B");
        assert_eq!(playlist.canciones[1].titulo, "A");
    }

    #[test]
    fn test_buscar_cancion() {
        let cancion = cancion_ejemplo();
        let mut playlist = Playlist {
            nombre: "Buscar".to_string(),
            canciones: vec![cancion.clone()],
        };

        let resultado = playlist.buscar_cancion_nombre_por_nombre("Imagine".to_string());
        assert!(resultado.is_some());
        assert_eq!(resultado.unwrap().titulo, "Imagine");
    }

    #[test]
    fn test_obtener_por_genero() {
        let c1 = cancion_ejemplo();
        let c2 = Cancion {
            titulo: "Blue in Green".to_string(),
            artista: "Miles Davis".to_string(),
            genero: Genero::jazz,
        };

        let playlist = Playlist {
            nombre: "Generos".to_string(),
            canciones: vec![c1.clone(), c2.clone()],
        };

        let rock = playlist.obtener_canciones_genero(Genero::rock);
        assert_eq!(rock.len(), 1);
        assert_eq!(rock[0].titulo, "Imagine");

        let jazz = playlist.obtener_canciones_genero(Genero::jazz);
        assert_eq!(jazz.len(), 1);
        assert_eq!(jazz[0].titulo, "Blue in Green");
    }

    #[test]
    fn test_obtener_por_artista() {
        let c1 = cancion_ejemplo();
        let c2 = Cancion {
            titulo: "Another song".to_string(),
            artista: "John Lennon".to_string(),
            genero: Genero::rap,
        };

        let playlist = Playlist {
            nombre: "Artistas".to_string(),
            canciones: vec![c1.clone(), c2.clone()],
        };

        let canciones = playlist.obtener_canciones_artista("John Lennon".to_string());
        assert_eq!(canciones.len(), 2);
    }

    #[test]
    fn test_modificar_titulo() {
        let mut playlist = Playlist {
            nombre: "Viejo nombre".to_string(),
            canciones: vec![],
        };
        playlist.modificar_titulo("Nuevo nombre".to_string());
        assert_eq!(playlist.nombre, "Nuevo nombre");
    }

    #[test]
    fn test_eliminar_todas() {
        let c1 = cancion_ejemplo();
        let mut playlist = Playlist {
            nombre: "Borrar todo".to_string(),
            canciones: vec![c1],
        };
        playlist.eliminar_todas_canciones();
        assert_eq!(playlist.canciones.len(), 0);
    }
}



fn main() {
    println!("Hello, world!");
}
