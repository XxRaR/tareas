package analizadorlex;
import java.util.Scanner; //Importa la clase Scanner para leer datos del usuario
import java.util.HashSet;   //Importa HashSet para almacenar el alfabeto sin duplicados


public class AnalizadorLex {

    public static void main(String[] args) {
        //Se carga el alfabeto definido en el método cargarAlfabeto()
        HashSet<Character> alfabeto = cargarAlfabeto();
        //Se crea un objeto Scanner para leer la entrada del usuario
        Scanner sc = new Scanner(System.in);
        
       
        
        //Se pide al usuario que ingrese una cadena de caracteres
        System.out.print("Ingrese el caracter a analizar: ");
        String entrada = sc.nextLine(); //SE ALMACENA LA ENTRADA POR USUARIO COMPLETA
        System.out.println(""); //SALTO DE LÍNE PARA SEPARAR LA SALIDA
        
        //SE RECORRE LA CADENA CARACTER POR CARACTER
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i); //Obtiene el caracter en la posición i
            int numL = i + 1;
            //Si el caracter es un espacio, se muestra como "Espacio"
            String espacio = (c == ' ') ? "Espacio" : "'" + c + "'";

            //Verifica si el caracter pertenece al alfabeto
            if (alfabeto.contains(c)) {
                System.out.println("Línea " + numL + " Símbolo " + espacio + " encontrado"); //MENSAJE SI EL SÍMBOLO FUE ENCONTRADO 
            } else {
                System.out.println("Línea " + numL + " Símbolo " + espacio + " no encontrado"); //MENSAJE SI EL SÍMBOLO NO FUE ENCONTRADO
            }
        }
        //SE CIERRA EL OBJETO SCANNER
        sc.close();
    }
    //MÉTODO QUE CONSTRUYE EL CONJUNTO DE CARACTERES PERMITIDOS (OSEA EL ALFABETO)
    private static HashSet<Character> cargarAlfabeto() {
        HashSet<Character> set = new HashSet<>();
        
        // Agregando todo el contenido, LETRAS Y NUMEROS
        String letrasYNum = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (char c : letrasYNum.toCharArray()) set.add(c);
        
        // agregando los operadores como los símbolos y espacio en blanco del alfabeto 
        char[] simbolos = {'+', '-', '*', '/', '<', '=', '>', '&', '!', '(', ')', '{', '}', ';', ':', ' '};
        for (char s : simbolos) set.add(s);
        
        //Se devuelve el conjunto completo del alfabeto
        return set;
    }
}