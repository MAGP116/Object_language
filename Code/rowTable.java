package Code;

public class rowTable {
    String type;
    Object value;
    public rowTable(String type, Object value){
        this.type = type;
        this.value = value;
    }

    public rowTable(String type){
        this(type,null);
    }

    @Override
    public String toString() {
        return "[type='" + type + '\'' + ", value=" + value + ']';
    }
}
