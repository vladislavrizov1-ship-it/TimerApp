import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerApp extends JFrame {


    private Timer timer1;
    private Timer timer2;
    private Timer timer3;


    private int counter = 0;


    private JLabel counterLabel;
    private JTextArea logArea;

    public TimerApp() {


        setTitle("Планировщик таймеров");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        JButton startButton = new JButton("Запустить таймеры");
        JButton stopButton = new JButton("Остановить");


        counterLabel = new JLabel("Счётчик: 0");


        logArea = new JTextArea(15, 45);
        logArea.setEditable(false);


        add(startButton);
        add(stopButton);
        add(counterLabel);
        add(new JScrollPane(logArea));


        startButton.addActionListener(e -> startTimers());
        stopButton.addActionListener(e -> stopTimers());
    }


    private void startTimers() {


        stopTimers();

        counter = 0;
        counterLabel.setText("Счётчик: 0");

        logArea.append("=== Таймеры запущены ===\n");


        timer1 = new Timer();

        timer1.schedule(new TimerTask() {

            @Override
            public void run() {

                SwingUtilities.invokeLater(() ->
                        logArea.append(
                                "Таймер 1: прошло 5 секунд!\n"
                        )
                );


                cancel();
            }

        }, 5000);




        timer2 = new Timer();

        timer2.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                counter++;

                SwingUtilities.invokeLater(() -> {

                    counterLabel.setText(
                            "Счётчик: " + counter
                    );

                    logArea.append(
                            "Таймер 2: прошла "
                                    + counter
                                    + " сек.\n"
                    );
                });
            }

        }, 0, 1000);


        timer3 = new Timer();

        timer3.schedule(new TimerTask() {

            @Override
            public void run() {

                SwingUtilities.invokeLater(() -> {

                    logArea.append(
                            "Таймер 3: прошло 10 секунд.\n"
                    );

                    logArea.append(
                            "=== Все таймеры остановлены ===\n"
                    );

                    stopTimers();
                });
            }

        }, 10000);
    }



    private void stopTimers() {

        if (timer1 != null) {
            timer1.cancel();
            timer1 = null;
        }

        if (timer2 != null) {
            timer2.cancel();
            timer2 = null;
        }

        if (timer3 != null) {
            timer3.cancel();
            timer3 = null;
        }
    }


    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TimerApp app = new TimerApp();

            app.setVisible(true);
        });
    }
}

