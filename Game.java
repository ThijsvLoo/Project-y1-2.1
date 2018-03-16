import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

public class Game implements Runnable {

    private Display display;

    private int width,height;
    private boolean running = false;
    public String title;

    private Thread thread;
    //Test
    Assets assets = new Assets();

    private BufferStrategy bs;
    private Graphics g;

    public Game(String title,int width,int height){
        this.title=title;
        this.width=width;
        this.height=height;
        display =new Display(title,width,height);
    }

    //The initialization
    private void init(){
        display = new Display(title,width,height);
        Assets.init();

    }

    private void tick(){

    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);     //Creating 3 buffers for our program
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0,width,height);                      //Clearing the screen before we start drawing
        //Draw Start

        g.drawImage(assets.grass,0,0,32,32,null);
        g.drawImage(assets.water,32,0,32,32,null);

        //Draw end
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        int fps=60;                                 //Setting fps limit
        double timePerTick=1000000000/fps;          //Maximum amount of time we have to execute the methods in order to achieve those 60 fps
        double delta=0;                             //Amount of time we have until we call the tick and render method
        long now;
        long lastTime=System.nanoTime();
        int ticks=0;
        long timer=0;

        while(running){                            //Game is running while "running" is True
            now=System.nanoTime();
            delta+=(now-lastTime)/timePerTick;
            timer+=now-lastTime;                   //The timer will count the amount of time that passed
            lastTime=now;

            if(delta>=1) {                         //That methos is just checking when to run the method tick and render so the
                tick();                            //program wont run those method over and over again , many times per second
                render();                          //and if it was called , delta resets back to <1
                delta--;
                ticks++;
            }

            if(timer>=1000000000) {
                System.out.println("Frames per second: " + ticks);     //Just printing the Fps in the console
                ticks = 0;
                timer = 0;
            }
        }
        stop();                                    //Calling the stop method after the loop just in case the game didn't close
    }

    //Start&Stop methods for the Thread
    public synchronized void start(){
        if(running){return;}                        //Checking if the game is already running
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop(){
        if(!running){return;}                       //Checking if the game is stopped
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
