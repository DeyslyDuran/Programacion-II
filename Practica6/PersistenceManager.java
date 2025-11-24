package Practica6;

import java.io.*;

import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {
    private static final String ARCHIVO_DATOS = "biblioteca.dat";
    private static final String ARCHIVO_ESTUDIANTES = "estudiantes.dat";


    public static void guardarBiblioteca(Biblioteca biblioteca) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(biblioteca);
            System.out.println("✓ Datos de biblioteca guardados exitosamente en " + ARCHIVO_DATOS);
        }
    }


    public static Biblioteca cargarBiblioteca() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO_DATOS);
        if (!archivo.exists()) {
            System.out.println("⚠ No se encontró archivo de datos. Creando nueva biblioteca.");
            return new Biblioteca("Biblioteca Central UMSA");
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_DATOS))) {
            Biblioteca biblioteca = (Biblioteca) ois.readObject();
            System.out.println("✓ Datos de biblioteca cargados exitosamente desde " + ARCHIVO_DATOS);
            return biblioteca;
        }
    }

    public static void guardarEstudiantes(ArrayList<Estudiante> estudiantes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_ESTUDIANTES))) {
            oos.writeObject(estudiantes);
            System.out.println("✓ Datos de estudiantes guardados exitosamente en " + ARCHIVO_ESTUDIANTES);
        }
    }


    @SuppressWarnings("unchecked")
    public static ArrayList<Estudiante> cargarEstudiantes() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO_ESTUDIANTES);
        if (!archivo.exists()) {
            System.out.println("⚠ No se encontró archivo de estudiantes. Creando lista vacía.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_ESTUDIANTES))) {
            ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) ois.readObject();
            System.out.println("✓ Datos de estudiantes cargados exitosamente desde " + ARCHIVO_ESTUDIANTES);
            return estudiantes;
        }
    }

    public static boolean existeArchivoBiblioteca() {
        return new File(ARCHIVO_DATOS).exists();
    }

    public static boolean existeArchivoEstudiantes() {
        return new File(ARCHIVO_ESTUDIANTES).exists();
    }

    public static void eliminarArchivoBiblioteca() {
        File archivo = new File(ARCHIVO_DATOS);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("✓ Archivo de datos de biblioteca eliminado.");
            } else {
                System.out.println("✗ No se pudo eliminar el archivo de biblioteca.");
            }
        }
    }

    public static void eliminarArchivoEstudiantes() {
        File archivo = new File(ARCHIVO_ESTUDIANTES);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("✓ Archivo de datos de estudiantes eliminado.");
            } else {
                System.out.println("✗ No se pudo eliminar el archivo de estudiantes.");
            }
        }
    }

    public static void eliminarTodosLosArchivos() {
        eliminarArchivoBiblioteca();
        eliminarArchivoEstudiantes();
    }

    public static String obtenerInformacionArchivos() {
        StringBuilder info = new StringBuilder();
        info.append("=== Información de Archivos de Persistencia ===\n\n");

        File archivoBib = new File(ARCHIVO_DATOS);
        if (archivoBib.exists()) {
            info.append("📄 ").append(ARCHIVO_DATOS).append("\n");
            info.append("   Tamaño: ").append(archivoBib.length()).append(" bytes\n");
            info.append("   Última modificación: ").append(new java.util.Date(archivoBib.lastModified())).append("\n\n");
        } else {
            info.append("📄 ").append(ARCHIVO_DATOS).append(" - NO EXISTE\n\n");
        }

        File archivoEst = new File(ARCHIVO_ESTUDIANTES);
        if (archivoEst.exists()) {
            info.append("📄 ").append(ARCHIVO_ESTUDIANTES).append("\n");
            info.append("   Tamaño: ").append(archivoEst.length()).append(" bytes\n");
            info.append("   Última modificación: ").append(new java.util.Date(archivoEst.lastModified())).append("\n");
        } else {
            info.append("📄 ").append(ARCHIVO_ESTUDIANTES).append(" - NO EXISTE\n");
        }

        return info.toString();
    }
}