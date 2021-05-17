package Code;

import game.entities.Entity;
import game.entities.Fire;
import game.entities.Wall;

import java.util.LinkedList;

public class CodeSystem {

     public static void main(String[] args) {
        new CodeSystem.CodeNodeBool("true");
         System.out.println(new CodeNodeBool("true").run(null));
    }

    public static class CodeNodeBool extends Code.CodeNode{
        private boolean value;
        public CodeNodeBool(String value){
            this.value = value.equals("true");
        }
        @Override
        public Object run(Object o) {
            return value;
        }
    }

    public static class CodeNodeFloat extends Code.CodeNode{
        private float value;
        public CodeNodeFloat(String value){
            this.value = Float.parseFloat(value);
        }
        @Override
        public Object run(Object o) {
            return value;
        }
    }

    public static class CodeNodeInteger extends Code.CodeNode{
        private int value;
        public CodeNodeInteger(String value){
            this.value = Integer.parseInt(value);
        }
        @Override
        public Object run(Object o) {
            return value;
        }
    }

    public static class CodeNodeUnaOp extends Code.CodeNode{
        public CodeNodeUnaOp(java_cup.runtime.Symbol[] symbols,CodeNode c){
            this.symbols = symbols;
            this.params = new CodeNode[] {c};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object d = params[0].run(o);
            if(d.getClass() == Integer.class)return -(int)d;
            else if(d.getClass() == Float.class) return -(float)d;
            else{
                report_fatal_error("Invalid Negative Convertion",symbols[0]);
                return null;
            }

        }
    }

    public static class CodeNodeDivision extends Code.CodeNode{
        public CodeNodeDivision(java_cup.runtime.Symbol[] symbols,CodeNode m,CodeNode u){
            this.symbols = symbols;
            this.params = new CodeNode[] {m,u};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object m, u;
            m = params[0].run(o);
            u = params[1].run(o);
            if(m.getClass() == Integer.class){
                if(u.getClass() == Integer.class){
                    return (int)m / (int) u;
                }
                else if (u.getClass() == Float.class){
                    return (int)m / (float) u;
                }
                else{
                     report_fatal_error("Invalid division types",symbols[1]);
                }
            }
            else if(m.getClass() == Float.class){
                if(u.getClass() == Integer.class){
                    return (float)m / (int) u;
                }
                else if (u.getClass() == Float.class){
                    return (float)m / (float) u;
                }
                else{
                    report_fatal_error("Invalid division types",symbols[1]);
                }
            }
            else{
                report_fatal_error("Invalid division types",symbols[0]);
            }
            return null;

        }
    }

    public static class CodeNodeMultiplication extends Code.CodeNode{
        public CodeNodeMultiplication(java_cup.runtime.Symbol[] symbols,CodeNode m,CodeNode u){
            this.symbols = symbols;
            this.params = new CodeNode[] {m,u};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object m, u;
            m = params[0].run(o);
            u = params[1].run(o);
            if(m.getClass() == Integer.class){
                if(u.getClass() == Integer.class){
                    return (int)m * (int) u;
                }
                else if (u.getClass() == Float.class){
                    return (int)m * (float) u;
                }
                else{
                    report_fatal_error("Invalid multiplication types",symbols[1]);
                }
            }
            else if(m.getClass() == Float.class){
                if(u.getClass() == Integer.class){
                    return (float)m * (int) u;
                }
                else if (u.getClass() == Float.class){
                    return (float)m * (float) u;
                }
                else{
                    report_fatal_error("Invalid multiplication types",symbols[1]);
                }
            }
            else{
                report_fatal_error("Invalid multiplication types",symbols[0]);
            }
            return null;

        }
    }

    public static class CodeNodeSubtraction extends Code.CodeNode{
        public CodeNodeSubtraction(java_cup.runtime.Symbol[] symbols,CodeNode s,CodeNode m){
            this.symbols = symbols;
            this.params = new CodeNode[] {s,m};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object s, m;
            s = params[0].run(o);
            m = params[1].run(o);
            if(s.getClass() == Integer.class){
                if(m.getClass() == Integer.class){
                    return (int)s - (int) m;
                }
                else if (m.getClass() == Float.class){
                    return  (int)s - (float) m;
                }
                else{
                    report_fatal_error("Invalid subtraction types",symbols[1]);
                }
            }
            else if(s.getClass() == Float.class){
                if(m.getClass() == Integer.class){
                    return  (float)s - (int) m;
                }
                else if (m.getClass() == Float.class){
                    return  (float)s - (float) m;
                }
                else{
                    report_fatal_error("Invalid subtraction types",symbols[1]);
                }
            }
            else{
                report_fatal_error("Invalid subtraction types",symbols[1]);
            }
            return null;
        }
    }

