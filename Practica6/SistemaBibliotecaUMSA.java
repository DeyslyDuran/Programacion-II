package Practica6;

public class SistemaBibliotecaUMSA {
    public static void main(String[] args) {
        // Crear biblioteca
        Biblioteca umsa = new Biblioteca("Biblioteca Central UMSA");

        // Crear autores
        Autor cervantes = new Autor("Miguel de Cervantes", "Española");
        Autor garcia = new Autor("Gabriel García Márquez", "Colombiana");

        // Crear libros
        String[] paginasDonQuijote = {"En un lugar de la Mancha...", "Segunda parte..."};
        String[] paginasCienAnios = {"Muchos años después...", "Macondo y su historia..."};
        Libro donQuijote = new Libro("Don Quijote de la Mancha", "ISBN12345", paginasDonQuijote);
        Libro cienAnios = new Libro("Cien Años de Soledad", "ISBN67890", paginasCienAnios);

        // Crear estudiantes
        Estudiante ana = new Estudiante("INF001", "Ana Flores");
        Estudiante juan = new Estudiante("INF002", "Juan Pérez");

        // Registrar autores y libros (AGREGACIÓN)
        umsa.agregarAutor(cervantes);
        umsa.agregarAutor(garcia);
        umsa.agregarLibro(donQuijote);
        umsa.agregarLibro(cienAnios);

        // Realizar préstamos (ASOCIACIÓN)
        umsa.prestarLibro(ana, donQuijote);
        umsa.prestarLibro(juan, cienAnios);

        // Mostrar estado de la biblioteca
        umsa.mostrarEstado();

        // Leer un libro
        System.out.println("\nLeyendo libro:");
        donQuijote.leer();

        // Cerrar biblioteca
        System.out.println("\nCerrando biblioteca...");
        umsa.cerrarBiblioteca();
    }
}