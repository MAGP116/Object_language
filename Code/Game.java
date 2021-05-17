package Code;

import game.entities.Entity;
import game.entities.Player;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Game {
    public LinkedList<Entity> entities = new LinkedList<>();
    public Player player;
    public Game(){
        player = new Player(0,0);
    }

    public void update(SymbolTable symbolTable, int width, int height){
        player.update(symbolTable);
        Entity[] es = new Entity[entities.size()];
        es = entities.toArray(es);
        //Evaluates the player movement
        player.move(width,height);
        if(player.move){
            for (Entity e : es){
                player.collision(e);
                if(!player.move){
                    break;
                }
            }
        }
        if(player.move)player.setBack();
        else player.goBack();

        //Evaluates the object movement
        for(int i = 0; i<es.length; i++){
            es[i].move(width,height);
            if(es[i].move){
                for(int j = 0; j<es.length; j++){
                    if(i == j)continue;
                    es[i].collision(es[j]);
                    if(!es[i].move){
                        break;
                    }
                }
                es[i].collision(player);
            }
            if(es[i].move)es[i].setBack();
            else es[i].goBack();
        }
        for(Entity e : es){
            e.update(symbolTable);
        }
    }

    public void draw(Graphics2D g){
        player.draw(g);
        for (Entity e:entities) {
            e.draw(g);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entities\n");
        sb.append(entities.toString());
        sb.append("\nPlayer\n");
        sb.append(player);
        return sb.toString();
    }
}
