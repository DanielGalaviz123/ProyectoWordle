package logica;

import java.util.List;

public class JuegoWordle {
    private List<String> palabras; // Lista de palabras disponibles para el juego
    private String palabraSecreta; // Palabra que el jugador debe adivinar
    private int intentos; // Contador de intentos realizados
    private static final int MAX_INTENTOS = 6; // Numero maximo de intentos permitidos

    // Constructor que inicializa el juego con una lista de palabras
    public JuegoWordle(List<String> palabras) {
        this.palabras = palabras;
        this.palabraSecreta = seleccionarPalabraSecreta(); // Seleccionar una palabra aleatoria
        this.intentos = 0; // Inicializar los intentos en 0
    }



    // Metodo privado que selecciona una palabra secreta aleatoria de la lista
    private String seleccionarPalabraSecreta() {
        int index = (int) (Math.random() * palabras.size()); // Generar un indice aleatorio
        return palabras.get(index); // Retornar la palabra en la posicion aleatoria
    }


    

    // Metodo que verifica un intento y genera un resultado en colores
    public String verificarIntento(String intento) {
        intento = intento.toLowerCase(); // Asegurar que la palabra ingresada este en minusculas
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < intento.length(); i++) {
            char letra = intento.charAt(i); // Obtener la letra actual del intento
            if (palabraSecreta.charAt(i) == letra) {
                // La letra esta en la posicion correcta
                resultado.append("verde");
            } else if (palabraSecreta.contains(String.valueOf(letra))) {
                // La letra esta en la palabra pero en la posicion incorrecta
                resultado.append("amarillo");
            } else {
                // La letra no esta en la palabra
                resultado.append("rojo");
            }

            if (i < intento.length() - 1) {
                resultado.append(" "); // Agregar un espacio entre los colores
            }
        }

        intentos++; // Incrementar el contador de intentos
        return resultado.toString(); // Retornar el resultado como cadena de colores
    }

    // Metodo que verifica si el juego ha terminado (por numero de intentos)
    public boolean esJuegoTerminado() {
        return intentos >= MAX_INTENTOS; // Retorna true si se alcanzaron los intentos maximos
    }

    // Metodo que retorna la palabra secreta
    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    // Metodo para obtener la cantidad de intentos restantes
    public int getIntentosRestantes() {
        return MAX_INTENTOS - intentos; // Calcular los intentos restantes
    }

    // Metodo que verifica si una palabra ingresada es la correcta
    public boolean esPalabraCorrecta(String palabra) {
        return palabra.equalsIgnoreCase(this.palabraSecreta); // Comparar ignorando mayusculas/minusculas
    }
}
