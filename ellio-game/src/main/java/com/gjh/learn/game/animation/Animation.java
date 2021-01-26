package com.gjh.learn.game.animation;

import java.awt.*;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Animation {
    private Frame[] frames;
    private double[] frameEndTimes;
    private int currentFrameIndex = 0;
    private double totalDuration = 0;
    private double currentTime = 0;

    public Animation(Frame... frames) {
        this.frames = frames;
        frameEndTimes = new double[frames.length];
        for (int i = 0; i < frames.length; i++) {
            Frame f = frames[i];
            totalDuration += f.getDuration();
            frameEndTimes[i] = totalDuration;
        }
    }

    public synchronized void update(float increment) {
        currentTime += increment;
        // 当前时间大于总需要用时，则可知动画已经完成了，重复该动画
        if (currentTime > totalDuration) {
            wrapAnimation();
        }
        while (currentTime > frameEndTimes[currentFrameIndex]) {
            currentFrameIndex++;
        }
    }

    private synchronized void wrapAnimation() {
        currentFrameIndex = 0;
        // 重新设置动画之前，动画已经结束并经过了多少秒
        currentTime %= totalDuration;
    }

    public synchronized void render(Graphics g, int x, int y) {
        g.drawImage(frames[currentFrameIndex].getImage(), x, y, null);
    }

    public synchronized void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(frames[currentFrameIndex].getImage(), x, y, width, height, null);
    }
}
