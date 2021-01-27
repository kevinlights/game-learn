package com.gjh.learn.game.main;

import com.gjh.learn.game.animation.Animation;
import com.gjh.learn.game.animation.Frame;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class Resources {
    public static BufferedImage welcome, iconImage, block, cloud1, cloud2, duck, grass, jump, run1, run2, run3, run4, run5, selector;
    public static AudioClip hit, onjump;
    public static Color skyBlue;
    public static Animation runAnim;

    public static void load() {
        welcome = loadImage("welcome.png");
        iconImage = loadImage("iconimage.png");
        block = loadImage("block.png");
        cloud1 = loadImage("cloud1.png");
        cloud2 = loadImage("cloud2.png");
        duck = loadImage("duck.png");
        grass = loadImage("grass.png");
        jump = loadImage("jump.png");
        run1 = loadImage("run_anim1.png");
        run2 = loadImage("run_anim2.png");
        run3 = loadImage("run_anim3.png");
        run4 = loadImage("run_anim4.png");
        run5 = loadImage("run_anim5.png");
        selector = loadImage("selector.png");

        hit = loadSound("hit.wav");
        onjump = loadSound("onjump.wav");

        skyBlue = new Color(208, 244, 247);

        runAnim = new Animation(
                new Frame(run1, .1f),
                new Frame(run2, .1f),
                new Frame(run3, .1f),
                new Frame(run4, .1f),
                new Frame(run5, .1f)
        );
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
