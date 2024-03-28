package com.naturegames.thewoods.tools;

public class GameState {

    public static final int MENU = 1;
    public static final int WAITING = 0;
    public static final int GAME = 1;

    private static int currentState;

    public static void setState(int newState) {
        currentState = newState;
    }

    public static int getState() {
        return currentState;
    }
}
