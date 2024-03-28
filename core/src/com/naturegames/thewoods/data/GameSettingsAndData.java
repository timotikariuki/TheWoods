package com.naturegames.thewoods.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSettingsAndData {
    private static final String PREFERENCES_NAME = "game_settings_Acorn in the woods";
    private static final String KEY_MUSIC_ON = "music_on";
    private static final String KEY_TOP5_SCORE = "top5_score";

    private final static Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

    //music
    public static boolean getMusicOn() {
        return preferences.getBoolean(KEY_MUSIC_ON, true);
    }

    public static void setMusicOn(boolean music) {
        preferences.putBoolean(KEY_MUSIC_ON, music);
        preferences.flush();
    }

    //top5 score
    public static int getTop5Score(int id) {
        return preferences.getInteger(KEY_TOP5_SCORE + id, 0);
    }

    public static void setTop5Score(int id, int value) {
        preferences.putInteger(KEY_TOP5_SCORE + id, value);
        preferences.flush();
    }

    //clear
    public static void clear() {
        preferences.clear();
        preferences.flush();
    }
}

