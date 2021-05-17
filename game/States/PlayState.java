package game.States;

import Code.SymbolTable;
import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;

public class PlayState extends GameState{
    SymbolTable symbolTable = null;

    public PlayState(GameStateManager gsm, int width, int height){
        super(gsm, width, height);
        this.symbolTable = gsm.symbolTable;
    }

    @Override
    public void update() {
        symbolTable.game.update(symbolTable, width, height);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        symbolTable.game.player.input(key);
    }

    @Override
    public void render(Graphics2D g) {
        symbolTable.game.draw(g);

    }
}
