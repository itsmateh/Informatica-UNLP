package P1.EJ3;

public class Profesor extends Persona {
    private String facultad;
    private String catedra;

    public Profesor(String nombre, String apellido, String email, String facultad, String catedra) {
        super(nombre, apellido, email);
        this.facultad = facultad;
        this.catedra = catedra;
    }

    public String getFacultad() {
        return facultad;
    }

    public String getCatedra() {
        return catedra;
    }

    @Override
    public String tusDatos(){
        return super.tusDatos() + "Facultad: " + this.getFacultad() + "Catedra: " + this.getCatedra();
    }

}
