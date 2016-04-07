/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.belocraft.models.Direction;
import com.badlogic.gdx.graphics.Texture;
import com.belocraft.construct.Maze;
import com.belocraft.render.ObjectToRender;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Eugene
 */
public class Enemy extends ObjectToRender
{
    private int[][] map;
    private Maze maze;
    private int countTick;
    private int lastPossible;
    private Random rnd;
    private Direction direct = Direction.none;
    
    public Enemy(float x, float y, Texture texture) {
        super(x, y, texture);
    }
    
    public Enemy(float x, float y, Texture texture, int[][] map, Maze maze) {
        super(x, y, texture);
        this.map = map;
        this.countTick = 0;
        this.rnd = new Random();
        this.maze = maze;
    }       
    
    @Override
    public void update(float delta)
    {
        //int lx = Math.round(super.getX()/25);
        //int ly = Math.round(super.getY()/25);
               
        ArrayList<Direction> posible = new ArrayList<Direction>();
try{
        if (map[Math.round(super.getX() / 25)][Math.round((super.getY() - 25 / 2 - 100 * delta) / 25)] != 0) {            
            posible.add(Direction.down);
        }

        if (map[Math.round(super.getX() / 25)][Math.round((super.getY() + 25 / 2 + 100 * delta) / 25)] != 0) {            
            posible.add(Direction.up);
        }

        if (map[Math.round((super.getX()-25/2 - 100*delta)/25)][Math.round(super.getY()/25)] != 0){            
            posible.add(Direction.left);
        }

        if (map[Math.round((super.getX()+25/2 + 100*delta)/25)][Math.round(super.getY()/25)] != 0){            
            posible.add(Direction.right);
        }
}catch(Exception e){}    
        
        if (!posible.contains(this.direct))
        {            
            int n = rnd.nextInt(posible.size());
            this.direct = posible.get(n);                          
        }                            
        
        if(countTick <= 0 && lastPossible != posible.size())
        {
            int n = rnd.nextInt(posible.size());
            this.direct = posible.get(n);
            this.countTick = rnd.nextInt(500);
            lastPossible = posible.size();                        
        }

        if (countTick > -10) countTick--;
        
        switch (direct) {
            case right:
                super.setX(super.getX() + 200 * delta);
                break;
            case left:
                super.setX(super.getX() - 200 * delta);
                break;
            case up:
                super.setY(super.getY() + 200 * delta);
                break;
            case down:
                super.setY(super.getY() - 200 * delta);
                break;
            default:
                break;
        }

    }
    
    @Override
    public void physics()
    {
        
    }
}
