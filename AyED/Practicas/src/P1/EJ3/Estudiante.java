package P1.EJ3;

public class Estudiante extends Persona{

    private String comision;
    private String direccion;


    public Estudiante(String nombre, String apellido, String comision, String email, String direccion) {
        super(nombre, apellido, email);
        this.comision = comision;
        this.direccion = direccion;
    }

    public String getComision() {
        return comision;
    }


    public String getDireccion() {
        return direccion;
    }

    @Override
    public String tusDatos(){
       return super.tusDatos() + "Comision: " + this.getComision() + "Direccin: " + this.getDireccion();
    }
}
