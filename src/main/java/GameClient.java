import object.Direction;
import object.Tank;
import object.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameClient extends JComponent {

    private int screenWidth;

    private int screenHeight;

    private Tank playerTank;

    private List<Tank> enemyTanks = new ArrayList<Tank>();

    private List<Wall> walls = new ArrayList<Wall>();

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

        Wall[] walls = {
                new Wall(150,175,true,12),
                new Wall(100,265,true,14),
                new Wall(150,355,true,14),
                new Wall(60,60,false,17),
                new Wall(150,3,false,5),
                new Wall(600,3,false,16),
                new Wall(450,43,false,4),
                new Wall(350,3,false,4),
                new Wall(250,43,false,4),

        };
        this.walls.addAll(Arrays.asList(walls));

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
        for (Tank tank : enemyTanks){
            tank.draw(g);
        }
        for (Wall wall:walls){
            wall.draw(g);
        }
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

    }

    public void init(){
        playerTank = new Tank(375,80,Direction.DOWN);
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                enemyTanks.add(new Tank(250+j*80,300+i*80,Direction.UP,true));
            }
        }
    }


}
