package com.LuisDu902.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.TerminalScreen;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game g1 = new Game();
        g1.run();
    }
}

