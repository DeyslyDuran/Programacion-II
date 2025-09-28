package Ejercicio1;

abstract class Empleado {
    private String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }
    abstract double CalcularSalarioMensual();

    public String getNombre() {
        return nombre;
    }

    public String toString() {
        return "Empleado =" + nombre ;
    }
}
