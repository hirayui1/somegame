package com.somegame.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public final class IOUtil {
    private IOUtil() {}

    public static void writeFileWithContents(String filePath, String contents, String fileName) {
        fileName = "/gameSave - " + fileName + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath + fileName))) {
            bw.write(contents);
        } catch (IOException e) {
            System.out.println("Failed to write gamestate.");
            e.printStackTrace();
        }// continue by creating a GameState object that holds the objects that determine the game state
    }
}