package week03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownTimer extends JFrame {
    Timer timer;
    JLabel timeLabel;
    JTextField timeInput;
    JButton startButton;
    int timeRemaining;

    public CountdownTimer() {
        setLayout(new FlowLayout());

        timeLabel = new JLabel("时间剩余: 00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        add(timeLabel);
        // 添加换行
        timeInput = new JTextField("1");
        timeInput.setFont(new Font("Arial", Font.BOLD, 30));
        add(timeInput);

        startButton = new JButton("Start Countdown");
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        add(startButton);

        event e = new event();
        startButton.addActionListener(e);
    }

    public class event implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int input = Integer.parseInt(timeInput.getText()) * 60;
            timeRemaining = input;
            timeInput.setEditable(false);
            startButton.setEnabled(false);

            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeRemaining--;
                    int minutes = timeRemaining / 60;
                    int seconds = timeRemaining % 60;
                    timeLabel.setText(String.format("时间剩余: %02d:%02d", minutes, seconds));
                    if (timeRemaining == 0) {
                        timer.stop();
                        timeInput.setEditable(true);
                        startButton.setEnabled(true);
                    }
                }
            });

            timer.start();
        }
    }

    public static void main(String[] args) {
        CountdownTimer gui = new CountdownTimer();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(650, 200);
        gui.setTitle("Countdown Timer");
        gui.setVisible(true);
    }
}