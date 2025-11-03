public class Autor {
    private String nombre;
    private String nacionalidad;

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    public void mostrarDatosAutor(){
        System.out.println("Autor: " + this.nombre+ " con " + this.nacionalidad);
    }

}
