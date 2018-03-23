import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements  MouseListener,MouseMotionListener{

    private boolean leftPressed,rightPressed;
    private boolean leftClick,rightClick;
    private int mouseX,mouseY;

    public MouseManager() {

    }

    //Getters

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isRightClick(){return rightClick;}

    public boolean isLeftClick(){return leftClick;}

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    //Implemented Methods

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            leftClick=true;
        }
        if(e.getButton()==MouseEvent.BUTTON3){
            rightClick=true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            leftPressed=true;
        }else if(e.getButton()==MouseEvent.BUTTON3) {
            rightPressed=true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            leftPressed=false;
            leftClick=false;
        }else if(e.getButton()==MouseEvent.BUTTON3) {
            rightPressed=false;
            rightClick=false;
        }

    }
}
