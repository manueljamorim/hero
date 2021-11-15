public class Monster extends Element{

    public Monster(int x, int y) {
        super(x, y);
        color = "#c02a2a";
        character = "M";
    }

    public Position move(Hero hero){
        Position difference = new Position(hero.getPosition().getX()-getPosition().getX(),hero.getPosition().getY()-getPosition().getY());
        Position end = getPosition();
        if(difference.getX()>0) end.setX(end.getX()+1);
        if(difference.getX()<0) end.setX(end.getX()-1);
        if(difference.getY()>0) end.setY(end.getY()+1);
        if(difference.getY()<0) end.setY(end.getY()-1);
        return end;
    }


}
