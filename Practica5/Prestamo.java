import java.util.Date;
import java.util.Calendar;
public class Prestamo {
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Estudiante estudiante;
    private Libro libro;

    public Prestamo(Estudiante estudiante, Libro libro) {
        this.fechaPrestamo = new Date();
        this.estudiante = estudiante;
        this.libro = libro;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaPrestamo);
        calendario.add(Calendar.DAY_OF_MONTH, 7);
        this.fechaDevolucion = calendario.getTime();

    }
    public void mostrarInfo() {
        System.out.println("Préstamo de: " + estudiante.getNombre() +
                " | Libro: " + libro.getTitulo() +
                " | Fecha préstamo: " + fechaPrestamo +
                " | Fecha devolución: " + fechaDevolucion);
    }
}
