package com.LuisDu902.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Hero hero;

    public Game() throws IOException {
        try {
            hero = new Hero(10,10);
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    private void processKey(KeyStroke key) throws IOException{
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp -> hero.moveUp();
            case ArrowDown -> hero.moveDown();
            case ArrowLeft -> hero.moveLeft();
            case ArrowRight -> hero.moveRight();
            case Character -> {
                if (key.getCharacter() == 'q') screen.close();
            }
        }
        }

    public void run() throws IOException {
        try {
            while (true) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.EOF) break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}