package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static int counter = 100;
    public Spawner spawner;

    public Main() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);

        spawner = new Spawner();
    }


    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0 ,0 ,WIDTH, HEIGHT);

//        Colocar texto na parte de cima da tela
//        g.setFont(new Font("Arial", Font.BOLD, 23));
//        g.setColor(Color.white);
//        g.drawString("Pontos" + counter, WIDTH/2 - 60, 30);

        g.setColor(Color.GREEN);
        g.fillRect(Main.WIDTH/2 - 100 - 70, 20, counter*3, 20);
        g.setColor(Color.WHITE);
        g.drawRect(Main.WIDTH/2 - 100 - 70, 20, 300, 20);

        spawner.render(g);
        bs.show();
    }

    public void update() {
        spawner.update();

        //counter--;
        if(counter <= 0){
            counter = 100;

        }

    }

    @Override
    public void run() {
        while(true) {
            update();
            render();


            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        JFrame jFrame = new JFrame("Jogo Besta");
        jFrame.add(main);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);

        new Thread(main).start();

    }
}