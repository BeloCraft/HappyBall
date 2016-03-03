/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.belocraft.inputs.KeyboardInputProcessor;
import com.belocraft.models.Direction;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.belocraft.render.ObjectToRender;

/**
 *
 * @author Eugene
 */
public class Player extends ObjectToRender {

    private int[][] maze;
    
    public Player(float x, float y, Texture texture) {
        super(x, y, texture);
    }
    
    public Player(float x, float y, Texture texture, int[][] maze) {
        super(x, y, texture);
        this.maze = maze;
    }

    public Player(float x, float y, Texture texture, BitmapFont font, String text) {
        super(x, y, font, text);
    }

    @Override    
    public void Update(float delta) {
        for (int str : KeyboardInputProcessor.keyPressedIsNow)
        {
            switch (str) {
                /*        
        KEY: 29 - a
        KEY: 51 - w
        KEY: 47 - s
        KEY: 32 - d
                 */

                case 29:
                    move(Direction.left,delta);
                    break;
                case 51:
                    move(Direction.up,delta);
                    break;
                case 47:
                    move(Direction.down,delta);
                    break;
                case 32:
                    move(Direction.right,delta);
                    break;
            }
        }
    }

    void move(Direction direction, float delta) {
        switch (direction) {
            case down:                
                if (maze[Math.round(super.getX()/25)][Math.round((super.getY()-25/2 - 100*delta)/25)] != 0)
                super.setY(super.getY() - 200*delta);
                break;
            case up:
                if (maze[Math.round(super.getX()/25)][Math.round((super.getY()+25/2 + 100*delta)/25)] != 0)
                super.setY(super.getY() + 200*delta);
                break;
            case left:
                if (maze[Math.round((super.getX()-25/2 - 100*delta)/25)][Math.round(super.getY()/25)] != 0)
                super.setX(super.getX() - 200*delta);
                break;
            case right:
                if (maze[Math.round((super.getX()+25/2 + 100*delta)/25)][Math.round(super.getY()/25)] != 0)
                super.setX(super.getX() + 200*delta);
                break;
        }
    }        
}
