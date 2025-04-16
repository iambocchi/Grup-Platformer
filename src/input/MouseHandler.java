package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    private int xDelta = 0, yDelta = 0;

    public int getxDelta() {
        return xDelta;
    }

    public void setxDelta(int xDelta) {
        this.xDelta = xDelta;
    }

    public int getyDelta() {
        return yDelta;
    }

    public void setyDelta(int yDelta) {
        this.yDelta = yDelta;
    }

    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // MOUSE MOTION
    @Override
    public void mouseDragged(MouseEvent e) {

        System.out.println("I DRAG");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gamePanel.setRecPos(e.getX(), e.getY());
        System.out.println(e.getX() + " || " + e.getY());
    }

    // MOUSELISTENER
    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("I CLICKED");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        System.out.println("I RELEASH");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
