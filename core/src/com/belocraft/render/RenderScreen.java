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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.belocraft.construct.MainGeneratorStage;
import com.belocraft.physics.CollisionsListener;


/**
 *
 * @author Eugene
 */
public class RenderScreen implements Screen {

    private Level stage;
    private final World world;

    public RenderScreen (Level stage)
    {
        this.stage = stage;
        this.world = new World(new Vector2(0,0),true);
        this.world.setContactListener(new CollisionsListener());
    }


    @Override
    public void show() {
        this.stage = new Level(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        MainGeneratorStage stageGenerator = new MainGeneratorStage(stage);
        stageGenerator.generate(30,world);
    }

    float deltaFrame = 0;
    int countFrame = 0;
    int lastFrame = 0;
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(Color.GRAY.r,Color.GRAY.g,Color.GRAY.b,Color.GRAY.a);
        stage.act(delta);
        stage.draw();
        world.step(delta, 2, 2);

        deltaFrame += delta;
        countFrame++;
        if (deltaFrame >= 1F)
        {
            Gdx.app.log("FPS",String.valueOf(lastFrame));
            lastFrame = countFrame;
            countFrame = 0;
            deltaFrame = 0;
        }
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width,height,true);
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
        stage.dispose();
    }
}
