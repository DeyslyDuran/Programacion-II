package Practica6;

import java.util.Calendar;
import java.util.Date;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que representa un Préstamo de libro
 * Demuestra ASOCIACIÓN entre Estudiante y Libro
 */
public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Estudiante estudiante;
    private Libro libro;
    private boolean activo;

    /**
     * Constructor de la clase Prestamo
     * Calcula automáticamente la fecha de devolución (7 días después)
     * @param estudiante Estudiante que realiza el préstamo
     * @param libro Libro que se presta
     */
    public Prestamo(Estudiante estudiante, Libro libro) {
        this.fechaPrestamo = new Date();
        this.estudiante = estudiante;
        this.libro = libro;
        this.activo = true;

        // Calcular fecha de devolución (7 días después)
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaPrestamo);
        calendario.add(Calendar.DAY_OF_MONTH, 7);
        this.fechaDevolucion = calendario.getTime();

        // Marcar el libro como prestado
        libro.setPrestado(true);
    }

    /**
     * Muestra la información del préstamo en consola
     */
    public void mostrarInfo() {
        System.out.println("Préstamo de: " + estudiante.getNombre() +
                " | Libro: " + libro.getTitulo() +
                " | Fecha préstamo: " + fechaPrestamo +
                " | Fecha devolución: " + fechaDevolucion +
                " | Estado: " + (activo ? "ACTIVO" : "DEVUELTO"));
    }

    /**
     * Marca el préstamo como devuelto y libera el libro
     */
    public void devolver() {
        this.activo = false;
        this.libro.setPrestado(false);
    }

    // Getters
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Libro getLibro() {
        return libro;
    }

    public boolean isActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return estudiante.getNombre() + " - " + libro.getTitulo() +
                " (Dev: " + fechaDevolucion + ")" + (activo ? " [ACTIVO]" : " [DEVUELTO]");
    }
}