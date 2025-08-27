public class EcuacionLineal {
    private float a,b,c,d,e,f;
    public EcuacionLineal(float a, float b, float c, float d,float e, float f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    public boolean tieneSolucion() {
        return (a * d - b * c) != 0;
    }
    public float getX() {
        return (e * d - b * f) / (a * d - b * c);
    }
    public float getY() {
        return (a * f - e * c) / (a * d - b * c);
    }
}
