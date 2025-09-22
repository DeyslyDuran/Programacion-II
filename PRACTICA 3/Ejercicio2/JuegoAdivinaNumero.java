package Ejercicio2;

import java.util.Random;
import java.util.Scanner;


class JuegoAdivinaNumero extends Juego {
    protected int numeroAAdivinar;  // Protected para acceso en subclases

    public JuegoAdivinaNumero(int vidas) {
        super(vidas);
    }

    // a) Nuevo método: valida si el número está entre 0 y 10
    public boolean validaNumero(int numero) {
        return (numero >= 0 && numero <= 10);
    }

    public void juega() {
        reiniciaPartida();
        Random rand = new Random();
        numeroAAdivinar = rand.nextInt(11); // Entre 0 y 10 inclusive
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Adivine un número entre el 0 y el 10.");
            int intento;
            while (true) { // b) Bucle para validar input con validaNumero
                intento = scanner.nextInt();
                if (validaNumero(intento)) {
                    break; // Válido, sale del bucle de validación
                } else {
                    System.out.println("Número inválido. Debe estar entre 0 y 10. Intente de nuevo.");
                }
            }

            if (intento == numeroAAdivinar) {
                System.out.println("Acertaste!!");
                actualizaRecord();
                return;
            } else {
                boolean quedanVidas = quitaVida();
                if (!quedanVidas) {
                    System.out.println("Te quedaste sin vidas. El número era: " + numeroAAdivinar);
                    return;
                } else {
                    if (intento < numeroAAdivinar) {
                        System.out.println("El número a adivinar es mayor.");
                    } else {
                        System.out.println("El número a adivinar es menor.");
                    }
                    // Continúa el bucle para pedir otro intento
                }
            }
        }
    }
}