package logica;

import igu.InterfazGrafica;
import java.util.List;
import java.util.Scanner;
import persistencia.ArchivoPalabras;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Menú de selección de interfaz
            System.out.println("Selecciona una opción:");
            System.out.println("1. Interfaz Consola");
            System.out.println("2. Interfaz Gráfica");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de la nueva línea

            // Cargar las palabras desde el archivo
            List<String> palabras;
            try {
                String rutaArchivo = "C:\\Users\\danie\\OneDrive\\Escritorio\\palabras.txt"; 
                palabras = ArchivoPalabras.cargarPalabras(rutaArchivo);
            } catch (Exception e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
                return; // Salir si no se pudo cargar el archivo
            }

            // Crear el juego
            JuegoWordle juego = new JuegoWordle(palabras);

            // Seleccionar interfaz
            if (opcion == 1) {
                // Interfaz Consola
                InterfazJuego interfazConsola = new InterfazConsola(juego);
                interfazConsola.mostrarInstrucciones();
                interfazConsola.iniciar();
            } else if (opcion == 2) {
                // Interfaz Gráfica
                InterfazGrafica.main(new String[]{}); // Llama directamente al método estático main
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error en el programa: " + e.getMessage());
        }
    }
}

