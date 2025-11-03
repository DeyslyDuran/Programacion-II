
public class SistemaBibliotecaUmsa {
    public static void main(String[] args) {
        Biblioteca umsa = new Biblioteca("Biblioteca Central UMSA",
                "Lunes a Viernes", "08:00", "20:00");

        Autor cervantes = new Autor("Miguel de Cervantes", "Española");
        Autor garcia = new Autor("Gabriel García Márquez", "Colombiana");

        String[] paginasDonQuijote = {"En un lugar de la Mancha...", "Segunda parte..."};
        String[] paginasCienAnios = {"Muchos años después...", "Macondo y su historia..."};
        Libro donQuijote = new Libro("Don Quijote de la Mancha", "ISBN12345", paginasDonQuijote);
        Libro cienAnios = new Libro("Cien Años de Soledad", "ISBN67890", paginasCienAnios);

        Estudiante ana = new Estudiante("INF001", "Ana Flores");
        Estudiante juan = new Estudiante("INF002", "Juan Pérez");

        umsa.agregarAutor(cervantes);
        umsa.agregarAutor(garcia);
        umsa.agregarLibro(donQuijote);
        umsa.agregarLibro(cienAnios);

        umsa.prestarLibro(ana, donQuijote);
        umsa.prestarLibro(juan, cienAnios);

        umsa.mostrarEstado();

        System.out.println("\nLeyendo libro:");
        donQuijote.leer();

        System.out.println("\nCerrando biblioteca...");
        umsa.cerrarBiblioteca();
    }
}