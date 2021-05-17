package game.States;

import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.Graphics2D;

public abstract class GameState {
    private GameStateManager gsm;
    protected int width, height;
    public GameState(GameStateManager gsm,int width, int height){
        this.width = width;
        this.height = height;
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
