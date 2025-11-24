package Practica6;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase principal que representa la Biblioteca
 * Demuestra AGREGACIÓN (libros, autores, prestamos) y COMPOSICIÓN (horario)
 */
public class Biblioteca implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private ArrayList<Libro> libros;       // AGREGACIÓN
    private ArrayList<Autor> autores;      // AGREGACIÓN
    private ArrayList<Prestamo> prestamos; // ASOCIACIÓN
    private Horario horario;               // COMPOSICIÓN

    /**
     * Clase interna que representa el Horario de la biblioteca
     * Demuestra COMPOSICIÓN: el horario no puede existir sin la biblioteca
     */
    static class Horario implements Serializable {
        private static final long serialVersionUID = 1L;
        private String diasApertura;
        private String horaApertura;
        private String horaCierre;

        /**
         * Constructor de la clase Horario
         * @param diasApertura Días de apertura
         * @param horaApertura Hora de apertura
         * @param horaCierre Hora de cierre
         */
        public Horario(String diasApertura, String horaApertura, String horaCierre) {
            this.diasApertura = diasApertura;
            this.horaApertura = horaApertura;
            this.horaCierre = horaCierre;
        }

        /**
         * Muestra el horario de la biblioteca
         */
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

    /**
     * Constructor de la clase Biblioteca
     * Inicializa las colecciones y crea un horario por defecto
     * @param nombre Nombre de la biblioteca
     */
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.horario = new Horario("Lunes-Viernes", "08:00", "20:00");
    }

    /**
     * Agrega un libro a la colección de la biblioteca
     * @param libro Libro a agregar
     */
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    /**
     * Agrega un autor al registro de la biblioteca
     * @param autor Autor a agregar
     */
    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    /**
     * Realiza un préstamo de libro a un estudiante
     * @param estudiante Estudiante que solicita el préstamo
     * @param libro Libro a prestar
     * @return true si el préstamo fue exitoso, false si el libro ya está prestado
     */
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

    /**
     * Registra la devolución de un libro
     * @param prestamo Préstamo a devolver
     */
    public void devolverLibro(Prestamo prestamo) {
        prestamo.devolver();
        System.out.println("Libro devuelto exitosamente.");
    }

    /**
     * Muestra el estado completo de la biblioteca en consola
     */
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

    /**
     * Cierra la biblioteca eliminando todos los préstamos
     */
    public void cerrarBiblioteca() {
        prestamos.clear();
        System.out.println("La biblioteca '" + nombre + "' ha cerrado. Todos los préstamos se eliminaron.");
    }

    /**
     * Obtiene la lista de préstamos activos
     * @return ArrayList con los préstamos activos
     */
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