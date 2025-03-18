package P1.EJ1;
public class ej1 {
    public static void withFor(int a, int b) {
        for(int i=a; i<=b; i++) {
            System.out.print("|" + i);
        }
        System.out.println();
    }
    public static void withWhile(int a, int b) {
        while(a<=b) {
            System.out.print("|" + a++);
        }
        System.out.println();
    }
    public static void withRecursion(int a, int b) {
        if(a<=b) {
            System.out.print("|" + a);
            withRecursion(a+1, b);
        }
        System.out.println();
    }
}
