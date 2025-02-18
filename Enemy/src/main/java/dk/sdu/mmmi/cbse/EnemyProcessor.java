package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Random;

public class EnemyProcessor implements IEntityProcessingService {
    private Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);
            if (player != null) {
                double deltaX = player.getX() - enemy.getX();
                double deltaY = player.getY() - enemy.getY();
                double angle = Math.atan2(deltaY, deltaX);
                enemy.setRotation(Math.toDegrees(angle));
                double changeX = Math.cos(angle);
                double changeY = Math.sin(angle);
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }

            if (random.nextInt(100) < 5) {
                shoot(enemy, gameData, world);
            }
        }
    }

    private void shoot(Entity enemy, GameData gameData, World world) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        bullet.setX(enemy.getX());
        bullet.setY(enemy.getY());
        bullet.setRotation(enemy.getRotation());
        bullet.setRadius(1);

        double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
        double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
        bullet.setX(bullet.getX() + changeX * 10);
        bullet.setY(bullet.getY() + changeY * 10);

        world.addEntity(bullet);
    }
}
