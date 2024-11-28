package org.example;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Examen {
    public void ejer1() {
        Scanner entrada = new Scanner(System.in);
        Random random = new Random();

        System.out.println("*** BIENVENIDO AL BINGO DEL CASINO CANTÁBRICO ***");
        int N_Bolas = random.nextInt(30) + 10;
        int[] Total_Bolas = new int[N_Bolas]; //creamos un array que tenga entre 10 y 40 espacios

        int[] Bolas_Repe; //creamos un array de comparación.
        do {
            for (int i = 0; i < N_Bolas; i++) {
                Total_Bolas[i] = random.nextInt(90) + 1;
            }

            Bolas_Repe = Arrays.stream(Total_Bolas).distinct().toArray();
        } while (Bolas_Repe.length != N_Bolas);
        //este bucle lo que hace es meter dentro de todos los huecos de Total_Bolas un número aleatorio entre 1 y 90.
        //luego mete dentro del array de comparación los valores de Total_Bolas sin duplicidades, es decir que si hay duplicidades, se eliminarán del vector.
        //Al elimininarse del vector, su longitud ya no será la misma, y por tanto volverá a randomizarlo hasta que no haya duplicados.

        System.out.println(N_Bolas + " bolas extraídas hasta ahora: " + Arrays.toString(Total_Bolas)); //muestra todas las bolas que se han generado.

        System.out.println(" ");
        System.out.println("*** Introduce los datos de tu cartón ***");

        String[][] carton = new String[3][3];
        String[] filas; //se crea una matriz que contenga los valores del cartón del jugador con dimensiones 3x3.
        //además de un array auxiliar que nos servirá más adelante.

        for (int i = 0; i < 3; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            String fila = entrada.next();//escribe los valores de tu cartón.

            boolean formato = fila.matches("\\d{1,2}-\\d{1,2}-\\d{1,2}"); //crea un booleano que compruebe que sigues el formato correcto.

            if (!formato) {
                System.out.println("Cerrando programa... Introduce valores con el formato correcto (N-N-N)"); //si el formato no coincide te lo dice y te saca del programa.
                return;
            } else {
                filas = fila.split("-");
                carton[i] = filas; //si todo está correcto, utiliza como delimitante el guión del formato y lo guarda en el vector auxiliar de antes.
                //por último guarda todos los valores de ese vector en cada una de las filas de la matriz.
            }
        }

        System.out.println(" ");
        System.out.println("Datos del cartón introducido:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(carton[i][j] + " ");
            }
            System.out.println();
        }
        //mostramos por pantalla la matriz de nuestro cartón.

        System.out.println(" ");
        System.out.println("Premios: ");

        int bingo = 0;
        int linea = 0;
        int linea_ganadora = 0; //estas variables no ayudarán a calcular los premios que nos corresponden por acertar o no las líneas.

        for (int i = 0; i < N_Bolas; i++) { //recorre todos los números de Total_Bolas.
            for (int j = 0; j < 3; j++) { //recorre las filas del cartón.
                linea = 0; //reestablece el valor de la línea a 0 cuando pasemos de fila para que el valor no se acumule y tenga como máximo hasta el 3 como valor.
                for (int k = 0; k < 3; k++) { //recorre las columnas del carton.
                    int num = Integer.parseInt(carton[j][k]); //coinvierte los valores del carton a números para operar con ellos.
                    if (num == Total_Bolas[i]) {
                        bingo++;
                        linea++; //si un número se acierta, se incrementa tanto la varible bingo como linea
                    }

                    if (linea == 3) {
                        linea_ganadora = j; //si linea es 3, significa que ha acertado una línea, y debido a ello, guardará la línea ganadora
                    }
                }
            }
        }

        if (bingo < 9) {
            System.out.println("No hay premio.");
        } else if (bingo == 9){
            System.out.println("BINGO!!");
            return;
        } //si el valor de bingo después de la comprobación del todos los números no es 9, entonces no es bingo, pero si es 9, entonces es bingo

        if (linea == 3) {
            for (int i = 1; i <= 3; i++) {
                if (i != linea_ganadora) {
                    System.out.println("Línea " + i + ": NO");
                } else {
                    System.out.println("Línea " + i + ": CORRECTA!!");
                }
            }
        } //este bucle te dice cuál línea corresponde a la ganadora en caso de no haber hecho bingo
    }
}