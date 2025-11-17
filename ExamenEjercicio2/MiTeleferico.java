package ExamenEjercicio2;

public class MiTeleferico {
    private Linea[] lineas;
    private float cantidadIngresos;
    private int nroLineas;

    public MiTeleferico() {
        this.lineas = new Linea[5];
        this.nroLineas = 0;
    }
    public void agregarCabina(String lineaColor) {
        for (int i = 0; i < nroLineas; i++) {
            if (lineas[i].getColor().equals(lineaColor)) {
                lineas[i].agregarCabina(i + 1);
            }
        }
    }

    public void agregarPersonaFila(Persona p, String lineaColor) {
        for (int i = 0; i < nroLineas; i++) {
            if (lineas[i].getColor().equals(lineaColor)) {
                lineas[i].agregarPersona(p);
            }
        }
    }

    public void agregarLinea(Linea l) {
        lineas[nroLineas++] = l;
    }


    public float calcularIngresoTotal() {
        float total = 0;
        for (int i = 0; i < nroLineas; i++) {
            total += lineas[i].calcularIngreso();
        }
        this.cantidadIngresos = total;
        return total;
    }

    public Linea obtenerLineaMayorIngresoRegular() {
        Linea mayor = null;
        float max = -1;

        for (int i = 0; i < nroLineas; i++) {
            float ingreso = lineas[i].calcularIngreso();
            if (ingreso > max) {
                max = ingreso;
                mayor = lineas[i];
            }
        }
        return mayor;
    }
}

