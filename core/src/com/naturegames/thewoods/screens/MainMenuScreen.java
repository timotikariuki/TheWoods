package com.naturegames.thewoods.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.naturegames.thewoods.Main;
import com.naturegames.thewoods.tools.GameState;

public class MainMenuScreen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;

    //Table
    private TextButton settingsButton;
    private TextButton top5Button;
    private TextButton gameButton;


    public MainMenuScreen(Main main) {
        this.main = main;
        GameState.setState(GameState.MENU);
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin/skin.json"));

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("back"));

        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.space(12.0f);

        settingsButton = new TextButton("Settings", skin);
        verticalGroup.addActor(settingsButton);

        top5Button = new TextButton("Top 5", skin);
        verticalGroup.addActor(top5Button);

        gameButton = new TextButton("Game", skin);
        verticalGroup.addActor(gameButton);
        table1.add(verticalGroup).expand();

        table1.row();
        table1.add();
        table.add(table1).minWidth(800.0f).minHeight(360.0f).maxWidth(800.0f).maxHeight(360.0f);
        stage.addActor(table);

        setClickListeners();
    }

    private void setClickListeners() {
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new SettingsScreen(main));
            }
        });

        top5Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new Top5Screen(main));
            }
        });

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new GameScreen(main));
            }
        });
    }


    public void render(float delta) {
        renderCamera();
    }

    public void resize(int width, int height) {
        resizeCamera(width, height);
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    /////Camera
    private void showCameraAndStage() {
        viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
    }

    private void renderCamera() {
        ScreenUtils.clear(Color.ROYAL);

        viewport.apply();
        stage.act();
        stage.draw();
    }

    private void resizeCamera(int width, int height) {
        viewport.update(width, height, true);
    }
    ////////
}

