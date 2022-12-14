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

    private List<Wall> walls;

    private List<Coin> coins;

    private List<Monster> monsters;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        walls = createWalls();
        coins = createCoins();
        monsters = createmonsters();
        hero = new Hero(10,10);
    }
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);

        for (Wall wall : walls)
            wall.draw(graphics);

        for (Coin coin : coins)
            coin.draw(graphics);

        for (Monster monster : monsters)
            monster.draw(graphics);
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
        retrieveCoins();
    }
    private boolean canHeroMove(Position position){
        return (position.getX() >= 0 && position.getX() < width) &&
                (position.getY() >= 0 && position.getY() < height) &&
                (!walls.contains(new Wall(position.getX(), position.getY())));
    }
    public void moveMonsters(){
        for (Monster monster : monsters){
            monster.setPosition(monster.move(this));
        }
    }
    private void retrieveCoins(){
        for(Coin coin : coins){
            if(coin.getPosition().equal(hero.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }
    public Position moveUp(){
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() - 1);
    }
    public Position moveDown(){
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() + 1);
    }
    public Position moveLeft(){
        return new Position(hero.getPosition().getX() - 1, hero.getPosition().getY());
    }
    public Position moveRight(){
        return new Position(hero.getPosition().getX() + 1, hero.getPosition().getY());
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
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }
    private List<Monster> createmonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return monsters;
    }
    public boolean verifyMonsterCollisions(){
        for(Monster monster: monsters){
            if (monster.getPosition().equal(hero.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }
}
