package P1.EJ7;

import java.lang.reflect.Array;
import java.util.*;

public class TestArrayList {
    // F
    public static boolean esCapicua(ArrayList<Integer> lista){
        if(lista.size() == 1 || lista.isEmpty()){return true;}
        else
            if(!lista.get(0).equals(lista.get(lista.size()-1))){return false;}
            else return esCapicua(new ArrayList<>(lista.subList(1, lista.size()-1)));
    }
    // F v2
    public static boolean esCapicuaV2(ArrayList<Integer> lista){
        boolean check = true;
        if(!lista.isEmpty()){
            int primero = 0;
            int ultimo = lista.size() - 1;
            while(ultimo > primero && check){
                if(lista.get(primero).equals(lista.get(ultimo))){
                    primero++; ultimo--;
                }
                else check = false;
            }
        }
        return check;
    }

    public static void ej7DyE(){
        //D

        // i
        List<Estudiante> student_list = new ArrayList<P1.EJ7.Estudiante>();
        Estudiante e1 = new Estudiante("Mateo", "Campagna", "Licenciatura en Informatica");
        Estudiante e2 = new Estudiante("Valentin", "Volatile", "Analista Programador Universitario");
        Estudiante e3 = new Estudiante("Catalina", "Brochero", "Licenciatura en Sistemas");
        student_list.add(e1); student_list.add(e2); student_list.add(e3);

        // ii
        List<Estudiante> copy_list = new ArrayList<Estudiante>(student_list);

        // iii
        System.out.println("Lista original: ");
        for(Estudiante e:student_list){
            System.out.println(e.toString());
        }

        System.out.println("Lista nueva");
        for(Estudiante e:copy_list){
            System.out.println(e.toString());
        }

        // iv
        student_list.set(0, new Estudiante("Mateo^2", "Campagna^2", "Licenciatura en Informatica^2"));
        System.out.println("Lista Modificada");
        for(Estudiante e:student_list){
            System.out.println(e.toString());
        }

        // v
        List<Estudiante> variant_copy_list = new ArrayList<>();
        variant_copy_list.addAll(student_list);
        System.out.println("Lista copiada v2");
        for(Estudiante e:variant_copy_list){
            System.out.println(e.toString());
        }


        // E
        boolean ok = false;
        Estudiante e4 = new Estudiante("John", "Von Neumann", "BSc in Mathematics");
        if(!student_list.contains(e4)){
            student_list.add(e4);
            System.out.println("Estudiante agregado!");
        }
        else{
            System.out.println("Alumno repetido");
        }
        System.out.println("Lista reciente: ");
        for(Estudiante e:student_list) {
            System.out.println(e.toString());
        }
    }


    public static void main(String[] args) {
        // A
        List<Integer> number_list_AL = new  ArrayList<Integer>();
        for(int i=0; i<10; i++){
            number_list_AL.add(i+1);
        }
        for(Integer n:number_list_AL){
            System.out.print(n+" ");
        }
        System.out.print("| ");
        // B : Diferencias: el espacio en memoria en la LL no es continuo y en la AL si. Ademas de la eficienica de acceso, siendo mas eficiente en la AL.
        List<Integer> number_list_LL = new LinkedList<Integer>();
        for(int i=0; i<10; i++){
            number_list_LL.add(i+11);
        }
        for(Integer n:number_list_LL){
            System.out.print(n+" ");
        }

        // espacio
        System.out.println(" ");

        // C : Alternativas para recorrer una lista -> foreach, for(i,lista.size()) y un while.
        for(int i=0; i<number_list_AL.size(); i++){
            System.out.print(number_list_AL.get(i) + " ");
        }
        System.out.print("| ");
        for(Integer n:number_list_LL){
            System.out.print(n + " ");
        }
        System.out.print("| ");
        Iterator<Integer> iter = number_list_AL.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next() + " ");
        }


        ej7DyE();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Scanner s = new Scanner(System.in);
        System.out.println("Tamaño de la lista: ");
        int tam = s.nextInt();
        System.out.println("Ingrese " + tam + " valores: ");
        for(int i=0; i<tam; i++){
            Integer val = s.nextInt();
            arr.add(val);
        }
        System.out.println(esCapicua(arr));
        System.out.println(esCapicuaV2(arr));
    }
}
