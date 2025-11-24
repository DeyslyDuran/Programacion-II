package Practica6;

import java.util.ArrayList;

import java.io.Serializable;

public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String isbn;
    private ArrayList<Pagina> paginas;
    private boolean prestado;

    static class Pagina implements Serializable {
        private static final long serialVersionUID = 1L;
        private int numero;
        private String contenido;


        public Pagina(int numero, String contenido) {
            this.numero = numero;
            this.contenido = contenido;
        }

        public void mostrarPagina() {
            System.out.println("Pagina " + this.numero + " - " + this.contenido);
        }

        public int getNumero() {
            return numero;
        }

        public String getContenido() {
            return contenido;
        }
    }

    public Libro(String titulo, String isbn, String[] contenidos) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas = new ArrayList<>();
        this.prestado = false;

        // Crear páginas automáticamente
        for (int i = 0; i < contenidos.length; i++) {
            this.paginas.add(new Pagina(i + 1, contenidos[i]));
        }
    }

    public void leer() {
        System.out.println("Leyendo el libro: " + titulo);
        for (Pagina p : paginas) {
            p.mostrarPagina();
        }
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public ArrayList<Pagina> getPaginas() {
        return paginas;
    }

    @Override
    public String toString() {
        return titulo + " [" + isbn + "]" + (prestado ? " - PRESTADO" : " - DISPONIBLE");
    }
}