package com.LuisDu902.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;

import static com.googlecode.lanterna.input.KeyType.*;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    public Arena(int width,int height){
        this.width = width;
        this.height = height;
        hero = new Hero(10,10);
    }
    public void draw(Screen screen){
        screen.setCharacter(hero.getX(), hero.getY(), TextCharacter.fromCharacter('X')[0]);
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
