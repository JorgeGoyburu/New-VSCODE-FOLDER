public class EjercicioArreglos {

    public static void main(String[] args) {
        
        // =========================================================================
        // PUNTO 1: Crear un arreglo de 5 elementos y llenarlo con el cuadrado de cada índice.
        // =========================================================================
        int[] numeros = new int[5];
        
        for (int i = 0; i < numeros.length; i++) {
            // El cuadrado de i es i * i (0*0, 1*1, 2*2, etc.)
            numeros[i] = i * i; 
        }

        // =========================================================================
        // PUNTO 2: Modificar el programa para calcular la suma y el promedio.
        // =========================================================================
        int suma = 0;
        
        // Recorremos el arreglo para acumular los valores
        for (int i = 0; i < numeros.length; i++) {
            suma += numeros[i];
        }
        
        // Calculamos el promedio. 
        // Usamos (double) para que la división sea exacta y no pierda los decimales.
        double promedio = (double) suma / numeros.length;

        // =========================================================================
        // PUNTO 3: Imprimir los elementos del arreglo separándolos con el símbolo |
        // =========================================================================
        System.out.print("Elementos del arreglo: ");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(numeros[i]);
            
            // Ponemos el separador "|" solo si NO es el último elemento
            if (i < numeros.length - 1) {
                System.out.print(" | ");
            }
        }
        
        // Imprimimos los resultados de los cálculos (Punto 2)
        System.out.println("\n---------------------------------");
        System.out.println("La suma de los elementos es: " + suma);
        System.out.println("El promedio de los elementos es: " + promedio);
    }
}