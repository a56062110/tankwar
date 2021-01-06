import object.Direction;
import object.GameObject;

import java.awt.*;

public class Bullet extends Tank{
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 10;
    }
    @Override
    public void draw(Graphics g){
        if (!alive){
            return;
        }
        move();
        collision();

        g.drawImage(image[direction.ordinal()],x,y,null);
    }


    public boolean collision() {
        boolean isCollision = collisionBound();

        if (!isCollision) {
            isCollision = collisionObject();
        }

        if (isCollision) {
            alive = false;
            return true;
        }

        return false;
    }

    @Override
    public boolean collisionObject() {
        boolean isCollision = false;
        for (GameObject object : TankGame.getGameClient().getGameObject()) {
            if (object instanceof Tank) {
                if (((Tank) object).isEnemy() == isEnemy()) {
                    continue;
                }
            }

            if (object != this && getRectangle().intersects(object.getRectangle())) {
                if (object instanceof Tank) {
                    ((Tank) object).getHurt(1);
                }
                isCollision = true;
            }
        }

        return isCollision;

    }
}
