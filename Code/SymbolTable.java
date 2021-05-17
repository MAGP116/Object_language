package Code;

import game.entities.Entity;

import java.util.HashMap;

public class SymbolTable {
    public HashMap<String, rowTable> table = new HashMap<>();
    private SymbolTable parent = null;
    public Game game = null;
    Entity entity = null;

    public SymbolTable(){
        this.game = new Game();
    }
    public SymbolTable(SymbolTable p){
        this.game = p.game;
        this.parent = p;
        this.entity = p.entity;
    }

    public rowTable get(String key) {
        rowTable r = table.get(key);
        if(r == null && parent != null)return parent.get(key);
        return r;
    }

    public SymbolTable createChild(){
        return new SymbolTable(this);
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

    public void put_object(String key, String type, Object value){
        if(entity == null)return;
        switch (key){
            case "p$x":
                entity.x = (float)value;
                return;
            case "p$y":
                entity.y = (float)value;
                return;
            case "d$x":
                entity.dir_x = (float)value;
                return;
            case "d$y":
                entity.dir_y = (float)value;
                return;
            case "a$":
                entity.acceleration = (float)value;
                return;
            case "v$":
                entity.velocity = (float)value;
                return;
        }
        entity.put(key,type,value);
    }
    public void put_object(String key, String type){
        put_object(key,type,null);
    }
    public Object get_object(String key){
        if(entity == null)return null;
        switch (key){
            case "p$x":
                return entity.x;
            case "p$y":
                return entity.y;
            case "d$x":
                return entity.dir_x;
            case "d$y":
                return entity.dir_y;
            case "a$":
                return entity.acceleration;
            case "v$":
                return entity.velocity;
        }
        return  entity.get(key);
    }

    public void addEntity(Entity entity){
        game.entities.push(entity);
        topEntity(entity);
    }

    public void topEntity(Entity entity){
        entity.parent = this.entity;
        this.entity = entity;
    }

    public void put_Trigger(String key, CodeNode b){
        switch (key){
            case "creacion":
                entity.creationCode = b;
                return;
            case "destruccion":
                entity.destructionCode = b;
                return;
            case "colision":
                entity.collisionCode = b;
                return;
            case "iteracion":
                entity.iteracionCode = b;
        }
    }

    public void popEntity(){
        this.entity = this.entity.parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TABLE\n");
        sb.append(table);
        sb.append("\nGAME\n[");
        sb.append(game);
        sb.append(']');
        return sb.toString();
    }
}
