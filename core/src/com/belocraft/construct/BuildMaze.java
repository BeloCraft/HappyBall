/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.construct;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.belocraft.render.ObjectToRender;
import com.belocraft.singletones.GameConstants;
import com.belocraft.singletones.ManagerRender;

/**
 *
 * @author Eugene
 */
public class BuildMaze {
    
    private int[][] map;
    private BuildEnemy enemyBuilder;
    
    BuildMaze()
    {
        this.enemyBuilder = new BuildEnemy();
    }
    
    public Maze constructMaze(int enemy) {
        
        Maze maze = new Maze();        
        this.map = maze.generate(GameConstants.GENERATOR_DESTROY_WALL_QUANTITY);                
        enemyBuilder.buildEnemy(maze,map,enemy);

        for (int y = 0; y < maze.gethCount(); y++) {
            for (int x = 0; x < maze.getwCount(); x++) {
                if (map[x][y] == 0) {
                    Pixmap pixRect = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
                    pixRect.setColor(Color.GRAY);
                    pixRect.fillRectangle(0, 0, 25, 25);
                    ObjectToRender objRender
                            = new ObjectToRender(25 * x, 25 * y, new Texture(pixRect));
                    pixRect.dispose();
                    ManagerRender.managerRender.addObject(objRender);
                }
            }
        }

        Pixmap pixRect = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.BLUE);
        pixRect.fillRectangle(0, 0, 25, 25);
        ObjectToRender objRender
                = new ObjectToRender((maze.getwCount() - 2) * 25,
                        (maze.gethCount() - 2) * 25, new Texture(pixRect));
        pixRect.dispose();
        ManagerRender.managerRender.addObject(objRender);
        
        return maze;
    }
}
