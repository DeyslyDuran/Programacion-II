package Practica6;

import java.util.ArrayList;
import java.io.Serializable;

public class Biblioteca implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Autor> autores;
    private ArrayList<Prestamo> prestamos;
    private Horario horario;


    static class Horario implements Serializable {
        private static final long serialVersionUID = 1L;
        private String diasApertura;
        private String horaApertura;
        private String horaCierre;

        public Horario(String diasApertura, String horaApertura, String horaCierre) {
            this.diasApertura = diasApertura;
            this.horaApertura = horaApertura;
            this.horaCierre = horaCierre;
        }

        public void mostrarHorario() {
            System.out.println("Horario: " + diasApertura +
                    " | Abre: " + horaApertura +
                    " | Cierra: " + horaCierre);
        }

        public String getDiasApertura() {
            return diasApertura;
        }

        public String getHoraApertura() {
            return horaApertura;
        }

        public String getHoraCierre() {
            return horaCierre;
        }

        @Override
        public String toString() {
            return diasApertura + " | " + horaApertura + " - " + horaCierre;
        }
    }


    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.horario = new Horario("Lunes-Viernes", "08:00", "20:00");
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }


    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public boolean prestarLibro(Estudiante estudiante, Libro libro) {
        if (libro.isPrestado()) {
            System.out.println("El libro ya está prestado.");
            return false;
        }
        Prestamo p = new Prestamo(estudiante, libro);
        prestamos.add(p);
        System.out.println("Préstamo realizado exitosamente.");
        return true;
    }

    public void devolverLibro(Prestamo prestamo) {
        prestamo.devolver();
        System.out.println("Libro devuelto exitosamente.");
    }

    public void mostrarEstado() {
        System.out.println("===== Estado de la Biblioteca: " + nombre + " =====");
        horario.mostrarHorario();

        System.out.println("\nLibros disponibles:");
        for (Libro l : libros) {
            System.out.println("- " + l.toString());
        }

        System.out.println("\nAutores registrados:");
        for (Autor a : autores) {
            a.mostrarDatosAutor();
        }

        System.out.println("\nPréstamos activos:");
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                p.mostrarInfo();
            }
        }
    }


    public void cerrarBiblioteca() {
        prestamos.clear();
        System.out.println("La biblioteca '" + nombre + "' ha cerrado. Todos los préstamos se eliminaron.");
    }


    public ArrayList<Prestamo> getPrestamosActivos() {
        ArrayList<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public Horario getHorario() {
        return horario;
    }
}