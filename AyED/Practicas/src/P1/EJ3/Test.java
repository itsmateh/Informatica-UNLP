package P1.EJ3;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Estudiante[] estudiante = new Estudiante[2];
        Profesor[] profesor = new Profesor[3];
        Scanner s = new Scanner(System.in);

        System.out.println("CARGAR DATOS DE ALUMNO");
        for(int i=0; i<2; i++){
            System.out.println("Nombre: ");
            String nombre = s.nextLine();

            System.out.println("Apellido: ");
            String apellido = s.nextLine();

            System.out.println("Comision: ");
            String comision = s.nextLine();

            System.out.println("Email: ");
            String email = s.nextLine();

            System.out.println("Direccion: ");
            String direccion = s.nextLine();

            estudiante[i] = new Estudiante(nombre, apellido, comision, email, direccion);
        }

        System.out.println("-------------------");
        System.out.println("CARGAR DATOS PROFESORES");
        for(int i=0; i<3; i++){

            System.out.println("Nombre: ");
            String nombre = s.nextLine();

            System.out.println("Apellido: ");
            String apellido = s.nextLine();

            System.out.println("Facultad: ");
            String facultad = s.nextLine();

            System.out.println("Email: ");
            String email = s.nextLine();

            System.out.println("Catedra: ");
            String catedra = s.nextLine();

            profesor[i] = new Profesor(nombre, apellido, email, facultad, catedra);
        }
    }
}
