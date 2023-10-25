package org.example;

import org.example.utils.GameUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Spawner {

    public int timer = 0;
    public List<GameObj> rectangles = new ArrayList<>();
    public List<Particle> particles = new ArrayList<>();

    public void render(Graphics g) {
        rectangles.forEach(current -> {
            Graphics2D g2 = (Graphics2D) g;
            g2.rotate(Math.toRadians(current.rotation), current.x + current.width / 2, current.y + current.height / 2);
            g2.setColor(current.color);
            g2.fillRect(current.x, current.y, current.width, current.height);
            g2.rotate(Math.toRadians(-current.rotation), current.x + current.width / 2, current.y + current.height / 2);
        });

        particles.forEach(particle -> particle.render(g));

    }

    public void update() {
        timer++;
        if (timer % 20 == 0) {
            rectangles.add(new GameObj(0, GameUtils.generateRandomInt(440), 40, 40));
        }

        for (int i = 0; i < rectangles.size(); i++) {
            GameObj current = rectangles.get(i);

            rectangles.get(i).update();

            if (current.x > Main.WIDTH) {
                rectangles.remove(current);
                Main.counter -= 2;
            }

            if (Main.clicked) {
                if (Main.mx >= current.x && Main.mx < current.x + current.width) {
                    if (Main.my >= current.y && Main.my < current.y + current.height) {
                        rectangles.remove(current);
                        Main.score++;
                        Main.clicked = false;

                        IntStream.range(0, 50).forEach(j -> particles.add(new Particle(current.x, current.y, 8, 8, current.color)));

                    }

                }
            }
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();

            Particle part = particles.get(i);
            if (part.timer >= 60) {
                particles.remove(part);
            }
        }
    }
}
