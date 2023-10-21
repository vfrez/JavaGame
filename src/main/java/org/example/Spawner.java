package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {
    public int timer = 0;
    public List<RectObj> rectangles = new ArrayList<RectObj>();
    public List<Particle> particles = new ArrayList<Particle>();

    public void render(Graphics g) {
        for(int i = 0; i < rectangles.size(); i++){
            RectObj current = rectangles.get(i);

            Graphics2D g2 = (Graphics2D) g ;
            g2.rotate(Math.toRadians(current.rotation), current.x+current.width/2, current.y+current.height/2);
            g2.setColor(current.color);
            g2.fillRect(current.x, current.y, current.width, current.height);
            g2.rotate(Math.toRadians(-current.rotation), current.x+current.width/2, current.y+current.height/2);


        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(g);
        }

    }


    public void update() {
        timer++;
        if(timer%20 == 0) {
            rectangles.add(new RectObj(0, new Random().nextInt(480 - 40), 40, 40));
        }

        for (int i = 0; i < rectangles.size(); i++) {
            RectObj current = rectangles.get(i);

            rectangles.get(i).update();

            if (current.x > Main.WIDTH){
                rectangles.remove(current);
                Main.counter-=2;
            }

            if(Main.clicked) {
                if(Main.mx >= current.x && Main.mx < current.x + current.width){
                    if(Main.my >= current.y && Main.my < current.y + current.height){
                        rectangles.remove(current);
                        Main.score++;
                        Main.clicked=false;

                        for (int j = 0; j < 50; j++) {
                            particles.add(new Particle(current.x, current.y, 8, 8, current.color));
                        }

                    }

                }
            }
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();

            Particle part = particles.get(i);
            if(part.timer >= 60) {
                particles.remove(part);
            }

        }
    }
}
