package com.gjh.learn.game.loneball.model;

import com.gjh.learn.game.loneball.main.GameMain;

import java.awt.*;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;
    private int velY;
    private Rectangle rect;

    private final static int MOVE_SPEED_Y = 4;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
        velY = 0;
    }

    public void update() {
        y += velY;
        if (y < 0) {
            y = 0;
        } else if (y + height > GameMain.GAME_HEIGHT) {
            y = GameMain.GAME_HEIGHT - height;
        }
        updateRect();
    }

    private void updateRect() {
        rect.setBounds(x, y, width, height);
    }

    public void accelUp() {
        velY = -MOVE_SPEED_Y;
    }

    public void accelDown() {
        velY = MOVE_SPEED_Y;
    }

    public void stop() {
        velY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRect() {
        return rect;
    }

}
