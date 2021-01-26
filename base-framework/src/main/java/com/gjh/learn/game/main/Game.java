package com.gjh.learn.game.main;

import com.gjh.learn.game.state.LoadState;
import com.gjh.learn.game.state.State;
import com.gjh.learn.game.util.InputHandler;
import com.gjh.learn.game.util.LogUtil;

import javax.swing.*;
import java.awt.*;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Game extends JPanel implements Runnable {
    private int gameWidth;
    private int gameHeight;
    private Image gameImage;

    private volatile boolean running;

    private volatile State currentState;

    private InputHandler inputHandler;

    public Game(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }

    private void initInput() {
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
    }

    public void setCurrentState(State newState) {
        System.gc();
        currentState = newState;
        newState.init();
        inputHandler.setCurrentState(currentState);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        initInput();
        setCurrentState(new LoadState());
        initGame();
    }

    private void initGame() {
        running = true;
        Thread gameThread = new Thread(this, "game-thread");
        gameThread.start();
    }


    /**
     * 自定义主动渲染方法
     * @param g
     */
    private void renderGameImage(Graphics g) {
        if (gameImage != null) {
            g.drawImage(gameImage, 0, 0, null);
        }
        g.dispose();
    }

    @Override
    public void run() {
        // 按照每秒 60 帧 (FPS) 的速率计算，一次迭代需要 1 / 60 约为 17 毫秒
        // 睡眠时间应按照实际 更新-渲染 的用时来计算
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;
        while (running) {
            long beforeUpdaedRender = System.nanoTime();
            long deltaMillis = updateDurationMillis + sleepDurationMillis;

            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdaedRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);
            try {
                Thread.sleep(sleepDurationMillis);
            } catch (InterruptedException e) {
                LogUtil.error("sleep interrupted error: %s", e.toString());
            }
        }
        System.exit(0);
    }

    /**
     * @param deltaMillis
     */
    private void updateAndRender(long deltaMillis) {
        currentState.update(deltaMillis / 1000f);
        prepareGameImage();
        if (null != gameImage && null != gameImage.getGraphics()) {
            currentState.render(gameImage.getGraphics());
        }
        // will trigger paintComponent
        // repaint();
        renderGameImage(getGraphics());
    }



    public void exit() {
        running = false;
    }

    private void prepareGameImage() {
        if (gameImage == null) {
            gameImage = createImage(gameWidth, gameHeight);
        }
        Graphics g = gameImage.getGraphics();
        g.clearRect(0, 0, gameWidth, gameHeight);
    }
}
