/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.construct;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.belocraft.models.Player;
import com.belocraft.singletones.GameplayObject;
import com.belocraft.singletones.ManagerRender;

/**
 *
 * @author Eugene
 */
public class MainBuildLevel {
    
    private final BuildMaze buildMaze;
    private Maze maze;
    
    public MainBuildLevel()
    {        
        this.buildMaze = new BuildMaze();
    }
    
    public void executeConstruct(int enemy)
    {        
        buildMaze.constructMaze(enemy);
        
        Pixmap pixCricle;
        pixCricle = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixCricle.setColor(Color.GOLD);
        pixCricle.fillCircle(10, 10, 10);
        Player player = new Player(25, 25, new Texture(pixCricle), maze);
        player.setNameObject("Player");
        ManagerRender.managerRender.addObject(player);
        pixCricle.dispose();
        GameplayObject.gameplay.addObjectForUpdate(player);
    }
}
