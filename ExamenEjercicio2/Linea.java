package ExamenEjercicio2;

public class Linea {
    private String color;
    private Persona[] filaPersonas;
    private Cabina[] cabinas;
    private int nroCabinas;
    private int nroFila;

    public Linea(String color) {
        this.color = color;
        this.filaPersonas = new Persona[200];
        this.cabinas = new Cabina[20];
        this.nroCabinas = 0;
        this.nroFila = 0;
    }

    public void agregarCabina(int nroCabina) {
        cabinas[nroCabinas++] = new Cabina(nroCabina);
    }

    public void agregarPersona(Persona p) {
        filaPersonas[nroFila++] = p;
    }

    public boolean agregarPrimeraPersonaCabina(int nroCabina) {
        if (nroFila == 0) return false;

        Persona primera = filaPersonas[0];

        for (int i = 0; i < nroCabinas; i++) {
            if (cabinas[i].getNroCabina() == nroCabina) {
                return cabinas[i].agregarPersona(primera);
            }
        }

        return false;
    }

    public float calcularIngreso() {
        float total = 0;
        for (int i = 0; i < nroCabinas; i++) {
            total += cabinas[i].calcularIngreso();
        }
        return total;
    }

    public String getColor() {
        return color;
    }
}
