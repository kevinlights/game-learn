package com.gjh.learn.game.loneball.main;

import com.gjh.learn.game.loneball.state.LoadState;
import com.gjh.learn.game.loneball.state.State;
import com.gjh.learn.game.loneball.util.InputHandler;
import com.gjh.learn.game.loneball.util.LogUtil;

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
        // System.gc();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameImage == null) {
            return;
        }
        g.drawImage(gameImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (running) {
            currentState.update();
            prepareGameImage();
            if (null != gameImage && null != gameImage.getGraphics()) {
                currentState.render(gameImage.getGraphics());
            }
            // will trigger paintComponent
            repaint();
            try {
                Thread.sleep(14);
            } catch (InterruptedException e) {
                LogUtil.error("sleep interrupted error: %s", e.toString());
            }
        }
        System.exit(0);
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
