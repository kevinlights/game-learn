package com.gjh.learn.game.loneball.main;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Resources {
    public static BufferedImage welcome;
    public static BufferedImage iconImage;
    public static BufferedImage line;
    public static AudioClip hit;
    public static AudioClip bounce;
    public static Color darkBlue;
    public static Color darkRed;

    public static void load() {
        welcome = loadImage("welcome.png");
        iconImage = loadImage("iconimage.png");
        line = loadImage("line.png");
        hit = loadSound("hit.wav");
        bounce = loadSound("bounce.wav");
        darkBlue = new Color(25, 83, 105);
        darkRed = new Color(105, 13, 13);
    }

    private static AudioClip loadSound(String fileName) {
        URL fileURL = Resources.class.getClassLoader().getResource(fileName);
        return Applet.newAudioClip(fileURL);
    }

    private static BufferedImage loadImage(String fileName) {
        try {
            return ImageIO.read(Resources.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            return null;
        }
    }
}
