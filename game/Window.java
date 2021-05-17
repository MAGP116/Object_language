package game;

import Code.SymbolTable;

import javax.swing.JFrame;

public class Window extends JFrame{

    public Window(SymbolTable symbolTable){
        setTitle("Your Title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(new GamePanel(480,320, symbolTable));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
