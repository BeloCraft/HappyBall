package com.belocraft.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.belocraft.singletones.GameConstants;

/**
 * Created by Eugene on 27.11.2016.
 */
public class Wall extends Actor {

    Texture t;
    public Wall (float x, float y){
        super.setX(x);
        super.setY(y);
        Pixmap pixRect = new Pixmap(GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.BLACK);
        pixRect.fillRectangle(0, 0, GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT);
        t = new Texture(pixRect);
        pixRect.dispose();
    }

    public void draw (Batch batch, float parentAlpha) {
        batch.draw(t, super.getX(), super.getY(), GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT);
    }
}