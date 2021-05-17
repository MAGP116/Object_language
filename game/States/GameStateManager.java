package game.States;

import Code.SymbolTable;
import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;
    public SymbolTable symbolTable;
    int width, height;
    public GameStateManager(SymbolTable symbolTable, int width, int height){
        this.width = width;
        this.height = height;
        this.symbolTable = symbolTable;
        states = new ArrayList<>();
        states.add(new PlayState(this,width,height));
    }

    public void update(){
        for(int i = 0; i< states.size();i++){
            states.get(i).update();
        }
    }
    public void input(MouseHandler mouse, KeyHandler key){
        for(int i = 0; i< states.size();i++){
            states.get(i).input(mouse,key);
        }
    }

    public void render(Graphics2D g){
        for(int i = 0; i< states.size();i++){
            states.get(i).render(g);
        }
    }

}
