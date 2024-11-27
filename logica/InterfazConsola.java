package logica;

import java.util.Scanner;

/**
 * Clase que representa la interfaz de usuario en consola para el juego Wordle.
 * Extiende de la clase abstracta InterfazJuego.
 */
public class InterfazConsola extends InterfazJuego {

    /**
     * Constructor que inicializa la interfaz con una instancia del juego Wordle.
     *
     * @param juego La instancia del juego Wordle que se usará en la interfaz.
     */
    public InterfazConsola(JuegoWordle juego) {
        super(juego);
    }

    /**
     * Muestra las instrucciones del juego en la consola.
     */
    @Override
    public void mostrarInstrucciones() {
        // Mensajes que explican las reglas del juego Wordle
        System.out.println("Bienvenido al juego Wordle en consola!");
        System.out.println("Tienes 6 intentos para adivinar la palabra secreta.");
        System.out.println("Las letras verdes están en la posición correcta.");
        System.out.println("Las letras amarillas están en la palabra pero en otra posición.");
        System.out.println("Las letras rojas no están en la palabra.");
    }

    /**
     * Inicia el flujo de interacción con el usuario en la consola.
     * Permite ingresar palabras y muestra el resultado de cada intento.
     */
    @Override
    public void iniciar() {
        Scanner scanner = new Scanner(System.in); // Objeto para leer la entrada del usuario
        mostrarInstrucciones(); // Muestra las instrucciones del juego

        // Bucle principal del juego: sigue pidiendo palabras hasta que el juego termine
        while (!juego.esJuegoTerminado() && juego.getIntentosRestantes() > 0) {
            // Muestra los intentos restantes
            System.out.println("Intentos restantes: " + juego.getIntentosRestantes());
            // Solicita una palabra de 5 letras
            System.out.print("Ingresa tu palabra (5 letras): ");
            String palabra = scanner.nextLine().toUpperCase(); // Convierte la palabra ingresada a mayúsculas

            // Valida que la palabra tenga exactamente 5 letras
            if (palabra.length() != 5) {
                System.out.println("La palabra debe tener 5 letras.");
                continue; // Si la palabra no tiene 5 letras, vuelve a pedir la entrada
            }

            // Verifica el intento y muestra el resultado con los colores
            String resultado = juego.verificarIntento(palabra);
            System.out.println("Resultado: " + resultado); // Muestra el resultado de la comparación
        }

        // Al finalizar los intentos o adivinar la palabra, muestra un mensaje de finalización
        if (juego.getIntentosRestantes() == 0) {
            // Si se agotaron los intentos, muestra el mensaje de fracaso y la palabra correcta
            System.out.println("Has agotado todos los intentos. La palabra era: " + juego.getPalabraSecreta());
        } else {
            // Si el jugador adivinó la palabra, muestra un mensaje de éxito
            System.out.println("¡Felicidades, adivinaste la palabra!");
        }
    }
}

