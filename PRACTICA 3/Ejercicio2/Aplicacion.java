package Ejercicio2;

class Aplicacion {
    public static void main(String[] args) {
      
        JuegoAdivinaNumero juegoNormal = new JuegoAdivinaNumero(3);
        JuegoAdivinaPar juegoPar = new JuegoAdivinaPar(3);
        JuegoAdivinaImpar juegoImpar = new JuegoAdivinaImpar(3);

        System.out.println("=== Juego Normal (0-10) ===");
        juegoNormal.juega();

        System.out.println("\n=== Juego de Pares (0-10 pares) ===");
        juegoPar.juega();

        System.out.println("\n=== Juego de Impares (1-9 impares) ===");
        juegoImpar.juega();
    }
}

