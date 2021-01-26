package com.gjh.learn.game.main;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
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

    public static void load() {
        welcome = loadImage("welcome.png");
        iconImage = loadImage("iconimage.png");
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
