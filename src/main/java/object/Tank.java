package object;

import javax.swing.*;
import java.awt.*;

public class Tank {

    private int x, y, speed;

    private Direction direction;

    private boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 5;

    }

    public boolean[] getDirs() {
        return dirs;
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
            return new ImageIcon("assets\\images\\iTankRU.png").getImage();
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
        g.drawImage(getImage(), x, y, null);
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
