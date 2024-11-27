package logica;

/**
 * Clase abstracta para definir la estructura de las interfaces de usuario en el juego Wordle.
 */
public abstract class InterfazJuego {
    protected JuegoWordle juego;

    /**
     * Constructor que inicializa la interfaz con una instancia de JuegoWordle.
     *
     * @param juego la instancia del juego que la interfaz manejará.
     */
    public InterfazJuego(JuegoWordle juego) {
        if (juego == null) {
            throw new IllegalArgumentException("El juego no puede ser nulo.");
        }
        this.juego = juego;
    }

    /**
     * Método abstracto para mostrar las instrucciones al usuario.
     */
    public abstract void mostrarInstrucciones();

    /**
     * Método abstracto para iniciar el flujo de interacción con el usuario.
     */
    public abstract void iniciar();
}