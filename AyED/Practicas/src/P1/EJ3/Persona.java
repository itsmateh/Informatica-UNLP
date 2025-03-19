package P1.EJ3;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String email;

    public Persona(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String tusDatos(){
        return "Nombre " + this.getNombre() + "Apellido: " + this.getApellido() + "Email: " + this.getEmail();
    }
}
