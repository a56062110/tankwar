import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;

    private ArrayList<GameObject> gameObject = new ArrayList<GameObject>();
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

        init();

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

    public ArrayList<GameObject> getGameObject() {
        return gameObject;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {                 //繪製功能
        g.setColor(Color.lightGray);
//        g.fillRect(0, 0, getScreenWidth(), getScreenHeight());
//        playerTank.draw(g);                  //繪製圖案
//        for (Tank tank : enemyTanks){
//            tank.draw(g);
//        }
//        for (Wall wall:walls){
//            wall.draw(g);
//        }
        for (GameObject object : gameObject) {
            object.draw(g);
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
        Image[] brickImage = {Tools.getImage("brick.png")};
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];

        String[] sub = {"U.png", "D.png", "L.png", "R.png", "LU.png", "RU.png", "LD.png", "RD.png"};

        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tools.getImage("iTank" + sub[i]);
            eTankImage[i] = Tools.getImage("eTank" + sub[i]);
        }
        playerTank = new Tank(375, 50, Direction.DOWN, iTankImage);
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                enemyTanks.add(new Tank(250+j*80,300+i*80,Direction.UP,true, eTankImage));
            }
        }
        walls.addAll(Arrays.asList(new Wall[]{
                new Wall(150, 100, true, 15, brickImage),
                new Wall(150, 200, false, 12, brickImage),
                new Wall(600, 200, false, 12, brickImage),
        }));

        gameObject.add(playerTank);
        gameObject.addAll((Collection<? extends GameObject>) walls);
        gameObject.addAll((Collection<? extends GameObject>) enemyTanks);





    }


}
