import object.Direction;

import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {
    public EnemyTank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, true, image);
    }
    public void ai() {

        Random random = new Random();

        if (random.nextInt(20) == 1) {
            dirs = new boolean[4];

            if (random.nextInt(2) == 1) {
                return;
            }
            getNewDirection();
        }
        if (random.nextInt(50) == 1){
            superFire();
        }

    }
    private void superFire() {
        for (Direction direction : Direction.values()) {
            Bullet bullet = new Bullet(x + (width - GameClient.getBulletImage()[0].getWidth(null)) / 2,
                    y + (height - GameClient.getBulletImage()[0].getHeight(null)) / 2, direction,
                    enemy, GameClient.getBulletImage());

            TankGame.getGameClient().addGameObject(bullet);
        }

    }

    private void getNewDirection() {
        dirs = new boolean[4];

        Random random = new Random();

        int dir = random.nextInt(Direction.values().length);

        if (dir <= Direction.RIGHT.ordinal()) {
            dirs[dir] = true;
        } else {
            if (dir == Direction.UP_LEFT.ordinal()) {
                dirs[0] = true;
                dirs[2] = true;
            } else if (dir == Direction.UP_RIGHT.ordinal()) {
                dirs[0] = true;
                dirs[3] = true;
            } else if (dir == Direction.DOWN_RIGHT.ordinal()) {
                dirs[1] = true;
                dirs[3] = true;
            } else if (dir == Direction.DOWN_LEFT.ordinal()) {
                dirs[1] = true;
                dirs[2] = true;
            }
        }
    }

    @Override
    public void draw(Graphics g){
        ai();
        super.draw(g);
    }
    @Override
    public boolean collision() {
        if(super.collision()){
            getNewDirection();
            return true;
        }

        return false;
    }
}
