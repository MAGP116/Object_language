package Code;

public abstract class CodeNode {
    CodeNode [] params;
    java_cup.runtime.Symbol[] symbols;
    public abstract Object run(Object o) throws Exception;
}
