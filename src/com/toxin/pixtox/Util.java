package com.toxin.pixtox;

import java.util.Random;

import static com.toxin.pixtox.Const.H;
import static com.toxin.pixtox.Const.W;

public class Util {

    private static Random rand = new Random();

    public static int randTo(int n) {
        return rand.nextInt(n);
    }

    public static boolean luckyTo(int n) {
        return rand.nextInt(n) == 0;
    }

    public static boolean validPoint(int x, int y) {
        return y >= 0 && y < H && x >= 0 && x < W;
    }

}
