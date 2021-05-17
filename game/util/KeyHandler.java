package game.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import game.GamePanel;


public class KeyHandler implements KeyListener {

    public static ArrayList<Key>keys = new ArrayList<>();

    public class Key{
        public int presses, absorbs;
        public boolean down, clicked;

        public Key(){
            keys.add(this);
        }

        public void toggle(boolean pressed){
            if(pressed != down){
                down = pressed;
            }
            if(pressed){
                presses++;
            }

        }

        public void tick(){
            if(absorbs < presses){
                absorbs++;
                clicked = true;
            }
            else{

                clicked = false;
            }
        }
    }
    public Key up = new Key();
    public Key down = new Key();
    public Key right = new Key();
    public Key left  = new Key();
    public Key attack = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape  = new Key();


    public KeyHandler(GamePanel game){
        game.addKeyListener(this);
    }

    public void releaseAll(){
        for(int i = 0; i<keys.size();i++){
            keys.get(i).down = false;
        }
    }

    public void tick(){
        for(int i = 0; i<keys.size();i++){
            keys.get(i).tick();
        }
    }

    public void toggle(KeyEvent e,boolean pressed){
        switch (e.getKeyCode()){
            case  KeyEvent.VK_W:
                up.toggle(pressed);
                break;
            case KeyEvent.VK_S:
                down.toggle(pressed);
                break;
            case KeyEvent.VK_D:
                right.toggle(pressed);
                break;
            case KeyEvent.VK_A:
                left.toggle(pressed);
                break;
            case KeyEvent.VK_Z:
                attack.toggle(pressed);
                break;
            case KeyEvent.VK_E:
                menu.toggle(pressed);
                break;
            case KeyEvent.VK_ENTER:
                enter.toggle(pressed);
                break;
            case KeyEvent.VK_ESCAPE:
                escape.toggle(pressed);
                break;

        }

        /*
        if(e.getKeyCode() == KeyEvent.VK_W)up.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W)down.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W)right.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W)left.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W)attack.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W)menu.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W)enter.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W)escape.toggle(pressed);

         */


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e,true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e,false);
    }
}