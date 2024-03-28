package com.naturegames.thewoods.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.naturegames.thewoods.Main;
import com.naturegames.thewoods.actors.AcornActor;
import com.naturegames.thewoods.actors.BackgroundGroup;
import com.naturegames.thewoods.actors.ItemActor;
import com.naturegames.thewoods.tools.GameState;

import java.util.Iterator;

public class GameScreen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;

    //Table
    private Label scoreLabel;

    //Game
    private int score = 0;
    private final int speed = 200;
    private int hearts = 3;
    private final Timer timerSpawnItems = new Timer();

    //Actors
    private BackgroundGroup backgroundGroup;
    private AcornActor acornActor;
    private final Array<ItemActor> items = new Array<>();
    private final Group itemsGroup = new Group();


    public GameScreen(Main main) {
        this.main = main;
        GameState.setState(GameState.WAITING);
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin/skin.json"));

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("back_game"));

        scoreLabel = new Label("0", skin, "f36_game");
        scoreLabel.setAlignment(Align.topRight);
        table1.add(scoreLabel).padRight(16.0f).padTop(16.0f).expand().align(Align.topRight);
        table.add(table1).minWidth(800.0f).minHeight(360.0f).maxWidth(800.0f).maxHeight(360.0f);
        stage.addActor(table);

        addMyActors();
        addActorsInStage();
        setClickListeners();
        initInputAdapter();
        spawnItem();
    }

    private void setClickListeners() {
    }


    public void render(float delta) {
        renderCamera();
        update();
    }

    private void update() {
        removeItems();
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
//        Gdx.input.setInputProcessor(stage);
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

    private void addMyActors() {
        Image roadImage = new Image(skin, "road");
        System.out.println(roadImage.getX() + " " + roadImage.getY() + " " + roadImage.getWidth() + " " + roadImage.getHeight());
        backgroundGroup = new BackgroundGroup(roadImage, -10, -30, roadImage.getWidth(), roadImage.getHeight(), speed);

        Image acornImage = new Image(skin, "object");
        acornActor = new AcornActor(acornImage, 27, 75, acornImage.getWidth(), acornImage.getHeight());

    }

    private void addActorsInStage() {
        stage.addActor(backgroundGroup);
        stage.addActor(acornActor);
        stage.addActor(itemsGroup);
    }

    private void initInputAdapter() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (GameState.getState() == GameState.WAITING) {
                    GameState.setState(GameState.GAME);
                } else if (GameState.getState() == GameState.GAME) {
                    acornActor.jump();
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void spawnItem() {
        timerSpawnItems.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ItemActor item = new ItemActor(skin, SCREEN_WIDTH, 75, speed);

                items.add(item);
                itemsGroup.addActor(item);
            }
        }, 1f, 2f);
    }

    private void removeItems() {
        if (GameState.getState() == GameState.GAME) {
            System.out.println("GOOO");
            for(Iterator<ItemActor> iterator = items.iterator(); iterator.hasNext();) {
                ItemActor item = iterator.next();
                if (Intersector.overlaps(acornActor.getRect(), item.getRect())) { /// remove items after a collision
                    System.out.println("collision");
                    if (item.getName().equals("coin")) {
                        score++;
                        scoreLabel.setText(score);
                    } else {
                        hearts--;
                        if (hearts == 0) {
                            timerSpawnItems.clear();
                            main.setScreen(new EndGameScreen(main, score));
                        }
                    }
                    iterator.remove();
                    itemsGroup.removeActor(item);
                } else if (item.getX() + item.getWidth() < 0) { /// remove items outside the screen
                    iterator.remove();
                    itemsGroup.removeActor(item);
                }
            }
        }
    }
}

