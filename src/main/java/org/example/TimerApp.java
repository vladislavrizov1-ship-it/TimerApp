import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerApp extends JFrame {

    // Три таймера
    private Timer timer1;
    private Timer timer2;
    private Timer timer3;

    // Счётчик секунд
    private int counter = 0;

    // Элементы интерфейса
    private JLabel counterLabel;
    private JTextArea logArea;

    public TimerApp() {

        // Настройка окна
        setTitle("Планировщик таймеров");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Кнопки
        JButton startButton = new JButton("Запустить таймеры");
        JButton stopButton = new JButton("Остановить");

        // Счётчик
        counterLabel = new JLabel("Счётчик: 0");

        // Область вывода событий
        logArea = new JTextArea(15, 45);
        logArea.setEditable(false);

        // Добавляем элементы в окно
        add(startButton);
        add(stopButton);
        add(counterLabel);
        add(new JScrollPane(logArea));

        // Обработчики кнопок
        startButton.addActionListener(e -> startTimers());
        stopButton.addActionListener(e -> stopTimers());
    }

    // Запуск всех таймеров
    private void startTimers() {

        // Если таймеры уже работают, сначала остановим их
        stopTimers();

        counter = 0;
        counterLabel.setText("Счётчик: 0");

        logArea.append("=== Таймеры запущены ===\n");

        // =====================================================
        // ТАЙМЕР 1
        // Однократное действие через 5 секунд
        // Используется schedule()
        // =====================================================

        timer1 = new Timer();

        timer1.schedule(new TimerTask() {

            @Override
            public void run() {

                SwingUtilities.invokeLater(() ->
                        logArea.append(
                                "Таймер 1: прошло 5 секунд!\n"
                        )
                );

                // После выполнения таймер больше не нужен
                cancel();
            }

        }, 5000);


        // =====================================================
        // ТАЙМЕР 2
        // Выполняет действие каждую секунду
        // Используется scheduleAtFixedRate()
        // =====================================================

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


        // =====================================================
        // ТАЙМЕР 3
        // Через 10 секунд останавливает работу таймеров
        // =====================================================

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


    // Остановка всех таймеров
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


    // Запуск программы
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TimerApp app = new TimerApp();

            app.setVisible(true);
        });
    }
}

