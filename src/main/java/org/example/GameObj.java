package org.example;

import org.example.utils.GameUtils;

import java.awt.*;

public class GameObj extends Rectangle {

    public Color color;
    private int speed = 0;
    public int rotation = 0;

    public GameObj(int x, int y, int width, int height) {
        super(x, y, width, height);

        color = new Color(GameUtils.generateRandomInt(255), GameUtils.generateRandomInt(255), GameUtils.generateRandomInt(255));
        speed = GameUtils.generateRandomInt(8 - 6) + 6;
    }

    public void update() {
        x += speed;
        rotation += 4;
        if (rotation >= 360) {
            rotation = 0;
        }
    }
}
