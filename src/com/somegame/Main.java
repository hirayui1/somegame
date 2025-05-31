package com.somegame;

import com.somegame.core.TextUI;
import com.somegame.util.IOUtil;

public class Main {
    public static void main(String[] args) {
        TextUI ui = TextUI.getInstance();

        try {
            ui.run();
        } catch (RuntimeException e) {
            saveGameState();
            System.out.println("game ended abruptly, attempted to save last game state.");
        }
    }

    public static void saveGameState() {
        TextUI uiInstance = TextUI.getInstance();
        IOUtil.writeFileWithContents("C:\\Users\\berke\\IdeaProjects\\somegame\\gamesave" ,uiInstance.getPlayer().toString(), uiInstance.getPlayer().getName());
    }
}