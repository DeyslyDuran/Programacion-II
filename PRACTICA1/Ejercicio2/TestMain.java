package Ejercicio2;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner lee = new Scanner(System.in);

        System.out.println("Ingrese a, b, c: ");
        float a = lee.nextFloat();
        float b = lee.nextFloat();
        float c = lee.nextFloat();

        EcuacionLineal ec= new EcuacionLineal(a,b,c);
        if(ec.getDiscriminante()>0){
            System.out.println("La ecuacion tiene dos racices "+ec.getRaiz1()+" y "+ ec.getRaiz2());
        }else if(ec.getDiscriminante()==0){
            System.out.println("La ecuacion tiene una raiz "+ec.getRaiz1());
        }else {
            System.out.println("La ecuacion no tiene raices reales");
        }


    }
}


