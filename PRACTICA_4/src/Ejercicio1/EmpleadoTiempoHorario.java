package Ejercicio1;

public class EmpleadoTiempoHorario  extends Empleado {
    private double horas_trabajadas;
    private double tarifa_por_hora;

    public EmpleadoTiempoHorario(String nombre, double horas_trabajadas, double tarifa_por_hora) {
        super(nombre);
        this.horas_trabajadas = horas_trabajadas;
        this.tarifa_por_hora = tarifa_por_hora;
    }

    public double CalcularSalarioMensual() {
        return horas_trabajadas * tarifa_por_hora;
    }
    public String toString() {
        return super.toString() +
                " Nombre : " + getNombre() +
                ", Horas Trabajadas: " + horas_trabajadas +
                ", Tarifa por Hora: " + tarifa_por_hora +
                ", Salario Mensual: " + CalcularSalarioMensual();
    }
}
