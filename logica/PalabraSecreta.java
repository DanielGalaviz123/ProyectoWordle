package logica;

public class PalabraSecreta {
    private String palabra;

    // Constructor que inicializa la palabra secreta
    public PalabraSecreta(String palabra) {
        this.palabra = palabra;
    }

    // Metodo que verifica si la palabra ingresada por el usuario coincide con la palabra secreta
    public boolean esCoincidente(String palabraUsuario) {
        return palabra.equalsIgnoreCase(palabraUsuario);
    }

    // Metodo que compara la palabra ingresada por el usuario con la palabra secreta
    // Devuelve un string con las letras resaltadas segun el resultado:
    // - Verde: La letra esta en la posicion correcta
    // - Amarillo: La letra esta en la palabra pero en otra posicion
    // - Rojo: La letra no esta en la palabra
    public String compararPalabra(String palabraUsuario) {
        StringBuilder resultado = new StringBuilder();
        palabraUsuario = palabraUsuario.toUpperCase(); // Convertir la palabra del usuario a mayusculas

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabraUsuario.charAt(i); // Obtener la letra actual del usuario
            if (palabra.charAt(i) == letra) {
                // Letra correcta en la posicion correcta
                resultado.append("\033[32m").append(letra).append("\033[0m"); // Verde
            } else if (palabra.contains(String.valueOf(letra))) {
                // Letra correcta pero en posicion incorrecta
                resultado.append("\033[33m").append(letra).append("\033[0m"); // Amarillo
            } else {
                // Letra incorrecta
                resultado.append("\033[31m").append(letra).append("\033[0m"); // Rojo
            }
        }
        return resultado.toString();
    }

    // Getter para obtener la palabra secreta
    public String getPalabra() {
        return palabra;
    }
}

