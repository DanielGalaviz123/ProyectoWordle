package igu;

import logica.JuegoWordle;
import persistencia.ArchivoPalabras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfazGrafica {

    private static JuegoWordle juego; // Controlador principal del juego
    private static JTextField[][] gridFields; // Cuadrícula de texto para los intentos (6x5)
    private static Map<String, JButton> tecladoBotones; // Mapa para asociar las teclas con los botones del teclado virtual
    private static int intentoActual = 0; // Número del intento actual

    public static void main(String[] args) {
        try {
            // Cargar palabras desde un archivo de texto
            String rutaArchivo = "C:\\Users\\danie\\OneDrive\\Escritorio\\palabras.txt";
            List<String> palabras = ArchivoPalabras.cargarPalabras(rutaArchivo); // Método para cargar las palabras desde el archivo
            juego = new JuegoWordle(palabras); // Inicializar el juego con las palabras cargadas
        } catch (Exception e) {
            // Si ocurre un error al cargar las palabras, mostrar un mensaje y salir
            JOptionPane.showMessageDialog(null, "Error al cargar las palabras: " + e.getMessage());
            e.printStackTrace();
            return; // Salir si ocurre un error crítico
        }

        // Crear y configurar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Wordle - Interfaz Gráfica"); // Crear ventana principal
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar el cierre de la ventana
            frame.setSize(400, 500); // Establecer tamaño de la ventana
            frame.setLayout(new BorderLayout()); // Establecer el layout principal (border)

            // Panel de la cuadrícula (6 filas x 5 columnas)
            JPanel gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(6, 5, 5, 5)); // Establecer el layout con 6 filas y 5 columnas
            gridFields = new JTextField[6][5]; // Inicializar la matriz de campos de texto
            for (int i = 0; i < 30; i++) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER); // Alinear el texto al centro
                textField.setEditable(false); // No permitir editar el campo directamente
                gridFields[i / 5][i % 5] = textField; // Asignar el JTextField en la posición correcta de la matriz
                gridPanel.add(textField); // Añadir el campo de texto al panel
            }

            // Panel del teclado (con botones para cada letra y acciones)
            JPanel keyboardPanel = new JPanel();
            keyboardPanel.setLayout(new GridLayout(3, 10, 5, 5)); // Establecer el layout con 3 filas y 10 columnas
            String[] keys = {
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ",
                "Z", "X", "C", "V", "B", "N", "M", "✔", "␡"
            };

            tecladoBotones = new HashMap<>(); // Inicializar el mapa para los botones del teclado
            for (String key : keys) {
                JButton button = new JButton(key); // Crear un botón por cada tecla
                button.addActionListener(new KeyListener()); // Asociar el evento al botón
                tecladoBotones.put(key, button); // Asociar la tecla con el botón en el mapa
                keyboardPanel.add(button); // Añadir el botón al panel del teclado
            }

            // Añadir los paneles de la cuadrícula y el teclado a la ventana principal
            frame.add(gridPanel, BorderLayout.CENTER); // Añadir la cuadrícula en el centro
            frame.add(keyboardPanel, BorderLayout.SOUTH); // Añadir el teclado en la parte inferior

            // Hacer visible la ventana
            frame.setVisible(true);
        });
    }

    // Clase interna que maneja los eventos de los botones del teclado
    private static class KeyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource(); // Obtener el botón que fue presionado
            String key = button.getText(); // Obtener el texto del botón presionado

            // Si se presiona el botón "✔", validar el intento actual
            if (key.equals("✔")) {
                StringBuilder intento = new StringBuilder();
                // Recoger las letras ingresadas en el intento actual
                for (int i = 0; i < 5; i++) {
                    intento.append(gridFields[intentoActual][i].getText());
                }

                // Verificar el intento y obtener el resultado
                String resultado = juego.verificarIntento(intento.toString());
                actualizarCuadricula(resultado); // Actualizar la cuadrícula con los resultados
                actualizarTeclado(intento.toString(), resultado); // Actualizar el teclado con los colores de las letras

                intentoActual++; // Incrementar el intento actual
                if (juego.esJuegoTerminado() || intentoActual >= 6) {
                    // Si el juego ha terminado o se agotaron los intentos, mostrar mensaje
                    JOptionPane.showMessageDialog(null, "¡Juego terminado!");
                }
            } else if (key.equals("␡")) {
                // Si se presiona el botón "␡", eliminar la última letra del intento
                for (int i = 4; i >= 0; i--) {
                    if (!gridFields[intentoActual][i].getText().isEmpty()) {
                        gridFields[intentoActual][i].setText(""); // Eliminar el texto
                        break;
                    }
                }
            } else {
                // Si se presiona una letra, agregarla al primer campo vacío en el intento actual
                for (int i = 0; i < 5; i++) {
                    if (gridFields[intentoActual][i].getText().isEmpty()) {
                        gridFields[intentoActual][i].setText(key); // Agregar la letra
                        break;
                    }
                }
            }
        }
    }

    // Método para actualizar la cuadrícula con los colores del resultado del intento
    private static void actualizarCuadricula(String resultado) {
        String[] colores = resultado.split(" "); // Separar los colores por espacio
        for (int i = 0; i < 5; i++) {
            JTextField field = gridFields[intentoActual][i]; // Obtener el campo de texto correspondiente
            switch (colores[i]) {
                case "verde":
                    field.setBackground(Color.GREEN); // Letra correcta y en la posición correcta
                    break;
                case "amarillo":
                    field.setBackground(Color.YELLOW); // Letra correcta pero en la posición incorrecta
                    break;
                case "rojo":
                    field.setBackground(Color.RED); // Letra incorrecta
                    break;
                default:
                    field.setBackground(Color.WHITE); // Caso por defecto (sin color)
                    break;
            }
        }
    }

    // Método para actualizar los colores de los botones del teclado
    private static void actualizarTeclado(String intento, String resultado) {
        String[] colores = resultado.split(" "); // Separar los colores del resultado
        for (int i = 0; i < intento.length(); i++) {
            String letra = String.valueOf(intento.charAt(i)); // Obtener la letra del intento
            JButton boton = tecladoBotones.get(letra.toUpperCase()); // Buscar el botón correspondiente
            if (boton != null) {
                switch (colores[i]) {
                    case "verde":
                        boton.setBackground(Color.GREEN); // Letra correcta
                        break;
                    case "amarillo":
                        boton.setBackground(Color.YELLOW); // Letra en la posición incorrecta
                        break;
                    case "rojo":
                        // Si la letra ya es verde o amarilla, no cambiarla a roja
                        if (boton.getBackground() != Color.GREEN && boton.getBackground() != Color.YELLOW) {
                            boton.setBackground(Color.RED); // Letra incorrecta
                        }
                        break;
                }
            }
        }
    }
}





