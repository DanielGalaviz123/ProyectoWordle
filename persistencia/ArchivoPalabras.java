package persistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Clase encargada de manejar las operaciones relacionadas con la carga de palabras desde un archivo.
 */
public class ArchivoPalabras {

    /**
     * Método que carga las palabras desde un archivo de texto.
     *
     * @param rutaArchivo La ruta del archivo que contiene las palabras.
     * @return Una lista de cadenas de texto donde cada elemento es una palabra leída del archivo.
     * @throws IOException Si ocurre un error al leer el archivo, se lanza una excepción.
     */
    public static List<String> cargarPalabras(String rutaArchivo) throws IOException {
        // Lee todas las líneas del archivo especificado y las devuelve como una lista de cadenas.
        return Files.readAllLines(Paths.get(rutaArchivo));
    }
}
