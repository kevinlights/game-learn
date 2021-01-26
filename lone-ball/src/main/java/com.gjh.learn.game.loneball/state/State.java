package com.gjh.learn.game.loneball.state;

import com.gjh.learn.game.loneball.main.GameMain;

import java.awt.*;
import java.awt.event.*;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public abstract class State {

    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onClick(MouseEvent e);

    public abstract void onKeyPress(KeyEvent e);

    public abstract void onKeyRelease(KeyEvent e);

    public void setCurrentState(State newState) {
        GameMain.sGame.setCurrentState(newState);
    }
}
