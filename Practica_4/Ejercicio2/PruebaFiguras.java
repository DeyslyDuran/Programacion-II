package Ejercicio2;

import java.util.Random;

public class PruebaFiguras {
    public static void main(String[] args) {
        Random rand = new Random();

        Figura[] figuras = new Figura[5];

        String[] colores = {"Rojo", "Azul", "Verde", "Amarillo", "Negro"};

        for (int i = 0; i < figuras.length; i++) {
            int tipo = rand.nextInt(2) + 1;
            String color = colores[rand.nextInt(colores.length)];

            if (tipo == 1) {
                double lado = 1 + rand.nextInt(10);
                figuras[i] = new Cuadrado(lado, color);
            } else {
                double radio = 1 + rand.nextInt(10);
                figuras[i] = new Circulo(radio, color);
            }
        }

        for (Figura f : figuras) {
            System.out.println(f.toString());
            System.out.println("Área: " + f.area());
            System.out.println("Perímetro: " + f.perimetro());

            if (f instanceof Coloreado) {
                Coloreado c = (Coloreado) f;
                System.out.println("Método comoColorear: " + c.comoColorear());
            }

            System.out.println("-------------------------");
        }
    }
}
