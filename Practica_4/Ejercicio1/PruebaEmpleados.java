package Ejercicio1;

import java.util.Scanner;

public class PruebaEmpleados {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Empleado[] empleados = new Empleado[5];


        for (int i = 0; i < 3; i++) {
            System.out.println("Ingrese el nombre del empleado a tiempo completo #" + (i+1));
            String nombre = sc.nextLine();

            System.out.println("Ingrese el salario anual de " + nombre + ":");
            double salarioAnual = sc.nextDouble();
            sc.nextLine();
            empleados[i] = new EmpleadoTiempoCompleto(nombre, salarioAnual);
        }

        for (int i = 3; i < 5; i++) {
            System.out.println("Ingrese el nombre del empleado a tiempo horario #" + (i-2));
            String nombre = sc.nextLine();

            System.out.println("Ingrese las horas trabajadas de " + nombre + ":");
            double horas = sc.nextDouble();

            System.out.println("Ingrese la tarifa por hora de " + nombre + ":");
            double tarifa = sc.nextDouble();
            sc.nextLine();

            empleados[i] = new EmpleadoTiempoHorario(nombre, horas, tarifa);
        }
        System.out.println("\n--- Lista de Empleados ---");
        for (Empleado e : empleados) {
            System.out.println(e.toString());
            System.out.println("Salario Mensual: " + e.CalcularSalarioMensual());
            System.out.println("----------------------------");
        }

        sc.close();
    }
}
