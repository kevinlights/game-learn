package com.gjh.learn.game.loneball.model;

import com.gjh.learn.game.loneball.main.GameMain;
import com.gjh.learn.game.loneball.main.Resources;
import com.gjh.learn.game.loneball.util.RandomNumberGenerator;

import java.awt.*;

import static com.gjh.learn.game.loneball.state.PlayState.BALL_X;
import static com.gjh.learn.game.loneball.state.PlayState.BALL_Y;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Ball {
    private int x, y, width, height, velX, velY;
    private Rectangle rect;

    private static final int BALL_VEL_X = 2;
    private static final int BALL_VEL_Y_LOW = -2;
    private static final int BALL_VEL_Y_UP = 3;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        velX = BALL_VEL_X;
        velY = RandomNumberGenerator.getRandIntBetween(BALL_VEL_Y_LOW, BALL_VEL_Y_UP);
        rect = new Rectangle(x, y, width, height);
    }

    public void update() {
        x += velX;
        y += velY;
        correctYCollisions();
        updateRect();
    }

    public void onCollideWith(Paddle p) {
        // 球碰撞到板子时，修正坐标
        if (x < GameMain.GAME_WIDTH / 2) {
            // 左边板子时
            x = p.getX() + p.getWidth();
        } else {
            // 右边板子时
            x = p.getX() - width;
        }
        // 水平反向运动
        velX = -velX;
        // 竖直方向随机速度运动
        // 真实物理引擎应该是要计算初始碰撞角度和碰撞方向，再决定反弹方向
        velY = RandomNumberGenerator.getRandIntBetween(BALL_VEL_Y_LOW, BALL_VEL_Y_UP);
    }

    public boolean isDead() {
        return x < 0 || x + width > GameMain.GAME_WIDTH;
    }

    public void reset() {
        x = BALL_X;
        y = BALL_Y;
        velX = BALL_VEL_X;
        velY = RandomNumberGenerator.getRandIntBetween(BALL_VEL_Y_LOW, BALL_VEL_Y_UP);
    }

    private void updateRect() {
        rect.setBounds(x, y, width, height);
    }

    private void correctYCollisions() {
        // 遇到上下边界时，修正坐标
        if (y < 0) {
            y = 0;
        } else if (y + height > GameMain.GAME_HEIGHT) {
            y = GameMain.GAME_HEIGHT - height;
        } else {
            return;
        }
        // 并竖直反向运动
        velY = -velY;
        // 播放声音
        Resources.bounce.play();
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
