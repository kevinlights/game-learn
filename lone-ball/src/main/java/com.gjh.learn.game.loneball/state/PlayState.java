package com.gjh.learn.game.loneball.state;

import com.gjh.learn.game.loneball.main.GameMain;
import com.gjh.learn.game.loneball.main.Resources;
import com.gjh.learn.game.loneball.model.Ball;
import com.gjh.learn.game.loneball.model.Paddle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class PlayState extends State {

    private static final int PADDLE_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 60;
    private static final int BALL_DIAMETER = 20;
    public static final int BALL_X = 300;
    public static final int BALL_Y = 300;

    private int playerScore = 0;
    private Font scoreFont;

    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball ball;

    @Override
    public void init() {
        int y = (GameMain.GAME_HEIGHT - PADDLE_HEIGHT) / 2;
        int xr = GameMain.GAME_WIDTH - PADDLE_WIDTH;
        paddleLeft = new Paddle(0, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddleRight = new Paddle(xr, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        scoreFont = new Font("SansSerif", Font.BOLD, 25);
        ball = new Ball(BALL_X, BALL_Y, BALL_DIAMETER, BALL_DIAMETER);
    }

    @Override
    public void update() {
        paddleLeft.update();
        paddleRight.update();
        ball.update();

        checkCollides();
    }

    private void checkCollides() {
        if (ballCollides(paddleLeft)) {
            doCollides(paddleLeft);
        } else if (ballCollides(paddleRight)) {
            doCollides(paddleRight);
        } else if (ball.isDead()) {
            playerScore -= 3;
            ball.reset();
        }
    }

    private void doCollides(Paddle p) {
        playerScore++;
        ball.onCollideWith(p);
        Resources.hit.play();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Resources.darkBlue);
        g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.setColor(Resources.darkRed);
        g.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) - 2, 0, null);

        if (null != paddleLeft) {
            // draw paddles
            g.setColor(Color.white);
            g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
            g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
        }
        if (null != scoreFont) {
            g.setFont(scoreFont);
            g.drawString(String.valueOf(playerScore), GameMain.GAME_WIDTH / 2 - 50, 40);
        }
        if (null != ball) {
            g.drawRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        }
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            paddleLeft.accelUp();
            paddleRight.accelUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            paddleLeft.accelDown();
            paddleRight.accelDown();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            paddleLeft.stop();
            paddleRight.stop();
        }
    }

    private boolean ballCollides(Paddle p) {
        return ball.getRect().intersects(p.getRect());
    }
}
