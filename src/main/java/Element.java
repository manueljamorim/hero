import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    private Position position;
    public String color;
    public String character;


    public Element(int x, int y){
        position = new Position(x,y);
    }

    public void draw(TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString(color));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(position.getX(), position.getY()), character);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveRight(){
        return new Position(position.getX()+1, position.getY());
    }

    public Position moveLeft(){
        return new Position(position.getX()-1, position.getY());
    }

}
