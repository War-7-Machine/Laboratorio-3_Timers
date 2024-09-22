import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab03 extends JFrame {

    public Lab03() {
        setTitle("Menú Principal");
        setSize(250, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Botón 1: Programa del Cronómetro con alarma
        JButton btnCronometro = new JButton("Cronómetro (alarma)");
        add(btnCronometro);

        // Botón 2: Programa de la animacion de la caminata
        JButton btnSecuenciaCaminata = new JButton("Secuencia caminata");
        add(btnSecuenciaCaminata);

        // Acción del botón 1: abrir el programa del cronómetro con alarma
        btnCronometro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CronometroGrafico cronometro = new CronometroGrafico();
                cronometro.setVisible(true);
            }
        });

        // Acción del botón 2: abrir el programa de la animacion de la caminata
        btnSecuenciaCaminata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecuenciaCaminata secuenciaCaminata = new SecuenciaCaminata();
                secuenciaCaminata.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Lab03().setVisible(true);
        });
    }
}
