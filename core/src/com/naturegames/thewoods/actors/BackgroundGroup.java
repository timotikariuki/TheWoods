package com.naturegames.thewoods.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.naturegames.thewoods.tools.GameState;

public class BackgroundGroup extends Group {
    private final Image background1;
    private final Image background2;
    private final float speed;
    private final float width;
    private final float deltaX = -50;


    public BackgroundGroup(Image background, float x, float y, float width, float height, float speed) {
        this.background1 = new Image(background.getDrawable());
        this.background2 = new Image(background.getDrawable());
        this.speed = speed;
        this.width = width;

        background1.setBounds(x, y, width, height);
        background2.setBounds(x + width + deltaX, y, width, height);

        this.addActor(background1);
        this.addActor(background2);
    }

    @Override
    public void act(float delta) {
        if (GameState.getState() == GameState.GAME) {
            background1.moveBy(-speed * delta, 0);
            background2.moveBy(-speed * delta, 0);
            updateBackgrounds();
        }
    }

    private void updateBackgrounds() {
        if (background1.getX() + width < 0) {
            background1.setPosition(background2.getX() + width + deltaX, background1.getY());
        }
        if (background2.getX() + width < 0) {
            background2.setPosition(background1.getX() + width + deltaX, background2.getY());
        }
    }
}

