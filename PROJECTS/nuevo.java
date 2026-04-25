package PROJECTS;
import java.util.Scanner; // es como el input en python

public class nuevo {
    public static void main(String[] args) {
        System.out.println("Give a year: "); 
        try (Scanner sc = new Scanner(System.in)) {
            int year = Integer.valueOf(sc.nextLine());
            if (year < 2015) {
                System.out.println("Ancient history!");
            }   
        }
        
        System.out.println("Hola Mundo");
        int codigo = 32;
        System.out.println(codigo);
        System.out.println("32");
        double pi = 3.14;
        System.out.println(pi);

        //Una variable decimal se puede convertir en una entera, pero si asignas un valor que no le corresponde al tipo de variable definida
        double point = 1.0;
        point=1; // se cambia el valor de la variable sin volver a definir el tipo de la variable
        int value = 10;
        point = value;
        System.out.println(point);
        System.out.println(value);

        System.out.println("Hola\nMundo"); // '\n' sirve para saltar lineas
        int dividend =3;
        int divisor = 2;
        double result = dividend/(divisor*1.0);
        System.out.println(result);
        

    }
}