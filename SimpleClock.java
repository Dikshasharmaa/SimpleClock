//package SimpleClock;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements Runnable{
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat timeFormat12 = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat timeFormat24 = new SimpleDateFormat("HH:mm:ss a");
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;

        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        TimeZone chosenTimeZone = TimeZone.getDefault();
        Boolean isGmt = false;
        TimeZone local = TimeZone.getDefault();
        TimeZone gmt = TimeZone.getTimeZone("GMT");

        String day;
        String date;
        Boolean is24Hours = false;
        Thread thread;

        JButton button;
        JButton button1;
        public void run() {
            while(thread.isAlive()){
                System.out.println("This code is running in a thread");
                timeFormat.setTimeZone(chosenTimeZone);
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                dayFormat.setTimeZone(chosenTimeZone);
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                dateFormat.setTimeZone(chosenTimeZone);
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);



                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }


        }

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(450, 350);

            button = new JButton("24/12 Hours Format");
            button.setForeground(Color.black);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    is24Hours = !is24Hours;
                    if (is24Hours){
                        timeFormat = timeFormat24;
                    } else {
                        timeFormat = timeFormat12;
                    }
                }

            });


            button1 = new JButton("Local/Gmt Time");
            button1.setForeground(Color.black);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("gmt button ");
                    isGmt = !isGmt;
                    if (isGmt){
                        chosenTimeZone = gmt;
                    } else {
                        chosenTimeZone = local;
                    }
                }

            });


            setVisible(true);

            Border blackLine = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black);
            this.getRootPane().setBorder(blackLine);
            this.setResizable(false);

    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,35));

            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));
    
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            add(button, BorderLayout.SOUTH);
            add(button1, BorderLayout.SOUTH);
            this.setVisible(true);   // Make the UI look better // Add button to switch between 12 & 24 hour format
    
            setTimer();
        }

        public void setTimer() {
            thread = new Thread(this);
            thread.start();
            // use of thread instead of infinite loop
            thread.run();
        }
        public static void main(String[] args) {
            SimpleClock sc = new SimpleClock();

            new SimpleClock();
        }
    }
