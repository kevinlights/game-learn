package com.gjh.learn.game.state;

import com.gjh.learn.game.main.Resources;
import com.gjh.learn.game.util.LogUtil;

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
    public void update(float delta) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Resources.welcome, 0, 0, null);
    }

    @Override
    public void onClick(MouseEvent e) {
        LogUtil.debug("onClick");
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
