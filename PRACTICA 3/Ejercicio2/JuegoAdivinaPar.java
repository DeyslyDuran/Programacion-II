package Ejercicio2;

import java.util.Random;
import java.util.Scanner;

class JuegoAdivinaPar extends JuegoAdivinaNumero {
    public JuegoAdivinaPar(int vidas) {
        super(vidas);
    }

    // b) Redefinición: true si es par Y entre 0-10. Mensaje si impar o fuera de rango.
    @Override
    public boolean validaNumero(int numero) {
        if (numero < 0 || numero > 10) {
            System.out.println("Número inválido: debe estar entre 0 y 10.");
            return false;
        }
        if (numero % 2 != 0) { // Es impar
            System.out.println("Error: Solo se permiten números pares en este juego.");
            return false;
        }
        return true;
    }

    // Redefinición para generar solo números pares (0,2,4,6,8,10) - asegura jugabilidad
    @Override
    public void juega() {
        reiniciaPartida();
        Random rand = new Random();
        numeroAAdivinar = rand.nextInt(6) * 2; // Pares: 0,2,4,6,8,10
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al Juego de Adivinanza de Números Pares!");

        while (true) {
            System.out.println("Adivine un número par entre el 0 y el 10.");
            int intento;
            while (true) { // Usa la validación redefinida
                intento = scanner.nextInt();
                if (validaNumero(intento)) {
                    break;
                }
                // Si inválido, el mensaje ya se mostró en validaNumero
            }

            if (intento == numeroAAdivinar) {
                System.out.println("¡Acertaste!! Era un número par.");
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