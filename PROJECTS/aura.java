import java.util.Scanner;


public class aura {
    public static void main(String[] args) {
        
            
            //EJERCICIO 4


        int[] arreglo = new int[10];
        System.out.println("La longitud del arreglo es: " + arreglo.length);

        Scanner sc = new Scanner(System.in);
        for (int i=0; i<arreglo.length; i++) {
            System.out.print("Ingrese el nuevo valor de la posicion " + i + " : ");
            arreglo[i] = sc.nextInt();
        }
        for (int i=0; i<arreglo.length; i++){            
            if (arreglo[i] > 5) {
                arreglo[i]= 100;
            }
                    
        }
        sc.close();

                        
        for (int i = 0; i<arreglo.length; i++) {
                    
        System.out.println("Arreglo en la posición " + i + " es: " +arreglo[i]);
                    
        }
    




            // EJERCICIO 5

        int[][] matriz1 = {
            {2, 4, 0},
            {6, 2, 3},
            {5, 2, 1}
        };
            
        int[][] matriz2 = {
            {1, 1, 2},
            {2, 1, 1},
            {1, 2, 1}
        };
        System.out.println(matriz1); System.out.println(matriz2);
            




    }
}

