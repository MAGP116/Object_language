



package parser;
import Code.SymbolTable;
import game.GameLauncher;
import java_cup.runtime.Symbol;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    static public void main(String argv[]) {
        long start = System.currentTimeMillis();
        long end;
        SymbolTable symbolTable = null;
        try {

            FileReader fr = new FileReader("input.txt");
            parser p = new parser(new Lexer(fr));
            Symbol result = p.parse();
            //System.out.println(((SymbolTable)result.value));
            symbolTable = (SymbolTable) result.value;
            end = System.currentTimeMillis();


        } catch (Exception e) {
            /* do cleanup here -- possibly rethrow e */
            //e.printStackTrace();
            end = System.currentTimeMillis();

        }
        System.out.println("Time needed to parser: "+(end-start)+" millisecond");
        if(symbolTable != null){
            new GameLauncher(symbolTable);
        }

        //System.out.println(end-start);
    }
}