package com.gjh.learn.game.model;

import com.gjh.learn.game.main.Resources;

import java.awt.*;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Player {
    private float x, y;
    private int width, height, velY;
    private Rectangle rect, duckRect, ground;

    private boolean isAlive;
    private boolean isDucked;
    private float duckDuration = DUCK_DURATION;

    private static final int JUMP_VELOCITY = -600;
    private static final int ACCEL_GRAVITY = 1800;
    private static final float DUCK_DURATION = .6f;

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ground = new Rectangle(0, 405, 800, 45);
        rect = new Rectangle();
        duckRect = new Rectangle();
        isAlive = true;
        isDucked = false;
        updateRects();
    }

    public void update(float delta) {
        if (duckDuration > 0 && isDucked) {
            duckDuration -= delta;
        } else {
            isDucked = false;
            duckDuration = DUCK_DURATION;
        }
        if (isGrounded()) {
            y = 406 - height;
            velY = 0;
        } else {
            velY += ACCEL_GRAVITY * delta;
        }
        y += velY * delta;
        updateRects();
    }

    public void jump() {
        if (isGrounded()) {
            Resources.onjump.play();
            isDucked = false;
            duckDuration = DUCK_DURATION;
            y -= 10;
            velY = JUMP_VELOCITY;
            updateRects();
        }
    }

    public void duck() {
        if (isGrounded()) {
            isDucked = true;
        }
    }

    public void pushBack(int dX) {
        Resources.hit.play();
        x -= dX;
        if (x < -width / 2) {
            isAlive = false;
        }
        rect.setBounds((int) x, (int) y, width, height);
    }

    public boolean isGrounded() {
        return rect.intersects(ground);
    }

    private void updateRects() {
        rect.setBounds((int) x + 10, (int) y, width - 20, height);
        duckRect.setBounds((int) x, (int) y + 20, width, height - 20);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getVelY() {
        return velY;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Rectangle getDuckRect() {
        return duckRect;
    }

    public Rectangle getGround() {
        return ground;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isDucked() {
        return isDucked;
    }

    public float getDuckDuration() {
        return duckDuration;
    }
}
