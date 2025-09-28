package Ejercicio1;

class EmpleadoTiempoCompleto extends Empleado {
    private double salario_anual;
    public EmpleadoTiempoCompleto(String nombre, double salario_anual) {
        super(nombre);
        this.salario_anual = salario_anual;
    }
    public double CalcularSalarioMensual() {
        return this.salario_anual /12;
    }
    public String toString() {
        return super.toString() +
                "Nombre : " + getNombre() +
                ", Salario Anual: " + salario_anual +
                ", Salario Mensual: " + CalcularSalarioMensual();
    }

}
