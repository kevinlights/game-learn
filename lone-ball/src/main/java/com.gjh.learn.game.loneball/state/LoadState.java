package com.gjh.learn.game.loneball.state;

import com.gjh.learn.game.loneball.main.Resources;
import com.gjh.learn.game.loneball.state.MenuState;
import com.gjh.learn.game.loneball.util.LogUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class LoadState extends State{
    @Override
    public void init() {
        Resources.load();
        LogUtil.info("Loaded Successfully");
    }

    @Override
    public void update() {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {

    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
