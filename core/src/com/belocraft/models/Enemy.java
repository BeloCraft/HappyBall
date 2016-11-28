/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.belocraft.singletones.GameConstants;

/**
 *
 * @author Eugene
 */
public class Enemy extends Actor
{
    private Texture t;
    private Fixture collider;
    private Body body;

    public Enemy(float x, float y, World world)
    {
        super.setX(x / GameConstants.WORLD_SCALE);
        super.setY(y / GameConstants.WORLD_SCALE);
        Pixmap pixRect = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.MAGENTA);
        pixRect.fillCircle(Math.round(GameConstants.WALL_WIDTH/2F)-1,Math.round(GameConstants.WALL_HEIGHT/2F)-1,
                Math.round(GameConstants.WALL_WIDTH/2F));
        t = new Texture(pixRect);
        pixRect.dispose();
        super.setName("Enemy");

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(GameConstants.WALL_WIDTH/2F,GameConstants.WALL_HEIGHT/2F));
        shape.setRadius((GameConstants.WALL_WIDTH/2)/GameConstants.WORLD_SCALE);
        collider = body.createFixture(shape,0);
        shape.dispose();
        body.setTransform(new Vector2(super.getX(),super.getY()),0);
        body.setUserData(super.getName());
    }

    public void draw (Batch batch, float parentAlpha) {
        super.setX(body.getPosition().x);
        super.setY(body.getPosition().y);
        batch.draw(t, super.getX()*GameConstants.WORLD_SCALE, super.getY()*GameConstants.WORLD_SCALE,
                GameConstants.WALL_WIDTH,GameConstants.WALL_HEIGHT);
    }
}
