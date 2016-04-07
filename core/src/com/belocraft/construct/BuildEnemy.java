/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.construct;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.belocraft.models.Enemy;
import com.belocraft.singletones.GameplayObject;
import com.belocraft.singletones.ManagerRender;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Eugene
 */
public class BuildEnemy {
    
    public void buildEnemy(Maze maze, int[][] map, int percent)
    {
        int x = 0;
        int y = 0;

        Random rnd = new Random();
        ArrayList<Enemy> enemys = new ArrayList<Enemy>();

        for (int j = 1; j < maze.gethCount() - 1; j++) {
            for (int i = 1; i < maze.getwCount() - 1; i++) {
                if (map[i][j] == 1) {
                    if (rnd.nextInt((i < 10 && j < 10)? percent*10:percent) < 1) {
                        Pixmap pixCricle = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
                        pixCricle.setColor(Color.MAGENTA);
                        pixCricle.fillCircle(10, 10, 10);
                        Enemy objRender
                                = new Enemy(i * 25, j * 25,
                                        new Texture(pixCricle), map, maze);
                        pixCricle.dispose();
                        ManagerRender.managerRender.addObject(objRender);
                        GameplayObject.gameplay.addObjectForUpdate(objRender);
                        enemys.add(objRender);
                    }
                }
            }
        }
    }
}
