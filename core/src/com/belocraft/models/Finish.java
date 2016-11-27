/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.belocraft.singletones.GameConstants;

/**
 *
 * @author Eugene
 */
public class Finish extends Actor{

    private Color color;
    boolean backColor = false;

    public Finish (float x, float y)
    {
        super.setX(x);
        super.setY(y);
        color = new Color(Color.BLUE);
    }

    public void act(float delta) {
        if (backColor) {
            if (color.r > 0.1F) {
                color.r -= 1 * delta;
                if (color.r < 0) color.r = 0.1F;
            } else {
                backColor = false;
            }
        } else {
            if (color.r < 1F) {
                color.r += 1 * delta;
                if (color.r > 1F) color.r = 1F;
            } else {
                backColor = true;
            }

        }
    }

    public void draw (Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, parentAlpha);
        Pixmap pixRect = new Pixmap(GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT, Pixmap.Format.RGBA8888);
        pixRect.setColor(color);
        pixRect.fillRectangle(0, 0, GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT);
        Texture t = new Texture(pixRect);
        batch.draw(t, super.getX(), super.getY(), GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT);
    }
}
