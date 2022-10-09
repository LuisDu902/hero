package com.LuisDu902.hero;

public class Position {
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean equal(Position o){
        if(o == null) return false;

        return(this == o || (x == o.getX() && y == o.getY()));
    }
}
