package game;

import Code.SymbolTable;
import game.States.GameStateManager;
import game.util.MouseHandler;
import game.util.KeyHandler;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    public static int width, height;
    private Thread thread;

    private BufferedImage img;
    private Graphics2D g;

    private boolean running = false;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;
    private SymbolTable symbolTable;

    public GamePanel(int width, int height, SymbolTable symbolTable){
        this.symbolTable = symbolTable;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            this.thread = new Thread(this,"GameThread");
            thread.start();
        }
    }

    public void init(){
        running = true;

        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        mouse = new MouseHandler();
        key = new KeyHandler(this);

        gsm = new GameStateManager(symbolTable,width, height);
    }

    @Override
    public void run() {
        init();
        final double GAME_HERTZ = 60.0;
        final double TBU = 1_000_000_000 / GAME_HERTZ;//Time before update

        final int MUBR = 5; //MUST UPDATE BEFORE RENDER

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;
        final double TARGET_FPS = 60;
        final double TTBR = 1_000_000_000 / TARGET_FPS;//total time before render

        int frameCount = 0;
        int lastSecondTime = (int)(lastUpdateTime/1_000_000_000);
        int oldFrameCount = 0;

        double now;
        int updateCount;
        int thisSecond;
        while(running){

            now = System.nanoTime();
            updateCount = 0;
            while((now-lastUpdateTime)>TBU && (updateCount < MUBR) ){
                update();
                input(mouse,key);
                lastUpdateTime += TBU;
                updateCount++;
            }
            if(now - lastUpdateTime > TBU){
                lastUpdateTime = now - TBU;
            }
            input(mouse,key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;
            thisSecond = (int) (lastUpdateTime / 1_000_000_000);
            if(thisSecond > lastSecondTime){
                if(frameCount != oldFrameCount){
                    System.out.println("NEW SECOND "+thisSecond+" "+frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }
            while(now - lastRenderTime < TTBR && now -lastUpdateTime < TBU){
                Thread.yield();
                try{
                    Thread.sleep(1);
                }catch (Exception e){
                    System.out.println("ERROR: yieldinf thread");
                }
                now = System.nanoTime();
            }
        }
    }

    public void update(){
        gsm.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        gsm.input(mouse,key);
    }
    public void render(){
        if(g != null){
            g.setColor(Color.black);
            g.setTransform(new AffineTransform());
            g.fillRect(0,0,width,height);
            g.translate(width/2,height/2);
            gsm.render(g);
        }

    }
    public void draw(){
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        g2.drawImage(img,0,0,width,height,null);
    }
}
