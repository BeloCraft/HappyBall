package com.belocraft.construct;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.belocraft.models.Wall;
import com.belocraft.singletones.GameConstants;

/**
 * Created by Eugene on 27.11.2016.
 */
public class MainGeneratorStage {

    private Stage stage;

    public MainGeneratorStage(Stage stage)
    {
        this.stage = stage;
    }

    public void generate()
    {
        GeneratorMaze maze = new GeneratorMaze();
        int width = GameConstants.SCREEN_WIDTH/GameConstants.WALL_WIDTH;
        int height = GameConstants.SCREEN_HEIGHT/GameConstants.WALL_HEIGHT;
        int[][] map = maze.generate(width, height,20);
        Group walls = new Group();
        walls.setX(0);
        walls.setY(0);
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (map[i][j] == 0)
                {
                    walls.addActor(new Wall(i*GameConstants.WALL_WIDTH,j*GameConstants.WALL_HEIGHT));
                }
            }
        }
        stage.addActor(walls);
    }
}
