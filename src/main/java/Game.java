import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private int x = 10;
    private int y = 10;


    public Game(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
    }

    private void draw() throws IOException{
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }

    public void run(){
        try {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
