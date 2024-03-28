package com.naturegames.thewoods.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.naturegames.thewoods.tools.GameState;

public class ItemActor extends Actor {

    private final Image image;
    private final float speed;
    private final Rectangle rect = new Rectangle();


    public ItemActor(Skin skin, float x, float y, float speed) {
        this.speed = speed;
        int randomItem = MathUtils.random(0, 10);

        if (randomItem >= 0 && randomItem <= 4) {
            image = new Image(skin, "rock" + randomItem);
            setBounds(x, y, image.getWidth(), image.getHeight());
            setName("rock");
        } else {
            image = new Image(skin, "coin");
            if(MathUtils.randomBoolean()){
                setBounds(x, 200, image.getWidth(), image.getHeight());
            } else {
                setBounds(x, y, image.getWidth(), image.getHeight());
            }
            setName("coin");
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        image.setBounds(getX(), getY(), getWidth(), getHeight());
        image.setOrigin(getOriginX(), getOriginY());
        image.setRotation(getRotation());
        image.setScale(getScaleX(), getScaleY());
        image.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameState.getState() == GameState.GAME) {
            moveBy(-speed * delta, 0);
        }
    }

    public Rectangle getRect() {
        rect.x = getX();
        rect.y = getY();
        rect.width = getWidth();
        rect.height = getHeight();
        return rect;
    }
}

