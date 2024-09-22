// Secuencia de imagenes de caminata con control de velocidad deslizante 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class SecuenciaCaminata extends JFrame {
    private JLabel lblImagen;
    private ImageIcon[] imagenes; // Arreglo de imágenes 
    private Timer timer;
    private int indiceImagen = 0; // Ubicación de la imagen actual
    private int velocidadAnimacion = 900; // Velocidad inicial de la animación 
    private JSlider sliderVelocidad;

    public SecuenciaCaminata () {
        setTitle("Animación de Caminata");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Imágenes en el arreglo
        imagenes = new ImageIcon[8];
        for (int i = 0; i < imagenes.length; i++) {
            imagenes[i] = new ImageIcon("imagen" + (i + 1) + ".png"); // Ubicación de las imagenes de la secuencia 
        }

        lblImagen = new JLabel(imagenes[0]); // Primera imagen
        add(lblImagen, BorderLayout.CENTER);

        // Control deslizante de velocidad 
        sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 100, 1000, velocidadAnimacion);
        sliderVelocidad.setMajorTickSpacing(200);
        sliderVelocidad.setPaintTicks(true);
        sliderVelocidad.setPaintLabels(true);
        add(sliderVelocidad, BorderLayout.SOUTH);

        sliderVelocidad.addChangeListener(e -> {
            velocidadAnimacion = sliderVelocidad.getValue(); // Actualizar la velocidad
            reiniciarTimer(); // Reiniciar el temporizador con la nueva velocidad
        });

        iniciarAnimacion();
    }

    // Iniciar el temporizador para la animación
    private void iniciarAnimacion() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    lblImagen.setIcon(imagenes[indiceImagen]);
                    indiceImagen = (indiceImagen + 1) % imagenes.length; // Ciclar a través de las imágenes
                });
            }
        }, 0, velocidadAnimacion);
    }

    // Reiniciar el temporizador con la nueva velocidad
    private void reiniciarTimer() {
        timer.cancel();
        iniciarAnimacion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SecuenciaCaminata().setVisible(true);
        });
    }
}
