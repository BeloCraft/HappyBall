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

/**
 *
 * @author Eugene
 */
public class Player extends Actor {

    public void draw (Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, parentAlpha);

        Pixmap pixRect = new Pixmap(5, 5, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.BLUE);
        pixRect.fillRectangle(0, 0, 5, 5);
        Texture t = new Texture(pixRect);
        batch.draw(t, super.getX(), super.getY(), 50, 50);
    }
}
