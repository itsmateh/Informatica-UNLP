package P1.EJ1;
public class ej1 {
    public static void withFor(int a, int b) {
        for(int i=a; i<=b; i++) {
            System.out.println(i);
        }
    }
    public static void withWhile(int a, int b) {
        while(a<=b) {
            System.out.println(a++);
        }
    }
    public static void withRecursion(int a, int b) {
        if(a<=b) {
            System.out.println(a);
            withRecursion(a+1, b);
        }
    }


}
