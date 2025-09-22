package Ejercicio2;


class Juego {
    private int numeroDeVidas;
    private int record;

    public Juego(int vidas) {
        this.numeroDeVidas = vidas;
        this.record = 0;
    }

    public void reiniciaPartida() {
        this.record = 0;
    }

    public void actualizaRecord() {
        if (this.numeroDeVidas > this.record) {
            this.record = this.numeroDeVidas;
        }
    }

    public boolean quitaVida() {
        this.numeroDeVidas--;
        return this.numeroDeVidas > 0;
    }
}


