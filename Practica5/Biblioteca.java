import java.util.ArrayList;

class Biblioteca {
    private String nombre;
    private ArrayList<Libro> libros;       
    private ArrayList<Autor> autores;      
    private ArrayList<Prestamo> prestamos; 
    private Horario horario;              

    class Horario {
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
    }

    public Biblioteca(String nombre, String dias, String horaA, String horaC) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.horario = new Horario(dias, horaA, horaC);
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public void prestarLibro(Estudiante estudiante, Libro libro) {
        Prestamo p = new Prestamo(estudiante, libro);
        prestamos.add(p);
    }

    public void mostrarEstado() {
        System.out.println("===== Estado de la Biblioteca: " + nombre + " =====");
        horario.mostrarHorario();

        System.out.println("\nLibros disponibles:");
        for (Libro l : libros) {
            System.out.println("- " + l.getTitulo());
        }

        System.out.println("\nAutores registrados:");
        for (Autor a : autores) {
            a.mostrarDatosAutor();
        }

        System.out.println("\nPréstamos activos:");
        for (Prestamo p : prestamos) {
            p.mostrarInfo();
        }
    }

    public void cerrarBiblioteca() {
        prestamos.clear();
        System.out.println("La biblioteca '" + nombre + "' ha cerrado. Todos los préstamos se eliminaron.");
    }
}