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
public class Paddle {
    private int paddleX = 200;
    private final int paddleY = 70;
    private final int move = 20;
    
    
    
    public void movePaddleLeft() {
        paddleX -= move;
        if (paddleX < 0) {
            paddleX = 0;
        }
    }

    public void movePaddleRight(int panelWidth) {
        paddleX += move;
        if (paddleX > panelWidth - 100) {
            paddleX = panelWidth - 100;
        }
    }
    
    public int getPaddleX() {
        return paddleX;
    }
    public int getPaddleY() {
        return paddleY;
    }
    
    public void draw(Graphics g, int screen_height) {
        g.setColor(Color.BLUE);
        g.fillRect(paddleX, screen_height - paddleY, 100, 20); // Drawing the paddle

    }
}
