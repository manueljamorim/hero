import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width, height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;


    public Arena(int width, int height){
        this.height = height;
        this.width =width;
        hero = new Hero(10,10);
        walls = createWalls();
        coins = createCoins();

    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;

    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        Coin new_coin = null;
        boolean add_coin = true;
        while(coins.size()<5){
            new_coin = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);

            //Avoid coins over hero
            if(new_coin.getPosition().equals(hero.getPosition())) add_coin=false;

            //Avoid coins over coins
            for (Coin coin: coins){
                if(new_coin.getPosition().equals(coin.getPosition())){
                    add_coin = false;
                    break;
                }
            }
            if(add_coin) coins.add(new_coin);

        }
        return coins;
    }


    private boolean canHeroMove(Position position){
        if(position.getX()>=1 && position.getY()>=1 && position.getX()<width-1 && position.getY()<height-1) return true;
        else return false;
    }

    public void retrieveCoins(){
        for (int j = 0; j<coins.size(); j++){
            if(hero.getPosition().equals(coins.get(j).getPosition())){
                coins.remove(j);
                break;
            }
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) hero.setPosition(position);
    }

    public void processKey(KeyStroke key) {
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

    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#447b57"));
        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(screen);
        for (Coin coin : coins)
            coin.draw(screen);
        hero.draw(screen);
    }




}
