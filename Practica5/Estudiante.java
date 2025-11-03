
public class Estudiante {
    private String codigo;
    private String nombre;

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public void mostratInfo(){
        System.out.println("Nombre: " + this.nombre+" - Codigo: " + this.codigo);
    }
    public String getNombre() {
        return nombre;
    }
}
