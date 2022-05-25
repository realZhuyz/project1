package view;

import javax.swing.*;
import java.awt.*;

public class Begin extends JFrame {
    JFrame begin = new JFrame();
    private static final String IMAGE_PATH = "./images/background.jpeg";
    private SetBackground setBackground;

    public Begin(){
        int width = 500;
        int height = 500;
        begin.setBounds(500,300,width,height);
        begin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground = new SetBackground(width,height,IMAGE_PATH);
        begin.setContentPane(setBackground);
        begin.setLayout(null);
        JLabel label = new JLabel("CHESS");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Times New Roman",Font.PLAIN,70));
        label.setBounds(145,50,500,60);
        JButton button1 = new JButton("Start");
        button1.setForeground(Color.BLACK);
        button1.setFont(new Font("Times New Roman",Font.PLAIN,40));
        button1.setBounds(180,150,150,60);
        button1.addActionListener((e) -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
        });
        JButton button2 = new JButton("Exit");
        button2.setForeground(Color.BLACK);
        button2.setFont(new Font("Times New Roman",Font.PLAIN,40));
        button2.setBounds(180,250,150,60);
        button1.addActionListener((e) -> begin.dispose());
        begin.getContentPane().add(label);
        begin.getContentPane().add(button2);
        begin.getContentPane().add(button1);
        begin.setVisible(true);
    }

}
