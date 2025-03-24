package P1.EJ2;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Escriba un numero: ");
        int n = s.nextInt();

        int[] v = ej2.n_multiplos(n);
        for(int i=0; i<n; i++){
            System.out.print("-> " + v[i] + " ");
        }
    }
}
