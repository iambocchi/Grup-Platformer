package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    // MOUSE MOTION
    @Override
    public void mouseDragged(MouseEvent e) {

        System.out.println("I DRAG");
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        System.out.println("I MOVE");
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
