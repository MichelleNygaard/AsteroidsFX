package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    private Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }


    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-10, -10, 15, 5, -10, 10);
        enemyShip.setX(random.nextInt(gameData.getDisplayWidth()));
        enemyShip.setY(random.nextInt(gameData.getDisplayHeight()));
        enemyShip.setRadius(15);

        return enemyShip;
    }

}