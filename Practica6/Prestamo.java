package Practica6;

import java.util.Calendar;
import java.util.Date;

import java.io.Serializable;

public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Estudiante estudiante;
    private Libro libro;
    private boolean activo;

    public Prestamo(Estudiante estudiante, Libro libro) {
        this.fechaPrestamo = new Date();
        this.estudiante = estudiante;
        this.libro = libro;
        this.activo = true;


        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaPrestamo);
        calendario.add(Calendar.DAY_OF_MONTH, 7);
        this.fechaDevolucion = calendario.getTime();

        libro.setPrestado(true);
    }

    public void mostrarInfo() {
        System.out.println("Préstamo de: " + estudiante.getNombre() +
                " | Libro: " + libro.getTitulo() +
                " | Fecha préstamo: " + fechaPrestamo +
                " | Fecha devolución: " + fechaDevolucion +
                " | Estado: " + (activo ? "ACTIVO" : "DEVUELTO"));
    }

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