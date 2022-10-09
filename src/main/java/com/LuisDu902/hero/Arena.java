package com.LuisDu902.hero;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Arena {
    private int width;
    private int height;
    private Hero hero;

    public Arena(int width,int height){
        this.width = width;
        this.height = height;
        hero = new Hero(10,10);
    }
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    private boolean canHeroMove(Position position){
        return (position.getX() >= 0 && position.getX() < width) &&
                (position.getY() >= 0 && position.getY() < height);
    }

    public Position moveUp(){
        return new Position(hero.getX(), hero.getY() - 1);
    }
    public Position moveDown(){
        return new Position(hero.getX(), hero.getY() + 1);
    }
    public Position moveLeft(){
        return new Position(hero.getX() - 1, hero.getY());
    }
    public Position moveRight(){
        return new Position(hero.getX() + 1, hero.getY());
    }



}
