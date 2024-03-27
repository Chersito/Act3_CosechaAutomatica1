import java.util.Random;
import java.util.Scanner;

class Cosechador extends Thread {
    private final int[] invernadero;
    private final String[] fruits;
    private final String[] vegetables;

    public Cosechador(int[] invernadero, String[] fruits, String[] vegetables, int n) {
        this.invernadero = new int[n];
        this.fruits = fruits;
        this.vegetables = vegetables;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < invernadero.length; i++) {
            String product;
            if (i < fruits.length) {
                product = fruits[i];
            } else {
                product = vegetables[i - fruits.length];
            }
            System.out.println("\t[" + getName() + "]:        Cosechando ... " + product);
        }
    }
}

class Analizador extends Thread {
    private final int[] invernaderoA;
    private final int[] invernaderoB;
    private final String[] products;
    private final int n;

    public Analizador(int[] invernaderoA, int[] invernaderoB, String[] products, int n) {
        this.invernaderoA = invernaderoA;
        this.invernaderoB = invernaderoB;
        this.products = products;
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println("\nInicia análisis de cosecha Analizador");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < products.length; i++) {
            if (i < n) {
                System.out.println("\t[AnalizadorProducto]: Analizando ... " + products[i]);
            }
        }
        
        System.out.println("\n\t\tESTADÍSTICAS DE LOS INVERNADEROS");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Producto   Chiapas   Cuernavaca     Total   %Exito   %Error");
        System.out.println("---------------------------------------------------------------------");
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String product = products[i];
            int totalA = random.nextInt(11) + 90;
            int totalB = random.nextInt(11) + 90;
            int total = totalA + totalB;
            double exito = (double) total / 2;
            double error = 100 - exito;

            System.out.printf("%s\t  %d\t     %d\t\t       %d\t   %.1f\t  %.1f%n", product, totalA, totalB, total, exito, error);
        }
        System.out.println("---------------------------------------------------------------------");
    }
}



public class CosechaAutomatica1 {
    private static final String[] FRUITS = {"Manzana", "Plátano", "Fresa", "Naranja", "Pera", "Uva", "Piña", "Mango", "Sandía", "Cereza"};
    private static final String[] VEGETABLES = {"Pepinillo", "Lechuga", "Tomate", "Zanahoria", "Cebolla", "Pimiento", "Brócoli", "Espinaca", "Calabacín", "Apio"};
    private static int[] invernaderoA; // 10 frutas + 10 verduras
    private static int[] invernaderoB; // 10 frutas + 10 verduras

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nmain: ¿Cuántos productos diferentes se cosecharán?: ");
        int n = scanner.nextInt();

        invernaderoA = new int[n];
        invernaderoB = new int[n];

        Cosechador cosechadorA = new Cosechador(invernaderoA, FRUITS, VEGETABLES, n);
        Cosechador cosechadorB = new Cosechador(invernaderoB, FRUITS, VEGETABLES, n);
        
        cosechadorA.setName("Robot_Chiapas");
        cosechadorB.setName("Robot_Cuernavaca");
        
        cosechadorA.start();
        cosechadorB.start();

        System.out.println("Inicia cosecha Robot_Chiapas");
        System.out.println("Inicia cosecha Robot_Cuernavaca");
        System.out.println("---------------------------------------------------------------------");

        cosechadorA.join();
        cosechadorB.join();

        System.out.println("\nRobot_Chiapas terminó la cosecha del día");
        System.out.println("Robot_Cuernavaca terminó la cosecha del día");

        Analizador analizadorProducto = new Analizador(invernaderoA, invernaderoB, FRUITS, n);
        analizadorProducto.start();

        scanner.close();
    }
}
