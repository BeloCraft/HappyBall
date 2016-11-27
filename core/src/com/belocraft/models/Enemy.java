/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.belocraft.singletones.GameConstants;

/**
 *
 * @author Eugene
 */
public class Enemy extends Actor
{
    public Enemy(float x, float y)
    {
        super.setX(x);
        super.setY(y);
    }

    public void draw (Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, parentAlpha);
        Pixmap pixRect = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.MAGENTA);
        pixRect.fillCircle(Math.round(GameConstants.WALL_WIDTH/2F)-1,Math.round(GameConstants.WALL_HEIGHT/2F)-1,
                Math.round(GameConstants.WALL_WIDTH/2F));
        Texture t = new Texture(pixRect);
        batch.draw(t, super.getX(), super.getY(),GameConstants.WALL_WIDTH,GameConstants.WALL_HEIGHT);
    }
}
