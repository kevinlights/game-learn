package com.gjh.learn.game.main;

import javax.swing.*;
import java.awt.*;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Game extends JPanel {
    private int gameWidth;
    private int gameHeight;
    private Image gameImage;
    private Thread gameThread;
    private volatile boolean running;

    public Game(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }
}
