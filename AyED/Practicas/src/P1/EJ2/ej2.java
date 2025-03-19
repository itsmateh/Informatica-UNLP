package P1.EJ2;
public class ej2 {
    public static int[] n_multiplos(int n){
        if(n < 0) {
            // si el valor de N es negativo -> tiramos un error
            throw new IllegalArgumentException("El valor de N no puede ser negativo!");
        }
        int[] v = new int[n];
        if (n == 0){
            for(int i=0; i<n; i++){
                v[i] = 0;
            }
        }
        else{
            for(int i = 0; i < n; i++) {
                v[i] = n * (i+1);
            }
        }
        return v;
    }
}