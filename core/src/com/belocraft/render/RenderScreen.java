/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.belocraft.gameplay.Gameplay;
import java.util.ArrayList;

/**
 *
 * @author Eugene
 */
public class RenderScreen implements Screen {

    private RenderesObject renderObjects;
    private Gameplay gameplay;
    private SpriteBatch batch;
    private BitmapFont font;
    private int lastQuentityFrame;
    private int tempQuentityFrame;
    private float tempDeltaFrame;
    private Boolean stopGame = false;

    public RenderScreen(RenderesObject renderObjects, Gameplay gameplay) {
        this.renderObjects = renderObjects;
        this.gameplay = gameplay;
        this.batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        this.lastQuentityFrame = 0;
        this.tempDeltaFrame = 0;
        this.tempQuentityFrame = 0;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        if (!stopGame) {
            gameplay.update(delta);
        }

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        ArrayList<ObjectToRender> objRender = renderObjects.getCollection();
        for (ObjectToRender obj : objRender) {
            if (obj.getBitmapFont() != null) {
               obj.getBitmapFont()
                            .draw(batch, obj.getText(), obj.getX(), obj.getY());

            } else {
                batch.draw(obj.getTexture(), obj.getX(), obj.getY());
            }
        }

        font.draw(batch, String.valueOf(lastQuentityFrame), 0, 675);

        batch.end();

        if (tempDeltaFrame >= 1) {
            lastQuentityFrame = tempQuentityFrame;
            tempDeltaFrame = 0;
            tempQuentityFrame = 0;
        }

        tempQuentityFrame++;
        tempDeltaFrame += delta;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }

}
