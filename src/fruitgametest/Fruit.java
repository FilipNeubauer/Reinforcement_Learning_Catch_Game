/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitgametest;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author filipneubauer
 */
public class Fruit {
        public static final int FRUIT_WIDTH = 30;
        public static final int FRUIT_HEIGHT = 30;
        private static final int move = 2;

        private int x, y;
        private static final int FRUIT_RADIUS = 15;
        private final Color FRUIT_COLOR = Color.RED;


        public Fruit(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void draw(Graphics g) {
            g.setColor(FRUIT_COLOR);
            g.fillOval(x - FRUIT_RADIUS, y - FRUIT_RADIUS, FRUIT_RADIUS * 2, FRUIT_RADIUS * 2);
        }

        public void update() {
            y += move;
        }
}
