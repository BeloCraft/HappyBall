/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.belocraft.models.Enemy;
import com.belocraft.render.ObjectToRender;
import java.util.ArrayList;

/**
 *
 * @author Eugene
 */
public class MainPhysics {
    
    ArrayList<ObjectToRender> obj;
    
    public void addObjectToUpdater(ObjectToRender obj)
    {
        this.obj.add(obj);
    }
    
    public void update()
    {
         float xPlayer = player.getX();
        float yPlayer = player.getY();

        for (Enemy enemy : enemys) {
            if (Math.abs(enemy.getX() - xPlayer) < 25 / 2
                    && Math.abs(enemy.getY() - yPlayer) < 25 / 2) {
                if (!this.gameOver){
                BitmapFont font = new BitmapFont();
                font.setColor(Color.RED);                  
                font.getData().setScale(5);
                lose = 
                        new ObjectToRender(maze.gethCount()*25/2, maze.getwCount()*25/2, font, "You lose");
                managerRender.addObject(lose);
                this.canReolad = false;
                }
                this.gameOver = true;                
            }
        }

        if (Math.abs(xPlayer - (maze.getwCount() - 2) * 25) < 10
                && Math.abs(yPlayer - (maze.gethCount() - 2) * 25) < 10) {
            
            if (!this.gameOver) {
                lose = null;
                BitmapFont font = new BitmapFont();
                font.setColor(Color.GREEN);
                font.getData().setScale(5);
                ObjectToRender win
                        = new ObjectToRender(maze.gethCount() * 25 / 2,
                                maze.getwCount() * 25 / 2, font, "You win");
                managerRender.addObject(win);
            }
            this.gameOver = true;
            this.canReolad = true;
        }
    }
}
