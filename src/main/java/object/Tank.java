package object;

import javax.swing.*;
import java.awt.*;

public class Tank {
    private int x, y, speed;
    private Direction direction;
    private boolean[] dirs = new boolean[4];

//    public static final int UP = 0;
//    public static final int DOWN = 1;
//    public static final int LEFT = 2;
//    public static final int RIGHT = 3;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 5;

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        if (direction == Direction.UP) {
            return new ImageIcon("assets\\images\\iTankU.png").getImage();
        }
        if (direction == Direction.DOWN) {
            return new ImageIcon("assets\\images\\iTankD.png").getImage();
        }
        if (direction == Direction.LEFT) {
            return new ImageIcon("assets\\images\\iTankL.png").getImage();
        }
        if (direction == Direction.RIGHT) {
            return new ImageIcon("assets\\images\\iTankR.png").getImage();
        }
        if (direction == Direction.UP_LEFT) {
            return new ImageIcon("assets\\images\\iTankLU.png").getImage();
        }
        if (direction == Direction.DOWN_LEFT) {
            return new ImageIcon("assets\\images\\iTankLD.png").getImage();
        }
        if (direction == Direction.UP_RIGHT) {
            return new ImageIcon("assets\\images\\iTankLU.png").getImage();
        }
        if (direction == Direction.DOWN_RIGHT) {
            return new ImageIcon("assets\\images\\iTankRD.png").getImage();
        }
        return null;
    }

    public void move() {
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
        }
    }

    private void determineDirection() {
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) direction = Direction.UP;
        else if (dirs[1] && !dirs[2] && !dirs[3] && !dirs[0]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.LEFT;
        else if (dirs[3] && !dirs[0] && !dirs[2] && !dirs[1]) direction = Direction.RIGHT;

    }
}
