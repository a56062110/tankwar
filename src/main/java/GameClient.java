import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;
    private PlayerTank playerTank;
    private boolean stop;
    protected static Image[] bulletImage = new Image[8];
    private  Image[] eTankImage = new Image[8];


    private CopyOnWriteArrayList<GameObject> gameObject = new CopyOnWriteArrayList<GameObject>();


    public void addGameObject(GameObject object) {
        gameObject.add(object);
    }


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
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public CopyOnWriteArrayList<GameObject> getGameObject() {
        return gameObject;
    }

    public static Image[] getBulletImage() {
        return bulletImage;
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
        for (GameObject object : gameObject) {
            object.draw(g);
        }
        for (GameObject object : gameObject) {
            if (!object.isAlive()) {
                gameObject.remove(object);
            }
        }

        checkGameState();
        System.out.println(gameObject.size());
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
            case KeyEvent.VK_CONTROL:
                playerTank.fire();
                break;
            case KeyEvent.VK_S:
                playerTank.superFire();
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

    public void init() {
        Image[] brickImage = {Tools.getImage("brick.png")};
        Image[] iTankImage = new Image[8];


        String[] sub = {"U.png", "D.png", "L.png", "R.png", "LU.png", "RU.png", "LD.png", "RD.png"};

        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tools.getImage("iTank" + sub[i]);
            eTankImage[i] = Tools.getImage("eTank" + sub[i]);
            bulletImage[i] = Tools.getImage("missile" + sub[i]);
        }
        playerTank = new PlayerTank(400, 50, Direction.DOWN, iTankImage);
        gameObject.add(playerTank);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gameObject.add(new EnemyTank(220 + j * 110, 300 + i * 110, Direction.UP, true, eTankImage));
            }
        }
        gameObject.addAll(Arrays.asList(new Wall[]{
                new Wall(175, 100, true, 15, brickImage),
                new Wall(125, 200, false, 12, brickImage),
                new Wall(675, 200, false, 12, brickImage),
        }));




    }

    public void checkGameState() {
        boolean gameWin = true;

        for (GameObject object : gameObject) {
            if (object instanceof EnemyTank) {
                gameWin = false;
                break;
            }
        }

        if (gameWin) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    gameObject.add(new EnemyTank(350 + j * 100, 500 + 80 * i, Direction.UP, true, eTankImage));
                }
            }
        }
    }


}
