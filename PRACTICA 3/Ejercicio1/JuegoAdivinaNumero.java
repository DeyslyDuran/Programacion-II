package Ejercicio1;
import java.util.Random;
import java.util.Scanner;

class JuegoAdivinaNumero extends Juego {
    private int numeroAAdivinar;

    public JuegoAdivinaNumero(int vidas) {
        super(vidas);
    }

    public void juega() {
        reiniciaPartida();
        Random rand = new Random();
        numeroAAdivinar = rand.nextInt(11); 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Adivine un número entre el 0 y el 10.");
            int intento = scanner.nextInt();

            if (intento == numeroAAdivinar) {
                System.out.println("Acertaste!!");
                actualizaRecord();
                return;
            } else {
                boolean quedanVidas = quitaVida();
                if (!quedanVidas) {
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
