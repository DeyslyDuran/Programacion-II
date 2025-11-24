package Practica6;

import java.io.Serializable;

public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String nacionalidad;


    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

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