package com.gjh.learn.game.animation;

import java.awt.*;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Frame {
    private Image image;
    private double duration;

    public Frame(Image image, double duration) {
        this.image = image;
        this.duration = duration;
    }

    public Image getImage() {
        return image;
    }

    public double getDuration() {
        return duration;
    }
}
