package com.naturegames.thewoods.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.naturegames.thewoods.Main;
import com.naturegames.thewoods.data.GameSettingsAndData;

public class Top5Screen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;

    //Table
    private Image returnButton;


    public Top5Screen(Main main) {
        this.main = main;
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin/skin.json"));


        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("back_top5"));

        returnButton = new Image(skin, "button_return");
        table1.add(returnButton).padLeft(14.0f).padTop(16.0f).align(Align.topLeft);

        table1.row();
        TextButton textButton = new TextButton(String.valueOf(GameSettingsAndData.getTop5Score(0)), skin, "b2");
        table1.add(textButton).padLeft(150.0f).align(Align.topLeft);

        textButton = new TextButton(String.valueOf(GameSettingsAndData.getTop5Score(1)), skin, "b2");
        table1.add(textButton).padTop(40.0f).align(Align.topLeft);

        textButton = new TextButton(String.valueOf(GameSettingsAndData.getTop5Score(2)), skin, "b2");
        table1.add(textButton).padTop(82.0f).align(Align.topLeft);

        textButton = new TextButton(String.valueOf(GameSettingsAndData.getTop5Score(3)), skin, "b2");
        table1.add(textButton).padTop(132.0f).align(Align.topLeft);

        textButton = new TextButton(String.valueOf(GameSettingsAndData.getTop5Score(4)), skin, "b2");
        table1.add(textButton).padTop(180.0f).expand().align(Align.topLeft);
        table.add(table1).minWidth(800.0f).minHeight(360.0f).maxWidth(800.0f).maxHeight(360.0f);
        stage.addActor(table);

        setClickListeners();
    }

    private void setClickListeners() {
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenuScreen(main));
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

