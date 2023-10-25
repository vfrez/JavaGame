package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable, MouseListener {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static int counter = 100;
    public static int score = 0;
    public static int mx, my;
    public static boolean clicked = false;
    public static boolean gameOver = false;

    public Spawner spawner;

    public Main() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.addMouseListener(this);
        spawner = new Spawner();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (!gameOver) {
//        Colocar texto na parte de cima da tela
//        g.setFont(new Font("Arial", Font.BOLD, 23));
//        g.setColor(Color.white);
//        g.drawString("Pontos" + counter, WIDTH/2 - 60, 30);

            g.setColor(Color.GREEN);
            g.fillRect(Main.WIDTH / 2 - 170, 20, counter * 3, 20);
            g.setColor(Color.WHITE);
            g.drawRect(Main.WIDTH / 2 - 170, 20, 300, 20);

            spawner.render(g);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Perdeu", WIDTH / 2 - 100, HEIGHT / 2);

            g.drawString("Aperte Enter para recomeçar", WIDTH / 2 - 200, HEIGHT / 2 + 80);

        }
        bs.show();
    }

    public void update() {
        if (!gameOver) {
            spawner.update();
            if (counter <= 0) {
                counter = 100;
                gameOver = true;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            update();
            render();


            try {
                Thread.sleep(1000 / 60);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}