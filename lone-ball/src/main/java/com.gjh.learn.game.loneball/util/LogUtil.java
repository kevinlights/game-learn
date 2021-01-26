package com.gjh.learn.game.loneball.util;

import java.time.LocalDateTime;

/**
 * created on 2021/1/26
 *
 * @author kevinlights
 */
public class LogUtil {
    public static void debug(String msg, Object... args) {
        log(String.format(msg, args));
    }

    public static void info(String msg, Object... args) {
        log(String.format(msg, args));
    }

    public static void error(String msg, Object... args) {
        log(String.format(msg, args));
    }

    private static void log(String msg) {
        System.out.println(String.format("%s [%s - %s] %s", LocalDateTime.now().toString(), Thread.currentThread().getName(), Thread.currentThread().getId(), msg));
    }
}
