package Practica6;

import java.io.Serializable;

/**
 * Clase que representa un Estudiante
 * Implementa Serializable para permitir la persistencia de datos
 */
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombre;

    /**
     * Constructor de la clase Estudiante
     * @param codigo Código único del estudiante
     * @param nombre Nombre completo del estudiante
     */
    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Muestra la información del estudiante en consola
     */
    public void mostrarInfo() {
        System.out.println("Nombre: " + this.nombre + " - Codigo: " + this.codigo);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}