/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.snake;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author anishavelayudhan
 */
public class Game {
    int gridHeight = 10;
    int gridWidth = 20;
    JFrame frame;
    GamePanel gamePanel;
    Snake snake;
    Apple apple;    



    // Input
    public Game() {
        snake = new Snake(gridWidth, gridHeight);
        apple = new Apple(gridWidth, gridHeight);
        apple.randomizePos(snake);

        gamePanel = new GamePanel(this);
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true); 
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Handle key presses and update snake direction
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (snake.direction != 'D'){
                            snake.direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.direction != 'U'){
                            snake.direction = 'D';
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (snake.direction != 'R'){
                            snake.direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.direction != 'L'){
                            snake.direction = 'R';
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        start();
    }



    private void start() {
        TimerTask moveTask = new TimerTask() {
            @Override
            public void run() {
                if (!snake.checkCollision && !snake.snakeMax()) {
                    if (snake.move(apple.getX(), apple.getY())){
                        apple.randomizePos(snake);
                    }                  
                    gamePanel.repaint();
                } else {
                    cancel();
                    if (snake.checkCollision) {
                        JOptionPane.showMessageDialog(frame, "GAME OVER!");
                    } else if (snake.snakeMax()) {
                       JOptionPane.showMessageDialog(frame, "CONGRATULATIONS! YOU WON THE GAME!");
                    }
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(moveTask, 0, 300);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Game game = new Game();
            }
        });   
    }

}
