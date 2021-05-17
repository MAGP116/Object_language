package game.entities;

import java.awt.Graphics2D;
import java.awt.Color;

import Code.SymbolTable;
import game.util.KeyHandler;

public class Player extends Entity {
    int width = 20;
    int height = 20;
    Color color = Color.white;
    boolean up,down,right,left;
    public Player(int x, int y){
        super( x,  y,  0,  0,  2,  0,true);

    }
    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)(x-(width/2)),(int)(y-(width/2)), width,height);
    }

    @Override
    public void update(SymbolTable s) {
        collision = false;
        move = true;
    }

    @Override
    public void move(int width, int height) {
        if(move){
            x+= dir_x*velocity;
            y+= dir_y*velocity;
            collisionExternal(width,height);
            if(!move)goBack();
        }
    }


    public void input(KeyHandler key){
        if(key.up.down){
            if(!up){
                up = true;
                dir_y--;
            }
        }
        else if(up){
            up = false;
            dir_y ++;
        }
        if(key.down.down){
            if(!down){
                down = true;
                dir_y++;
            }
        }
        else if(down){
            down = false;
            dir_y --;
        }
        if(key.right.down){
            if(!right){
                right = true;
                dir_x++;
            }
        }
        else if(right){
            right = false;
            dir_x --;
        }
        if(key.left.down) {
            if (!left) {
                left = true;
                dir_x--;
            }
        }
        else if(left){
            left = false;
            dir_x ++;
        }

    }

}
