package P1.EJ5;

public class main {
    public static void main(String[] args) {
        //A
        int[] vA = {15,25,30};
        String ans = ej5.withReturn(vA);
        System.out.println("A");
        System.out.println(ans);

        System.out.println(" ");

        //B
        int[] vB = {150,250,300};
        ej5.Info stats = new ej5.Info();
        ej5.withParameters(vB, stats);
        System.out.println("B");
        System.out.println("Max. Value: " + stats.max+
                           " Min. Value: " + stats.min +
                           " Mean: " + stats.mean);

        System.out.println(" ");

        //C
        int[] vC = {1500,2500,3000};
        ej5.withoutReturnAndParameters(vC);
        System.out.println("C");
        System.out.println("Max. Value: " + ej5.max+
                           " Min. Value: " + ej5.min +
                           " Mean: " + ej5.mean);


    }
}
