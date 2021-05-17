package game.entities;

import Code.SymbolTable;

import java.awt.*;


public class Wall extends Entity {
    int width = 20;
    int height = 20;
    Color color = Color.gray;
    public Wall(int x, int y, float dir_x, float dir_y, float velocity, float aceleration) {
        super(x, y, dir_x, dir_y, velocity, aceleration, true);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)(x-(width/2)),(int)(y-(width/2)), width,height);
    }

    @Override
    public void update(SymbolTable s) {

        SymbolTable st = s.createChild();
        st.topEntity(this);
        try {
            if(creation){
                creation = false;
                if(creationCode != null)creationCode.run(st);
            }
        }catch (Exception ex){
            creation = false;
            System.err.println("Error on creation trigger");
            //ex.printStackTrace();
        }

        try {
            if(destruction){
                destruction = false;
                if(destructionCode != null)destructionCode.run(st);
                st.game.entities.remove(this);
                return;
            }
        }catch (Exception ex){
            destruction = true;
            System.err.println("Error on destruction trigger");
            //ex.printStackTrace();
        }

        try {
            if(collision){
                collision = false;
                if(collisionCode != null)collisionCode.run(st);
            }
        }catch (Exception ex){
            System.err.println("Error on collision trigger");
            collision = false;
            //ex.printStackTrace();
        }
        try{
            if(iteracionCode != null)iteracionCode.run(st);
        }catch (Exception ex){
            System.err.println("Error on iteration trigger");
            //ex.printStackTrace();
        }
    }

    @Override
    public void move(int a, int b) {
        if(move){
            x+= dir_x*velocity;
            y+= dir_y*velocity;
            velocity += acceleration;
            collisionExternal(a,b);
            if(!move){
                x-= dir_x*velocity;
                y-= dir_y*velocity;
                velocity-= acceleration;
            }
        }
        if(velocity > 20)velocity= 20;
        move = true;
    }
}
