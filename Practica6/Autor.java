package Practica6;

import java.io.Serializable;
import java.io.Serializable;

/**
 * Clase que representa un Autor de libros
 * Implementa Serializable para permitir la persistencia de datos
 */
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String nacionalidad;

    /**
     * Constructor de la clase Autor
     * @param nombre Nombre completo del autor
     * @param nacionalidad Nacionalidad del autor
     */
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Muestra los datos del autor en consola
     */
    public void mostrarDatosAutor() {
        System.out.println("Autor: " + this.nombre + " con nacionalidad " + this.nacionalidad);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}