    public static class CodeNodeSum extends Code.CodeNode{
        public CodeNodeSum(java_cup.runtime.Symbol[] symbols,CodeNode s,CodeNode m){
            this.symbols = symbols;
            this.params = new CodeNode[] {s,m};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object s, m;
            s = params[0].run(o);
            m = params[1].run(o);
            if(s.getClass() == Integer.class){
                if(m.getClass() == Integer.class){
                    return (int)s + (int) m;
                }
                else if (m.getClass() == Float.class){
                    return  (int)s + (float) m;
                }
                else{
                    report_fatal_error("Invalid sum types",symbols[1]);
                }
            }
            else if(s.getClass() == Float.class){
                if(m.getClass() == Integer.class){
                    return  (float)s + (int) m;
                }
                else if (m.getClass() == Float.class){
                    return  (float)s + (float) m;
                }
                else{
                    report_fatal_error("Invalid sum types",symbols[1]);
                }
            }
            else{
                report_fatal_error("Invalid sum types",symbols[0]);
            }
            return null;
        }
    }

    public static class CodeNodeGreaterEquals extends Code.CodeNode{
        public CodeNodeGreaterEquals(java_cup.runtime.Symbol[] symbols,CodeNode s1,CodeNode s2){
            this.symbols = symbols;
            this.params = new CodeNode[] {s1,s2};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object s1, s2;
            s1 = params[0].run(o);
            s2 = params[1].run(o);
            if(s1.getClass() == Integer.class){
                if(s2.getClass() == Integer.class){
                    return (int)s1 >= (int) s2;
                }
                else if (s2.getClass() == Float.class){
                    return (int)s1 >= (float) s2;
                }
                else{
                    report_fatal_error("Invalid Comparision types",symbols[1]);
                }
            }
            else if(s1.getClass() == Float.class){
                if(s2.getClass() == Integer.class){
                    return (float)s1 >= (int) s2;
                }
                else if (s2.getClass() == Float.class){
                    return (float)s1 >= (float) s2;
                }
                else{
                    report_fatal_error("Invalid Comparision types",symbols[1]);
                }
            }
            else{
                report_fatal_error("Invalid Comparision types",symbols[0]);
            }
            return null;
        }
    }

    public static class CodeNodeEquals extends Code.CodeNode{
        public CodeNodeEquals(java_cup.runtime.Symbol[] symbols,CodeNode s1,CodeNode s2){
            this.symbols = symbols;
            this.params = new CodeNode[] {s1,s2};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object s1, s2;
            s1 = params[0].run(o);
            s2 = params[1].run(o);
            if(s1.getClass() == Integer.class){
                if(s2.getClass() == Integer.class){
                    return (int)s1 == (int) s2;
                }
                else if (s2.getClass() == Float.class){
                    return (int)s1 == (float) s2;
                }
                else{
                    report_fatal_error("Invalid Comparision types",symbols[1]);
                }
            }
            else if(s1.getClass() == Float.class){
                if(s2.getClass() == Integer.class){
                    return (float)s1 == (int) s2;
                }
                else if (s2.getClass() == Float.class){
                    return (float)s1 == (float) s2;
                }
                else{
                    report_fatal_error("Invalid Comparision types",symbols[1]);
                }
            }
            else{
                report_fatal_error("Invalid Comparision types",symbols[0]);
            }
            return null;
        }
    }

    public static class CodeNodeNegation extends Code.CodeNode{
        public CodeNodeNegation(java_cup.runtime.Symbol[] symbols,CodeNode c){
            this.symbols = symbols;
            this.params = new CodeNode[] {c};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object c;
            c = params[0].run(o);
            if(c.getClass() == Boolean.class){
                return !(boolean)c;
            }
            else{
                report_fatal_error("Invalid division types",symbols[0]);
            }
            return null;
        }
    }

    public static class CodeNodeAND extends Code.CodeNode{
        public CodeNodeAND(java_cup.runtime.Symbol[] symbols,CodeNode a, CodeNode n){
            this.symbols = symbols;
            this.params = new CodeNode[] {a,n};
        }
        @Override
        public Object run(Object o) throws Exception{
            Object a,n;
            a = params[0].run(o);
            n = params[1].run(o);
            if(a.getClass() != Boolean.class){
                report_fatal_error("Invalid Logic operation types",symbols[0]);
            }
            else if(n.getClass() != Boolean.class){
                report_fatal_error("Invalid Logic operation types",symbols[1]);
            }
            else{
                return  (boolean)a && (boolean)n;
            }
            return null;
        }
    }

