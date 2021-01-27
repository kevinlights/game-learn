package com.gjh.learn.game.state;

import com.gjh.learn.game.main.GameMain;
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

    private int currentSelection = 0;

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
        if (currentSelection == 0) {
            g.drawImage(Resources.selector, 335, 241, null);
        } else {
            g.drawImage(Resources.selector, 335, 291, null);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        LogUtil.debug("onClick");
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        LogUtil.debug("onKeyPress");
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
            if (currentSelection == 0) {
                setCurrentState(new PlayState());
            } else if (currentSelection == 1) {
                GameMain.sGame.exit();
            }
        } else if (key == KeyEvent.VK_UP) {
            currentSelection = 0;
        } else if (key == KeyEvent.VK_DOWN) {
            currentSelection = 1;
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        LogUtil.debug("onKeyRelease");
    }
}
