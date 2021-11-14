import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.security.Key;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            arena = new Arena(20,20);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run(){
        try {
            draw();
            while(true){
                KeyStroke key = screen.readInput();
                if(key.getKeyType()==KeyType.EOF) break;
                if(key.getKeyType()==KeyType.Character && key.getCharacter()=='q'){
                    screen.close();
                    break;
                }
                processKey(key);
                arena.retrieveCoins();
                draw();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
