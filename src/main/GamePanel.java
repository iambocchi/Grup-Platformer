package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import entity.Player;
import input.KeyHandler;
import input.MouseHandler;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final static int ORIGINALTILESIZE = 16; // 16x16 tile
    final static int SCALE = 3;
    public static final int TILESIZE = ORIGINALTILESIZE * SCALE; // 48x48 tile
    final static int MAXSCREENCOL = 16;
    final int MAXSCREENROW = 12;
    public final static int SCREENWIDTH = TILESIZE * MAXSCREENCOL; // 768 pixel
    final int SCREENHEIGHT = TILESIZE * MAXSCREENROW; // 576 pixel

    // FPS
    int FPS = 60;

    // initialization
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler(this);
    Thread gameThread;
    Random random = new Random();
    Color recColor;

    // variables
    int xDelta = random.nextInt(SCREENWIDTH - TILESIZE), yDelta = random.nextInt(SCREENHEIGHT - TILESIZE);
    int xDir = 8, yDir = 8;
    // mouse position

    // initiate player
    Player player = new Player(keyH);

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);

    }

    // MOUSE INPUT IN AREA
    // public void setRecPos(int x, int y) {
    // mouseH.setxDelta(x);
    // mouseH.setyDelta(y);
    // repaint();
    // }

    public void bouncingRec() {
        xDelta += xDir;
        if (xDelta > SCREENWIDTH - TILESIZE || xDelta < 0) {
            xDir *= -1;
            recColor = getRndColor();
            update();
        }
        yDelta += yDir;
        if (yDelta > SCREENHEIGHT - TILESIZE || yDelta < 0) {
            yDir *= -1;
            recColor = getRndColor();
            update();
        }

    }

    public Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
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
        if (player != null) {
            player.update();
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (player != null) {
            player.draw(g2);
        }

        bouncingRec();
        g2.setColor(recColor);
        g2.fillRect(xDelta, yDelta, TILESIZE, TILESIZE);

        g2.dispose();
    }
}

// @Override
// GAME LOOP #1 METHOD
// public void run() {

// double drawInterval = 1000000000 / FPS; // 0.01666 seconds
// double nextDrawTime = System.nanoTime() + drawInterval;

// while (gameThread != null) {

// // System.out.println("this is running");

// // 1 UPDATE: update information such as character position
// update();
// // 2 DRAW: draw the screen with the updated information
// // the paintComponent method == repaint();
// repaint();
// // GAME LOOP #1
// // try {
// // double remainingTime = nextDrawTime - System.nanoTime();
// // remainingTime = remainingTime / 1000000;

// // if (remainingTime < 0) {
// // remainingTime = 0;
// // }
// // Thread.sleep((long) remainingTime);
// // nextDrawTime += drawInterval;

// // } catch (InterruptedException e) {

// // e.printStackTrace();
// // }
// }
