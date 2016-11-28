package com.belocraft.construct;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.belocraft.models.Enemy;
import com.belocraft.models.Finish;
import com.belocraft.models.Player;
import com.belocraft.models.Wall;
import com.belocraft.render.Level;
import com.belocraft.singletones.GameConstants;

import java.util.Random;

/**
 * Created by Eugene on 27.11.2016.
 */
public class MainGeneratorStage {

    private Level stage;

    public MainGeneratorStage(Level stage)
    {
        this.stage = stage;
    }

    public void generate(int enemyCount, World world)
    {
        GeneratorMaze maze = new GeneratorMaze();
        int width = GameConstants.SCREEN_WIDTH/GameConstants.WALL_WIDTH;
        int height = GameConstants.SCREEN_HEIGHT/GameConstants.WALL_HEIGHT;
        int[][] map = maze.generate(width, height,20);
        Group walls = new Group();
        Group enemys = new Group();
        enemys.setX(0);
        enemys.setY(0);
        walls.setX(0);
        walls.setY(0);
        Random rnd = new Random();
        int w = 0;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (map[i][j] == 0)
                {
                    walls.addActor(new Wall(i*GameConstants.WALL_WIDTH,j*GameConstants.WALL_HEIGHT));
                }else
                {
                    if (i > 5 && j > 5 && enemyCount > 0)
                    {
                        int l = 5;
                        if ((i > (width - width/6)) &&
                                (j > (height - height/6)))
                            l = Math.round(((i+j)/(width+height+0.0F))*100);
                        if (rnd.nextInt(100) <= l)
                        {
                            Enemy newEnemy = new Enemy(i*GameConstants.WALL_WIDTH,j*GameConstants.WALL_HEIGHT);
                            enemys.addActor(newEnemy);
                            enemyCount -= 1;
                        }
                    }
                }
            }
        }

        stage.addActor(walls);
        stage.addActor(enemys);

        Player player = new Player(GameConstants.WALL_WIDTH,GameConstants.WALL_HEIGHT);
        stage.addActor(player);
        player.initCollider(world);
        stage.setPlayer(player);

        Finish finish = new Finish(GameConstants.SCREEN_WIDTH-GameConstants.WALL_WIDTH*2,
                GameConstants.SCREEN_HEIGHT-GameConstants.WALL_HEIGHT*2);
        stage.addActor(finish);
    }
}
