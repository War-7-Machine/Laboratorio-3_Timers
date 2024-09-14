import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CronometroGrafico1 extends JFrame {
    private JLabel lblHora;
    private Timer timer;
    private TimerTask cronometroTask;
    private TimerTask alarmaTask;
    private long alarmaDelay;
    private long alarmaInterval;
    
    public CronometroGrafico1() {
        setTitle("Cronómetro con Alarma");
        setSize(300, 200);
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
        add(new JLabel("Retraso (s):"));
        add(txtDelay);
        add(new JLabel("Intervalo (s):"));
        add(txtIntervalo);
        
        btnConfigurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long delay = Long.parseLong(txtDelay.getText()) * 1000;
                    long intervalo = Long.parseLong(txtIntervalo.getText()) * 1000;
                    configurarAlarma(delay, intervalo);
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
    
    private void configurarAlarma(long delay, long interval) {
        alarmaDelay = delay;
        alarmaInterval = interval;
        
        if (alarmaTask != null) {
            alarmaTask.cancel();
        }
        
        alarmaTask = new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(null, "¡Alarma! Han pasado " + (alarmaDelay / 1000) + " segundos.");
                    }
                });
            }
        };
        
        // Programar la alarma
        timer.scheduleAtFixedRate(alarmaTask, alarmaDelay, alarmaInterval);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CronometroGrafico1().setVisible(true);
            }
        });
    }
}
