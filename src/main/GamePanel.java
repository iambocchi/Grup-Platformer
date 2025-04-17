package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import input.KeyHandler;
import input.MouseHandler;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    public final int ORIGINALTILESIZE = 16;
    final int SCALE = 4;
    public final int TILESIZE = ORIGINALTILESIZE * SCALE;
    final int MAXSCREENCOL = 18;
    final int MAXSCREENROW = 10;
    public final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    public final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;

    // FPS
    int FPS = 120;

    // initialization
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler(this);
    Player player = new Player(this, keyH);
    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);

    }

    public void windowFocusLost() {
        player.resetMoveBoleans();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // starts the run
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int frames = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                frames++;
            }

            // FPS CHECK
            if (timer >= 1000000000) {
                System.out.println("FPS:" + frames);
                frames = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        // g2.drawImage(null, 100, 100, null);

        g2.dispose();
    }
}