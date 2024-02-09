package pe.joedayz.ecommerce;

public class Main
{

    public static void main(String[] args) {
        // Ctrl + Alt + N | Opt + Cmd + N
        lower("JoeDayz", "bar");
        lower("Joe", "bar");  //Duplica la linea con Ctrl + D | Cmd D
        lower("Dayz", "bar");  //Duplica la linea con Ctrl + D | Cmd D
    }

    //Ctrl + Alt +M | Opt + Cmd + M
    private static void lower(String joeDayz, String foo) { //ctrl + f6 | Cmd + f6
        System.out.println(joeDayz.toLowerCase());
    }
}
