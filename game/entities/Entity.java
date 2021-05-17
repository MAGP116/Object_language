package game.entities;

import Code.CodeNode;
import Code.SymbolTable;
import Code.rowTable;

import java.awt.*;
import java.util.HashMap;

public abstract class Entity {
    private int radius = 10;
    public float  x, y;
    private  float prev_x, prev_y;
    public float dir_x,dir_y;
    public float velocity;
    public float acceleration;
    private boolean solid;
    protected int collisionDelay = 0;
    boolean creation = true;
    boolean destruction = false;
    public boolean collision = false;
    public boolean move = true;
    public Entity parent = null;
    public HashMap<String, rowTable> table = new HashMap<>();
    public CodeNode creationCode = null, destructionCode = null, collisionCode = null, iteracionCode = null;

    public Entity(int x, int y, float dir_x, float dir_y, float velocity, float acceleration, boolean solid){
        this.x = x;
        this.y = y;
        this.prev_x = x;
        this.prev_y = y;
        this.dir_x = dir_x;
        this.dir_y = dir_y;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.solid = solid;
    }
    public abstract void draw(Graphics2D g);
    public abstract void update(SymbolTable s);
    public abstract void move(int width, int  height);
    public  void goBack(){
        x = prev_x;
        y = prev_y;
    }
    public void setBack(){
        prev_y = y;
        prev_x = x;
    }
    public void collision(Entity e){
        if(e.solid == this.solid && !e.solid)return;
        if(Math.sqrt(Math.pow(this.x - e.x,2)+Math.pow(this.y - e.y,2)) <= this.radius + e.radius){
            this.collision = true;
            //e.collision = true;
            //if(this.solid)e.move = false;
            if(e.solid)this.move = false;

        }
    };
    public void collisionExternal (int a, int b){
        int button = (b/2);
        int right = a/2;
        //If wall collision
        if(x < -right+radius || x > right-radius || y < -button+radius || y > button-radius){
            this.collision = true;
            this.move = false;
        }
        //If no wall collision
    }


    public void put(String key, rowTable row) {
        table.put(key, row);
    }
    public void put(String key, String type, Object value){
        table.put(key,new rowTable(type, value));
    }
    public void put(String key, String type){
        table.put(key,new rowTable(type));
    }
    public rowTable get(String key) {
        rowTable r = table.get(key);
        return r;
    }
}
