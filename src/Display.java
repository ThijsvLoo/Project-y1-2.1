import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width,height;

    

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public Display(String title, int width, int height){
        this.height=height;
        this.title=title;
        this.width=width;

        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(screenSize.width,screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        canvas=new Canvas();
        canvas.setBackground(Color.BLUE);
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));



        frame.add(canvas);
        frame.pack();



    }
//GETTERS AND SETTERS
    public Canvas getCanvas() {
        return canvas;
    }

}
