

package fruitgametest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class FruitGameTest extends JFrame {
    private JPanel gamePanel;


    private List<Fruit> fruits = new ArrayList<>();
    private Paddle paddle;
    private int score = 0;
    
    
    private long lastFruitTime = 0;
    private static final long FRUIT_CREATION_DELAY = 2500; // 2.5 second
    

    public FruitGameTest() {
        setTitle("Fruit Catch Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        paddle = new Paddle();
                

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };
        setContentPane(gamePanel);

        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    paddle.movePaddleLeft();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    paddle.movePaddleRight(getWidth());
                }
            }
        });

        
        setFocusable(true);
        requestFocus();

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });
        timer.start();
    }



    private void updateGame() {
        // Update falling fruits and check for collisions
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            fruit.update();
            if (fruit.getY() > getHeight()) {
                fruits.remove(i);
                i--;
            } else if (fruit.getY() + Fruit.FRUIT_HEIGHT > getHeight() - 50 &&
                    fruit.getX() >= paddle.getPaddleX() && fruit.getX() <= paddle.getPaddleX() + 100) {
                fruits.remove(i);
                i--;
                score += 10;
            }
        }
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFruitTime >= FRUIT_CREATION_DELAY) {
            int x = new Random().nextInt(getWidth() - Fruit.FRUIT_WIDTH);
            fruits.add(new Fruit(x, 0));
            lastFruitTime = currentTime;
        }



        
    }
    
    private double calculateDistance(Fruit fruit) {
        int paddleCenterX = paddle.getPaddleX() + 50; // Assuming the paddle width is 100
        int fruitCenterX = fruit.getX() + Fruit.FRUIT_WIDTH / 2;
        int fruitCenterY = fruit.getY() + Fruit.FRUIT_HEIGHT / 2;

        int dx = fruitCenterX - paddleCenterX;
        int dy = fruitCenterY - (getHeight() - 30);

        return Math.sqrt(dx * dx + dy * dy);
    }

    private void drawGame(Graphics g) {
        // Panel
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Paddle
        paddle.draw(g, getHeight());

        for (Fruit fruit : fruits) {
            fruit.draw(g);
            //g.draw(fruit.getImage(), fruit.getX(), fruit.getY(), null);
        }

        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
        
        if (!fruits.isEmpty()) {
            Fruit nextFruit = fruits.get(0);
            double distance = calculateDistance(nextFruit);

            g.setColor(Color.BLACK);
            g.drawString("Distance to next apple: " + distance, 10, 40);
    }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FruitGameTest().setVisible(true);
            }
        });
    }

}
