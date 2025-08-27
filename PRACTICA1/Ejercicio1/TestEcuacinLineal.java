import java.util.Scanner;

public class TestEcuacinLineal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese a, b, c, d, e, f: ");
        float a = sc.nextFloat();
        float b = sc.nextFloat();
        float c = sc.nextFloat();
        float d = sc.nextFloat();
        float e = sc.nextFloat();
        float f = sc.nextFloat();

        EcuacionLineal eq = new EcuacionLineal(a,b,c,d,e,f);

        if (eq.tieneSolucion()) {
            System.out.println("x = " + eq.getX() + ", y = " + eq.getY());
        } else {
            System.out.println("La ecuación no tiene solución");
        }


        sc.close();

    }

}