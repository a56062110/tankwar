import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {

    private int speed;
    private Direction direction;
    private boolean enemy;
    private boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction,Image[] image){
        this(x,y,direction,false,image);
    }

    public Tank(int x, int y, Direction direction,boolean enemy,Image[] image) {
        super(x,y,image);
        this.direction = direction;
        this.enemy =enemy;
        speed = 10;

    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void move() {
        oldX = x;
        oldY = y;
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LEFT:
                y += speed;
                x -= speed;
                break;
            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }

        if (x<0){
            x=0;
        }else if (x>TankGame.getGameClient().getScreenWidth()-width){
            x=TankGame.getGameClient().getScreenWidth()-width;
        }
        if (y<0){
            y=0;
        }else if (y>TankGame.getGameClient().getScreenHeight()-height){
            y=TankGame.getGameClient().getScreenHeight()-height;
        }

        for (Wall wall:TankGame.gameClient.getWalls()){
            if (getRectangle().intersects(wall.getRectangle())){
                System.out.println("hit!");
                x=oldX;
                y=oldY;
                return;
            }
        }
        for (Tank tank:TankGame.gameClient.getEnemyTanks()){
            if (getRectangle().intersects(tank.getRectangle())){
                x=oldX;
                y=oldY;
                return;
            }
        }

    }

    private void determineDirection() {
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP;
        else if (dirs[1] && !dirs[3] && !dirs[2] && !dirs[0]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[0] && !dirs[1] && !dirs[3]) direction = Direction.LEFT;
        else if (dirs[3] && !dirs[0] && !dirs[1] && !dirs[2]) direction = Direction.RIGHT;

    }

    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
        }
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    public boolean isStop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }


}
