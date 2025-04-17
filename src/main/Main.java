package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        ImageIcon img = new ImageIcon("GameIcon.jpg");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Grup");
        window.setIconImage(img.getImage());
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.windowFocusLost();
                System.out.println("THIS IS THE BYEBYE");
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

        });
        gamePanel.startGameThread();
    }
}