    public static class CodeNodeOR extends Code.CodeNode{
        public CodeNodeOR(java_cup.runtime.Symbol[] symbols,CodeNode o, CodeNode a){
            this.symbols = symbols;
            this.params = new CodeNode[] {o,a};
        }
        @Override
        public Object run(Object o) throws Exception {
            Object a,n;
            a = params[0].run(o);
            n = params[1].run(o);
            if(a.getClass() != Boolean.class){
                report_fatal_error("Invalid Logical operation types",symbols[0]);
            }
            else if(n.getClass() != Boolean.class){
                report_fatal_error("Invalid Logical operation types",symbols[1]);
            }
            else{
                return  (boolean)a || (boolean)n;
            }
            return null;
        }
    }

    public static class CodeNodeBlock extends Code.CodeNode{
         public CodeNodeBlock(CodeNode l, CodeNode b){
             this.params = new CodeNode[] {l,b};
         }
        @Override
        public Object run(Object o) throws Exception {
             params[0].run(o);
             params[1].run(o);
            return null;
        }
    }

    public static class CodeNodeEmpty extends Code.CodeNode{
        public CodeNodeEmpty(){
            this.params = null;
        }
        @Override
        public Object run(Object o) {
            return null;
        }
    }

    public static class CodeNodeDecls extends Code.CodeNode{
         String type;
         String ID;
         String paramReturn;
        public CodeNodeDecls(String type, String ID){
            this.type = type;
            this.ID = ID;
            this.params = null;
        }
        public CodeNodeDecls(String type, String ID, CodeNode a, CodeNode b, String paramReturn){
            this.type = type;
            this.ID = ID;
            this.paramReturn = paramReturn;
            this.params = new CodeNode[]{a,b};
        }
        @Override
        public Object run(Object o) throws Exception {
            if(o.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) o;
            if(type.equals("function")){
                s.put(ID,type, new Object[]{params[0],params[1],paramReturn});
                return null;
            }
            s.put(ID,type);
            return null;
        }
    }

    public static class CodeNodeAssignation extends Code.CodeNode{
        public CodeNodeAssignation(java_cup.runtime.Symbol[] symbols,CodeNode c, CodeNode o){
            this.symbols = symbols;
            this.params = new CodeNode[] {c,o};
        }
        @Override
        public Object run(Object obj) throws Exception {
            if(obj.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) obj;
            Object c,o;
            c = params[0].run(s);
            o = params[1].run(s);
            //The container has to be a list with if Symbol or Attribute (true, false) and Key
            if(c.getClass() != LinkedList.class)report_fatal_error("Error on type of container",null);
            LinkedList<Object> arr = (LinkedList<Object>) c;
            //If is a Symbol
            if(((boolean) arr.get(0))){
                rowTable r = s.get((String) arr.get(1));
                if(r == null)report_fatal_error("Assignation to no-registered identifier",symbols[0]);
                if(!Compare(r.type,o))report_fatal_error("Assignation of different type to variable",symbols[1]);
                s.put((String)arr.get(1),r.type,o);
            }
            //Else, its an attribute
            else{
                Object content = s.get_object((String) arr.get(1));
                if(content == null)report_fatal_error("Assignation to no-registered attribute",symbols[0]);
                if(content.getClass() == rowTable.class){
                    String type = ((rowTable) content).type;
                    if(!Compare(type,o))report_fatal_error("Assignation of different type to attribute",symbols[1]);
                    s.put_object((String)arr.get(1),type,o);
                }
                else{
                    if(content.getClass() != o.getClass())report_fatal_error("Assignation of different type to attribute",symbols[1]);
                    s.put_object((String) arr.get(1),null,o);
                }
            }
            return null;
        }
    }

    public static class CodeNodeIf extends Code.CodeNode{
        public CodeNodeIf(java_cup.runtime.Symbol[] symbols,CodeNode o, CodeNode b){
            this.symbols = symbols;
            this.params = new CodeNode[] {o,b};
        }
        @Override
        public Object run(Object obj) throws Exception {
            if(obj.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) obj;
            Object o = params[0].run(s);
            if(o.getClass() != Boolean.class)report_fatal_error("Invalid If condition",symbols[0]);
            else if((Boolean)o){
                //Creates a new SymbolTable that is a child of the SymbolTable get on run, and use it as table of the if statement
                params[1].run(s.createChild());
            }
            return null;
        }
    }

    public static class CodeNodeIfElse extends Code.CodeNode{
        public CodeNodeIfElse(java_cup.runtime.Symbol[] symbols,CodeNode o, CodeNode b1,CodeNode b2){
            this.symbols = symbols;
            this.params = new CodeNode[] {o,b1,b2};
        }
        @Override
        public Object run(Object obj) throws Exception {
            if(obj.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) obj;
            Object o = params[0].run(s);
            if(o.getClass() != Boolean.class)report_fatal_error("Invalid If condition",symbols[0]);
            else if((Boolean)o){
                //Creates a new SymbolTable that is a child of the SymbolTable get on run, and use it as table of the if statement
                params[1].run(s.createChild());
            }
            else{
                params[2].run(s.createChild());
            }
            return null;
        }
    }

