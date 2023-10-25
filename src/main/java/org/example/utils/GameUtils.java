package org.example.utils;

import java.util.Random;

public class GameUtils {

    public static Integer generateRandomInt(Integer maxRandomValue) {
        return new Random().nextInt(maxRandomValue);
    }

}
