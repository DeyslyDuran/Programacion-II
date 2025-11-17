package ExamenEjercicio2;

public class Cabina {
    private int nroCabina;
    private Persona[] personasAbordo;
    private int nroPersonas;

    public Cabina(int nroCabina) {
        this.nroCabina = nroCabina;
        this.personasAbordo = new Persona[10];
        this.nroPersonas = 0;
    }

    public boolean agregarPersona(Persona p) {
        float totalPeso = p.getPeso();

        for (int i = 0; i < nroPersonas; i++) {
            totalPeso += personasAbordo[i].getPeso();
        }

        if (nroPersonas < 10 && totalPeso <= 850) {
            personasAbordo[nroPersonas++] = p;
            return true;
        }

        return false;
    }

    public float calcularIngreso() {
        float total = 0;

        for (int i = 0; i < nroPersonas; i++) {
            int edad = personasAbordo[i].getEdad();

            if (edad < 25 || edad > 60)
                total += 1.5;
            else
                total += 3;
        }

        return total;
    }

    public int getNroCabina() {
        return nroCabina;
    }
}
