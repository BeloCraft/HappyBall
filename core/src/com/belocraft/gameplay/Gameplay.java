/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.gameplay;

import com.belocraft.render.ObjectToRender;
import com.belocraft.models.Player;
import com.belocraft.inputs.KeyboardInputProcessor;
import com.belocraft.models.Enemy;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.belocraft.construct.MainBuildLevel;
import com.belocraft.construct.Maze;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Eugene
 */
public class Gameplay {

    
    private final ArrayList<ObjectToRender> updateObj;
    private final int wScreen;
    private final int hScreen;

    private int[][] map;
    private Boolean gameOver = false;
    private Boolean canReolad = false;

    public Boolean getCanReolad() {
        return canReolad;
    }

     public void setCanReolad(Boolean t) {
        this.canReolad = t;
    }
    public Boolean getGameOver() {
        return gameOver;
    }
    private Player player;
    private ArrayList<Enemy> enemys;
    private Maze maze;    
    private ObjectToRender lose;
    private final int enemy;
    private final MainBuildLevel builderLevel;

    public Gameplay(int wScreen, int hScreen, int enemy, MainBuildLevel builderLevel) { 
        this.updateObj = new ArrayList<ObjectToRender>();
        this.wScreen = wScreen;
        this.hScreen = hScreen;
        this.enemy = enemy;
        this.builderLevel = builderLevel;        
        
        this.builderLevel.executeConstruct(enemy);
    }

    public void update(float delta) {
        if (!this.gameOver) {
            for (ObjectToRender obj : updateObj) {
                obj.update(delta);
            }
        }
        checkCollision();        
    }    

    public void addObjectForUpdate(ObjectToRender obj)
    {        
        this.updateObj.add(obj);
    }
    
    void checkCollision() {
       
    }

    void constructObj() {
        constructMaze();
        Pixmap pixCricle;
        pixCricle = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixCricle.setColor(Color.GOLD);
        pixCricle.fillCircle(10, 10, 10);
        Player player = new Player(25, 25, new Texture(pixCricle), map);
        this.player = player;
        managerRender.addObject(player);
        pixCricle.dispose();
        updateObj.add(player);
    }

    void constructMaze() {
        Maze maze = new Maze(25, 25, wScreen, hScreen);
        this.maze = maze;        
        map = maze.generate(50);
        constructEnemy(maze, map, enemy);

        for (int y = 0; y < maze.gethCount(); y++) {
            for (int x = 0; x < maze.getwCount(); x++) {
                if (map[x][y] == 0) {
                    Pixmap pixRect = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
                    pixRect.setColor(Color.GRAY);
                    pixRect.fillRectangle(0, 0, 25, 25);
                    ObjectToRender objRender
                            = new ObjectToRender(25 * x, 25 * y, new Texture(pixRect));
                    pixRect.dispose();
                    managerRender.addObject(objRender);
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
        managerRender.addObject(objRender);
    }

    void constructEnemy(Maze maze, int[][] map, int percent) {
        int x = 0;
        int y = 0;

        Random rnd = new Random();
        this.enemys = new ArrayList<Enemy>();

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
                        managerRender.addObject(objRender);
                        updateObj.add(objRender);
                        enemys.add(objRender);
                    }
                }
            }
        }
    }
}
