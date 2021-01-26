package com.gjh.learn.game.loneball.state;

import com.gjh.learn.game.loneball.main.Resources;
import com.gjh.learn.game.loneball.util.LogUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class MenuState extends State{
    @Override
    public void init() {
        LogUtil.info("Entered MenuState");
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Resources.welcome, 0, 0, null);
    }

    @Override
    public void onClick(MouseEvent e) {
        LogUtil.debug("onClick");
        setCurrentState(new PlayState());
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        LogUtil.debug("onKeyPress");
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        LogUtil.debug("onKeyRelease");
    }
}
