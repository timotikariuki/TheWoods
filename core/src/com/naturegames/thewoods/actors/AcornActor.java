package com.naturegames.thewoods.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.naturegames.thewoods.tools.GameState;

public class AcornActor extends Actor {

    private final Image image;
    private final Rectangle rect = new Rectangle();

    public float width;
    public float height;

    private boolean isJumping = false;
    private float jumpVelocity = 350;
    private final float gravity = -500;
    private final float startY;


    public AcornActor(Image image, float x, float y, float width, float height) {
        this.image = image;
        this.width = width;
        this.height = height;

        startY = y;

        setBounds(x, y, width, height);
        setOrigin(width / 2, height / 2);
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
        if (GameState.getState() == GameState.GAME) {
            if (isJumping) {
                float deltaY = jumpVelocity * delta + 0.5f * gravity * delta * delta;
                setY(getY() + deltaY);
                jumpVelocity += gravity * delta;

                if (getY() <= startY) {
                    setY(startY);
                    jumpVelocity = 0;
                    isJumping = false;
                }
            } else {
               rotateBy(-360 * delta);
            }
        }
        super.act(delta);
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            jumpVelocity = 350;
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

