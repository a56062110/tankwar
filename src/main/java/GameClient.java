import object.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {

    private int screenWidth;

    private int screenHeight;

    private Tank playerTank;

    private boolean stop;

    public GameClient() {
        this(800, 600);          //設置遊戲畫面大小
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        playerTank = new Tank(100, 100, Direction.UP);

        new Thread(new Runnable() {
            public void run() {
                while (!stop) {

                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {                 //繪製功能
        super.paintComponent(g);                        //可有可無
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getScreenWidth(), getScreenHeight());
        playerTank.draw(g);                  //繪製圖案
    }

    public void keyPressed(KeyEvent e) {

        boolean[] dirs = playerTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
            default:
        }
        playerTank.move();
    }

    public void keyReleased(KeyEvent e) {

        boolean[] dirs = playerTank.getDirs();

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
            default:
        }
        playerTank.move();
    }

}
