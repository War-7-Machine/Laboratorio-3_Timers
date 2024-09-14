//Cronometro con timers

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CronometroGrafico extends JFrame {
    private JLabel lblHora;
    private Timer timer;
    private TimerTask cronometroTask;
    private TimerTask alarmaTask;
    private long alarmaDelay;
    private long alarmaInterval;
    private int numeroIntervalos;
    private int contadorIntervalos;
   
    public CronometroGrafico() {
        setTitle("Cronómetro con Alarma");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
       
        lblHora = new JLabel();
        lblHora.setFont(new Font("Arial", Font.BOLD, 30));
        add(lblHora);
       
        JButton btnConfigurar = new JButton("Configurar Alarma");
        add(btnConfigurar);
       
        // Campos para la configuración de la alarma
        JTextField txtDelay = new JTextField(5);
        JTextField txtIntervalo = new JTextField(5);
        JTextField txtNumeroIntervalos = new JTextField(5);
        add(new JLabel("Tiempo de activación de la alarma (s):"));
        add(txtDelay);
        add(new JLabel("Tiempo entre repeticiones de la alarma (s):"));
        add(txtIntervalo);
        add(new JLabel("Número de repeticiones de la alarma:"));
        add(txtNumeroIntervalos);
       
        btnConfigurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long delay = Long.parseLong(txtDelay.getText()) * 1000;
                    long intervalo = Long.parseLong(txtIntervalo.getText()) * 1000;
                    int numIntervalos = Integer.parseInt(txtNumeroIntervalos.getText());
                    configurarAlarma(delay, intervalo, numIntervalos);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores válidos para la alarma.");
                }
            }
        });
       
        iniciarCronometro();
    }
   
    private void iniciarCronometro() {
        timer = new Timer();
        cronometroTask = new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        String horaActual = sdf.format(new Date());
                        lblHora.setText(horaActual);
                    }
                });
            }
        };
       
        // Ejecutar la tarea cada segundo
        timer.scheduleAtFixedRate(cronometroTask, 0, 1000);
    }
   
    private void configurarAlarma(long delay, long interval, int numIntervalos) {
        alarmaDelay = delay;
        alarmaInterval = interval;
        numeroIntervalos = numIntervalos;
        contadorIntervalos = 0;
       
        if (alarmaTask != null) {
            alarmaTask.cancel();
        }
       
        alarmaTask = new TimerTask() {
            @Override
            public void run() {
                contadorIntervalos++;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (contadorIntervalos <= numeroIntervalos) {
                            JOptionPane.showMessageDialog(null, "¡Alarma! Han pasado " + (alarmaDelay / 1000) + " segundos. Alarma número: " + contadorIntervalos);
                        }
                        if (contadorIntervalos >= numeroIntervalos) {
                            alarmaTask.cancel();
                        }
                    }
                });
            }
        };
       
        // Programar la alarma con la cantidad de intervalos deseados
        timer.scheduleAtFixedRate(alarmaTask, alarmaDelay, alarmaInterval);
    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CronometroGrafico().setVisible(true);
            }
        });
    }
}

