/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.gameplay;

import com.belocraft.render.ObjectToRender;
import com.belocraft.render.RenderesObject;
import com.belocraft.models.Player;
import com.belocraft.inputs.KeyboardInputProcessor;
import com.belocraft.mymath.Tuple;
import com.belocraft.models.Enemy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.belocraft.construct.Maze;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Eugene
 */
public class Gameplay {

    private RenderesObject managerRender;
    private ArrayList<ObjectToRender> updateObj;
    private int wScreen;
    private int hScreen;

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
    private int enemy;
    private ObjectToRender lose;

    public Gameplay(RenderesObject managerRender, int wScreen, int hScreen, int enemy) {
        this.managerRender = managerRender;
        this.updateObj = new ArrayList<ObjectToRender>();
        this.wScreen = wScreen;
        this.hScreen = hScreen;
        this.enemy = enemy;
        constructObj();
    }

    public void Update(float delta) {
        if (!this.gameOver) {
            for (ObjectToRender obj : updateObj) {
                obj.Update(delta);
            }
        }
        checkCollision();
        checkReset();        
    }
    int l = 0;

    void checkReset(){
        for (int str : KeyboardInputProcessor.keyPressedIsNow)
        {
            if (str == 46 && this.gameOver && lose != null)
            {
                player.setX(25);
                player.setY(25);
                this.gameOver = false;
                managerRender.removeObject(lose);
            }
            
            if (str == 46 && !this.gameOver)
            {
                this.canReolad = true;
            }
        }
    }
    
    void checkCollision() {
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
