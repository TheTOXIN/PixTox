package com.toxin.pixtox;

import java.util.Random;

public class Util {

    private static Random rand = new Random();

    public static int randTo(int n) {
        return rand.nextInt(n);
    }

}