    public static class CodeNodeContainer extends Code.CodeNode{
         String ID;
         boolean b;
        public CodeNodeContainer(boolean b, String ID){
            this.b = b;
            this.ID = ID;
        }
        @Override
        public Object run(Object obj) throws Exception {
            LinkedList<Object> list = new LinkedList<Object>();
            list.push(ID);
            list.push(b);
            return list;
        }
    }

    public static class CodeNodeCreateObject extends Code.CodeNode{
         String type;
        public CodeNodeCreateObject(String t, CodeNode o){
            this.type = t;
            this.params = new CodeNode[] {o};
        }
        @Override
        public Object run(Object obj) throws Exception {
            if(obj.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) obj;
            Entity e;
            if(type.equals("fuego"))e = new Fire(0,0,0,0,0,0);
            else e = new Wall(0,0,0,0,0,0);
            //Sets the new entity as the top entity to modify
            s.addEntity(e);
            params[0].run(s);
            //Removes the entity from the top, It still on the game entities list.
            s.popEntity();
            return null;
        }
    }

    public static class CodeNodeObjectBlock extends Code.CodeNode{
        public CodeNodeObjectBlock(CodeNode a, CodeNode v, CodeNode t){
            this.params = new CodeNode[] {a,v,t};
        }
        @Override
        public Object run(Object obj) throws Exception {
            if(obj.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) obj;
            params[0].run(s);
            params[1].run(s);
            params[2].run(s);
            return null;
        }
    }

    public static class CodeNodeObjectDecls extends Code.CodeNode{
        String type;
        String ID;
        public CodeNodeObjectDecls(String type, String ID){
            this.type = type;
            this.ID = ID;
        }
        @Override
        public Object run(Object o) throws Exception {
            if(o.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) o;
            if(s.entity == null)report_fatal_error("Attribute declaration with null object",null);
            s.put_object(ID,type);
            return null;
        }
    }

    public static class CodeNodeObjectTrigger extends Code.CodeNode{
        String type;
        public CodeNodeObjectTrigger(String type, CodeNode b){
            this.type = type;
            this.params = new CodeNode[]{b};
        }
        @Override
        public Object run(Object o) throws Exception {
            if(o.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) o;
            if(s.entity == null)report_fatal_error("Attribute declaration with null object",null);
            s.put_Trigger(type,params[0]);
            return null;
        }
    }

    public static class CodeNodeContainerConsult extends Code.CodeNode{
        public CodeNodeContainerConsult(java_cup.runtime.Symbol[] symbols,CodeNode c){
            this.symbols = symbols;
            this.params = new CodeNode[] {c};
        }
        @Override
        public Object run(Object obj) throws Exception {
            if(obj.getClass() != SymbolTable.class)report_fatal_error("Error on Symbol Table",null);
            SymbolTable s = (SymbolTable) obj;
            Object c;
            c = params[0].run(s);
            //The container has to be a list with if Symbol or Attribute (true, false) and Key
            if(c.getClass() != LinkedList.class)report_fatal_error("Error on type of container",null);
            LinkedList<Object> arr = (LinkedList<Object>) c;
            //If is a Symbol
            if(((boolean) arr.get(0))){
                rowTable r = s.get((String) arr.get(1));
                if(r == null)report_fatal_error("Consult on no-registered identifier",symbols[0]);
                if(r.value == null)report_fatal_error("Consult on no-assigned identifier",symbols[0]);
                return r.value;
            }
            //Else, its an attribute
            else{
                Object content = s.get_object((String) arr.get(1));
                if(content == null)report_fatal_error("Consult on no-registered attribute",symbols[0]);
                if(content.getClass() == rowTable.class){
                    if(((rowTable) content).value == null)report_fatal_error("Consult on no-assigned attribute",symbols[0]);
                    return ((rowTable) content).value;
                }
                else{
                    return content;
                }
            }
        }
    }



    public static void report_error(String message, Object info) {

        StringBuilder m = new StringBuilder("Error");

        if (info instanceof java_cup.runtime.Symbol) {

            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);

            if (s.left >= 0) {
                m.append(" in line "+(s.left+1));

                if (s.right >= 0)
                    m.append(", column "+(s.right+1));
            }
        }

        m.append(" : "+message);

        System.err.println(m);
    }

    public static void report_fatal_error(String message, Object info) throws Exception {
        report_error(message, info);
        throw new Exception();
    }

    private static boolean  Compare(String type, Object obj){
        switch (type){
            case "int":return obj.getClass() == Integer.class;
            case "float": return obj.getClass() == Float.class;
            case "bool": return obj.getClass() == Boolean.class;
            default:return false;
        }
    }

}
