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

GameClient(){
    this(800,600);          //設置遊戲畫面大小
}
public GameClient(int screenWidth,int screenHeight){
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.setPreferredSize(new Dimension(screenWidth,screenHeight));

    playerTank = new Tank(100,100,Direction.UP);

    new Thread(new Runnable() {
        public void run() {
            while (!stop){

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

    @Override
    protected void paintComponent(Graphics g) {                 //繪製功能
        super.paintComponent(g);                        //可有可無
        g.drawImage(playerTank.getImage(),playerTank.getX(),playerTank.getY(),null);                  //繪製圖案
    }


    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                playerTank.setY(playerTank.getY()-playerTank.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                playerTank.setY(playerTank.getY()+playerTank.getSpeed());
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                playerTank.setX(playerTank.getX()-playerTank.getSpeed());
                break;
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                playerTank.setX(playerTank.getX()+playerTank.getSpeed());
                break;
            default:
        }
        playerTank.move();
    }
}
