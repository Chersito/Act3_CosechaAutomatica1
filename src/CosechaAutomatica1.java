// Libreria Random genera números aleatorios
import java.util.Random;
// Libreria Scanner lee datos de entrada en consola
import java.util.Scanner;

// Clase Cosechador que extiende de Thread para manejar hilos
class Cosechador extends Thread {
    // Vectores que almacenan los nombres de frutas, verduras y su cantidad
    private final int[] invernadero;
    private final String[] fruits;
    private final String[] vegetables;

    // Constructor con invernadero tamaño n
    public Cosechador(int[] invernadero, String[] fruits, String[] vegetables, int n) {
        this.invernadero = new int[n];
        this.fruits = fruits;
        this.vegetables = vegetables;
    }

    // Sobreescribir el método run()
    @Override
    public void run() {
        // Itera cada producto para que aparezca en consola
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

// Clase Analizador que extiende de Thread para manejar hilos
class Analizador extends Thread {
    // invernadores contienen los datos, products tienen el nombre del dato
    private final int[] invernaderoA;
    private final int[] invernaderoB;
    private final String[] products;
    private final int n;

    //Constructor
    public Analizador(int[] invernaderoA, int[] invernaderoB, String[] products, int n) {
        this.invernaderoA = invernaderoA;
        this.invernaderoB = invernaderoB;
        this.products = products;
        this.n = n;
    }

    // Sobreescribir el método run()
    @Override
    public void run() {
        System.out.println("\nInicia análisis de cosecha Analizador");
        System.out.println("---------------------------------------------------------------------");
        // Itera cada producto para que aparezca en consola
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
            int totalA = random.nextInt(11) + 90; //Número random del 90 al 100
            int totalB = random.nextInt(11) + 90; //Número random del 90 al 100
            int total = totalA + totalB;
            double exito = (double) total / 2;
            double error = 100 - exito;

            System.out.printf("%s\t  %d\t     %d\t\t       %d\t   %.1f\t  %.1f%n", product, totalA, totalB, total, exito, error);

            // Verificar si el éxito es igual a 100 o no
            if (exito == 100) {
                System.out.println("¡Analizador ---> La cosecha de " + product + " ha alcanzado el 100% de éxito!");
            } else {
                System.out.println("Analizador ---> Aún se debe calibrar los sistemas de IA para obtener el 100%");
            }
        }
        System.out.println("---------------------------------------------------------------------");
    }
}


// Clase Pública
public class CosechaAutomatica1 {
    // Se crean cadenas con los nombres de frutas y verduras respectivamente
    private static final String[] FRUITS = {"Manzana", "Plátano", "Fresa", "Naranja", "Pera", "Uva", "Piña", "Mango", "Sandía", "Cereza"};
    private static final String[] VEGETABLES = {"Pepinillo", "Lechuga", "Tomate", "Zanahoria", "Cebolla", "Pimiento", "Brócoli", "Espinaca", "Calabacín", "Apio"};
    // Representa cada invernadero, utilizando los datos de frutas y verduras después
    private static int[] invernaderoA; // 10 frutas + 10 verduras
    private static int[] invernaderoB; // 10 frutas + 10 verduras

    // MAIN
    public static void main(String[] args) throws InterruptedException {
        // Se crea objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Se pide al usuario introducir el dato
        System.out.print("\nmain: ¿Cuántos productos diferentes se cosecharán?: ");
        int n = scanner.nextInt();

        // Se inicializan los Vectores con el tamaño n ya predefinido
        invernaderoA = new int[n];
        invernaderoB = new int[n];

        // Se crean objetos de la clase Cosechador que contienen hilos
        Cosechador cosechadorA = new Cosechador(invernaderoA, FRUITS, VEGETABLES, n);
        Cosechador cosechadorB = new Cosechador(invernaderoB, FRUITS, VEGETABLES, n);
        
        // Se asigna nombre a los hilos
        cosechadorA.setName("Robot_Chiapas");
        cosechadorB.setName("Robot_Cuernavaca");
        
        // Se inicializan los hilos
        cosechadorA.start();
        cosechadorB.start();

        System.out.println("Inicia cosecha Robot_Chiapas");
        System.out.println("Inicia cosecha Robot_Cuernavaca");
        System.out.println("---------------------------------------------------------------------");

        // Se espera hasta que terminen los hilos su ejecución
        cosechadorA.join();
        cosechadorB.join();

        System.out.println("\nRobot_Chiapas terminó la cosecha del día");
        System.out.println("Robot_Cuernavaca terminó la cosecha del día");

        // Se crea objeto de clase Analizador y se inicializa
        Analizador analizadorProducto = new Analizador(invernaderoA, invernaderoB, FRUITS, n);
        analizadorProducto.start();

        // Se detienen scanner
        scanner.close();
    }
}
