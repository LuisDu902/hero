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

    private Arena arena;

    public Game() throws IOException {
        try {
            arena = new Arena(20,20);
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
        arena.draw(screen);
        screen.refresh();
    }
    private void processKey(KeyStroke key) throws IOException{
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp -> arena.moveHero(arena.moveUp());
            case ArrowDown -> arena.moveHero(arena.moveDown());
            case ArrowLeft -> arena.moveHero(arena.moveLeft());
            case ArrowRight -> arena.moveHero(arena.moveRight());
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