/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.belocraft.singletones.GameConstants;

/**
 *
 * @author Eugene
 */
public class Player extends Actor implements IObject {

    private Fixture collider;
    private Body body;

    private boolean isKeyDown_A;
    private boolean isKeyDown_D;
    private boolean isKeyDown_W;
    private boolean isKeyDown_S;
    private Texture t;

    public Player(float x, float y){
        super.setX(x / GameConstants.WORLD_SCALE);
        super.setY(y / GameConstants.WORLD_SCALE);
        super.setName("Player");
        Pixmap pixRect = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.BLUE);
        pixRect.fillCircle(Math.round(GameConstants.WALL_WIDTH/2F)-1,Math.round(GameConstants.WALL_HEIGHT/2F)-1,
                Math.round(GameConstants.WALL_WIDTH/2F));
        t = new Texture(pixRect);
        pixRect.dispose();
    }

    public void draw (Batch batch, float parentAlpha) {

        super.setX(body.getPosition().x);
        super.setY(body.getPosition().y);

        if (isKeyDown_W)
        {
            body.setLinearVelocity(new Vector2(body.getLinearVelocity().x,GameConstants.PLAYER_SPEED));
        }

        if (isKeyDown_A)
        {
            body.setLinearVelocity(new Vector2(-GameConstants.PLAYER_SPEED,body.getLinearVelocity().y));
        }

        if (isKeyDown_D)
        {
            body.setLinearVelocity(new Vector2(GameConstants.PLAYER_SPEED,body.getLinearVelocity().y));
        }

        if (isKeyDown_S)
        {
            body.setLinearVelocity(new Vector2(body.getLinearVelocity().x,-GameConstants.PLAYER_SPEED));
        }

        if (!isKeyDown_W && !isKeyDown_S)
        {
            body.setLinearVelocity(new Vector2(body.getLinearVelocity().x,0));
        }

        if (!isKeyDown_A && !isKeyDown_D)
        {
            body.setLinearVelocity(new Vector2(0,body.getLinearVelocity().y));
        }
        batch.draw(t, super.getX() * GameConstants.WORLD_SCALE, super.getY() * GameConstants.WORLD_SCALE,GameConstants.WALL_WIDTH,GameConstants.WALL_HEIGHT);
    }

    public Fixture initCollider(World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(GameConstants.WALL_WIDTH/2F,GameConstants.WALL_HEIGHT/2F));
        shape.setRadius((GameConstants.WALL_WIDTH/2)/GameConstants.WORLD_SCALE);
        collider = body.createFixture(shape,0);
        shape.dispose();
        body.setTransform(new Vector2(super.getX(),super.getY()),0);
        body.setUserData(this);
        return collider;
    }

    public Fixture getCollider()
    {
        return  collider;
    }

    public boolean keyDown(int keycode)
    {

        if (keycode == Input.Keys.A)
        {
            isKeyDown_A = true;
        }
        if (keycode == Input.Keys.D)
        {
            isKeyDown_D = true;
        }
        if (keycode == Input.Keys.W)
        {
            isKeyDown_W = true;
        }
        if (keycode == Input.Keys.S)
        {
            isKeyDown_S = true;
        }
        return false;
    }

    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.A)
        {
            isKeyDown_A = false;
        }
        if (keycode == Input.Keys.D)
        {
            isKeyDown_D = false;
        }
        if (keycode == Input.Keys.W)
        {
            isKeyDown_W = false;
        }
        if (keycode == Input.Keys.S)
        {
            isKeyDown_S = false;
        }
        return false;
    }

    @Override
    public void beginContact(Contact contact) {
        IObject objFirst = (IObject)contact.getFixtureA().getBody().getUserData();
        IObject objSecond = (IObject)contact.getFixtureB().getBody().getUserData();

        if ((objFirst == this || objSecond == this) &&
                (objFirst.toString().equals("Enemy") || objSecond.toString().equals("Enemy")))
        {
            body.getTransform().setPosition(new Vector2(GameConstants.WALL_WIDTH/GameConstants.WORLD_SCALE,
                    GameConstants.WALL_HEIGHT/GameConstants.WORLD_SCALE));
            super.setX(GameConstants.WALL_WIDTH/GameConstants.WORLD_SCALE);
            super.setY(GameConstants.WALL_HEIGHT/GameConstants.WORLD_SCALE);
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    body.setTransform(GameConstants.WALL_WIDTH/2F,GameConstants.WALL_HEIGHT/2F,0);
                }
            });

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public String toString()
    {
        return super.getName();
    }
}
