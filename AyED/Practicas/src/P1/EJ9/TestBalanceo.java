package P1.EJ9;

import java.util.Scanner;
import java.util.Stack;



public class TestBalanceo {
    private static boolean balanceado(String s){
        if (s.length()%2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if (c == '(' || c == '[' || c == '{') {stack.push(c);}
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) return false;
            }
        }
        return stack.isEmpty(); // si esta vacia = true
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("=== TEST DE BALANCEO DE CARACTERES ===");
        System.out.print("Ingrese los caracteres: ");
        String txt = s.nextLine();

        if(balanceado(txt)){
            System.out.println("Correcto.");
        }
        else{
            System.out.println("Incorrecto");
        }
    }
}
