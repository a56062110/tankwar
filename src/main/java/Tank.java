import object.Direction;
import object.GameObject;

import java.awt.*;
import java.util.List;

public class Tank extends GameObject {

    protected int speed;
    protected Direction direction;
    protected boolean enemy;
    protected boolean[] dirs = new boolean[4];
    protected int hp;

    public boolean isEnemy() {
        return enemy;
    }

    public Tank(int x, int y, Direction direction, Image[] image){
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
        collision();
    }

    public Direction getDirection() {
        return direction;
    }

    public void getHurt(int damage){
        if(--hp<=0){
            alive=false;
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

    public void fire(){
        TankGame.gameClient.addGameObject(
                new Bullet(x+width/2-GameClient.bulletImage[0].getWidth(null)/2,
                        y+height/2-GameClient.bulletImage[0].getHeight(null)/2,
                        direction,enemy,GameClient.bulletImage));
    }

    public boolean collision(){
        boolean isCollision = collisionBound();

        if (!isCollision) {
            isCollision = collisionObject();
        }

        return isCollision;
    }

    public boolean collisionBound() {
        boolean isCollision = false;
        if (x < 0) {
            x = 0;
            isCollision = true;
        } else if (x > TankGame.getGameClient().getScreenWidth() - width) {
            x = TankGame.getGameClient().getScreenWidth() - width;
            isCollision = true;
        }

        if (y < 0) {
            y = 0;
            isCollision = true;
        } else if (y > TankGame.getGameClient().getScreenHeight() - height) {
            y = TankGame.getGameClient().getScreenHeight() - height;
            isCollision = true;
        }

        return isCollision;
    }

    public boolean collisionObject() {
        boolean isCollision = false;
        for (GameObject object : TankGame.getGameClient().getGameObject()) {
            if (object != this && getRectangle().intersects(object.getRectangle())) {
                x = oldX;
                y = oldY;
                isCollision = true;
            }
        }

        return isCollision;
    }

}
