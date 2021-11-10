import com.googlecode.lanterna.TextCharacter;
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
    private Hero hero;
    private Arena arena;

    public Game(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            hero = new Hero(10,10);
            arena = new Arena(20,20);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveHero(Position position) {
        hero.setPosition(position);
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);

        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
        }
    }
    private void draw() throws IOException{
        screen.clear();
        hero.draw(screen);
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
                draw();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
