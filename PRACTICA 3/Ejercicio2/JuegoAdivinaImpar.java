package Ejercicio2;

import java.util.Random;
import java.util.Scanner;


class JuegoAdivinaImpar extends JuegoAdivinaNumero {
    public JuegoAdivinaImpar(int vidas) {
        super(vidas);
    }

    // b) Redefinición: true si es impar Y entre 0-10. Mensaje si par o fuera de rango.
    @Override
    public boolean validaNumero(int numero) {
        if (numero < 0 || numero > 10) {
            System.out.println("Número inválido: debe estar entre 0 y 10.");
            return false;
        }
        if (numero % 2 == 0) { // Es par (incluye 0 y 10)
            System.out.println("Error: Solo se permiten números impares en este juego.");
            return false;
        }
        return true;
    }

    // Redefinición para generar solo números impares (1,3,5,7,9) - asegura jugabilidad
    @Override
    public void juega() {
        reiniciaPartida();
        Random rand = new Random();
        numeroAAdivinar = (rand.nextInt(5) * 2) + 1; // Impares: 1,3,5,7,9
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al Juego de Adivinanza de Números Impares!");

        while (true) {
            System.out.println("Adivine un número impar entre el 1 y el 9.");
            int intento;
            while (true) { // Usa la validación redefinida
                intento = scanner.nextInt();
                if (validaNumero(intento)) {
                    break;
                }
                // Si inválido, el mensaje ya se mostró en validaNumero
            }

            if (intento == numeroAAdivinar) {
                System.out.println("¡Acertaste!! Era un número impar.");
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
                }
            }
        }
    }
}
