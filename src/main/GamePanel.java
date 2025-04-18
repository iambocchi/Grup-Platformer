package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import input.KeyHandler;
import input.MouseHandler;
import levels.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    public final int ORIGINALTILESIZE = 16;
    final int SCALE = 3;
    public final int TILESIZE = ORIGINALTILESIZE * SCALE; // 32
    public final int MAXSCREENCOL = 26; // widht
    public final int MAXSCREENROW = 14; // hieght
    public final int SCREENWIDTH = TILESIZE * MAXSCREENCOL; // 1248
    public final int SCREENHEIGHT = TILESIZE * MAXSCREENROW; // 672

    // FPS
    int FPS = 120;

    // initialization
    private KeyHandler keyH = new KeyHandler();
    private MouseHandler mouseH = new MouseHandler(this);
    private Player player = new Player(this, keyH);
    private TileManager levelM = new TileManager(this);
    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT)); // 1248, 672
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
        levelM.update();
        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        levelM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}