import java.util.ArrayList;

public class Libro {
    private String titulo;
    private String isbn;
    private ArrayList<Pagina> paginas;

    class Pagina{
        private int numero;
        private String contenido;

        public Pagina(int numero, String contenido){
            this.numero = numero;
            this.contenido = contenido;
        }
        public void mostrarPagina(){
            System.out.println("Pagina "+this.numero + " - " + this.contenido);
        }
    }
    public Libro(String titulo, String ISBN, String[] contenidos){
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas = new ArrayList<>();

        for(int i = 0; i < contenidos.length; i++){
            this.paginas.add(new Pagina(i + 1, contenidos[i]));
        }
    }
    public String getTitulo(){
        return titulo;
    }
    public void leer() {
        System.out.println("Leyendo el libro: " + titulo);
        for (Pagina p : paginas) {
            p.mostrarPagina();
        }
    }


}
