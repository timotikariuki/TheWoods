package com.naturegames.thewoods.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.naturegames.thewoods.Main;
import com.naturegames.thewoods.data.GameSettingsAndData;

public class SettingsScreen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;

    //Table
    private Image returnButton;
    private CheckBox musicCheckBox;


    public SettingsScreen(Main main) {
        this.main = main;
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin/skin.json"));

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("back_settings"));

        returnButton = new Image(skin, "button_return");
        table1.add(returnButton).padLeft(14.0f).padTop(16.0f).expandX().align(Align.topLeft);

        table1.row();
        musicCheckBox = new CheckBox(null, skin);
        musicCheckBox.setChecked(true);
        table1.add(musicCheckBox).padLeft(336.0f).padTop(91.0f).expand().align(Align.topLeft);
        table.add(table1).minWidth(800.0f).minHeight(360.0f).maxWidth(800.0f).maxHeight(360.0f);
        stage.addActor(table);
        musicCheckBox.setChecked(GameSettingsAndData.getMusicOn());

        setClickListeners();
    }

    private void setClickListeners() {
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenuScreen(main));
            }
        });

        musicCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSettingsAndData.setMusicOn(musicCheckBox.isChecked());
                main.playMusic(GameSettingsAndData.getMusicOn());
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

