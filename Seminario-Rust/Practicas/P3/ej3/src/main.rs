
struct Fecha{
    dia:u32, 
    mes:u32,
    anio:u32,
}

impl Fecha {
    fn new(dia:u32, mes:u32, anio:u32) -> Fecha {
        return Fecha{dia, mes, anio} 
    }

    fn es_bisiesto(&self) -> bool {
        let ans:bool = (self.anio % 4 == 0 && self.anio % 100 != 0)  || (self.anio % 400 == 0);
        return ans
    }

    fn es_fecha_valida(&self) -> bool {
        let meses_30_dias:[u32; 4] = [4,6,9,11];
        if self.dia < 1 || self.dia > 31 || self.mes < 1 || self.mes > 12 || self.anio < 1 {return false}
        if self.dia > 28 && self.mes == 2 && !self.es_bisiesto() {return false}
        if meses_30_dias.contains(&self.mes) && self.dia > 30 {return false}

        return true
    }   

    // asumimos que quiere agregar +dias al mes, es decir que si agrega 90 dias, no se le suman 3 meses
    fn sumar_dias(&mut self, dias:u32){
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

    fn restar_dias(&mut self, dias:u32){
        if(self.dia > dias) {self.dia -= dias;}
    }

    fn es_mayor(&self, fecha:Fecha) -> bool {
       return (self.anio, self.mes, self.dia) > (fecha.anio, fecha.mes, fecha.dia)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_es_bisiesto() {
        let f = Fecha::new(1, 1, 2020);
        assert!(f.es_bisiesto());

        let f2 = Fecha::new(1, 1, 1900);
        assert!(!f2.es_bisiesto());

        let f3 = Fecha::new(1, 1, 2000);
        assert!(f3.es_bisiesto());
    }

    #[test]
    fn test_es_fecha_valida() {
        assert!(Fecha::new(31, 1, 2021).es_fecha_valida());
        assert!(!Fecha::new(31, 4, 2021).es_fecha_valida()); // abril solo tiene 30
        assert!(!Fecha::new(29, 2, 2021).es_fecha_valida()); // 2021 no es bisiesto
        assert!(Fecha::new(29, 2, 2020).es_fecha_valida());  // 2020 es bisiesto
        assert!(!Fecha::new(0, 1, 2020).es_fecha_valida());  // día inválido
    }

    #[test]
    fn test_sumar_dias() {
        let mut f = Fecha::new(25, 1, 2023);
        f.sumar_dias(5);
        assert_eq!(f.dia, 30);

        let mut f2 = Fecha::new(27, 2, 2020); // año bisiesto
        f2.sumar_dias(2);
        assert_eq!(f2.dia, 29);

        let mut f3 = Fecha::new(27, 2, 2021); // no bisiesto
        f3.sumar_dias(2);
        assert_eq!(f3.dia, 27); // no se suma, pasaría el limite
    }

    #[test]
    fn test_restar_dias() {
        let mut f = Fecha::new(10, 3, 2023);
        f.restar_dias(5);
        assert_eq!(f.dia, 5);

        let mut f2 = Fecha::new(5, 3, 2023);
        f2.restar_dias(6);
        assert_eq!(f2.dia, 5); // no cambia, iria a negativo
    }

    #[test]
    fn test_es_mayor() {
        let f1 = Fecha::new(10, 3, 2023);
        let f2 = Fecha::new(9, 3, 2023);
        assert!(f1.es_mayor(f2));

        let f3 = Fecha::new(10, 2, 2023);
        let f4 = Fecha::new(10, 3, 2022);
        assert!(!f4.es_mayor(f3));
    }
}


fn main() {
    println!("Confiamos en las respuestas aca che");
}
