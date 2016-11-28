/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
public class Player extends Actor {

    private Fixture collider;
    private Body body;

    boolean isKeyDown_A;
    boolean isKeyDown_D;
    boolean isKeyDown_W;
    boolean isKeyDown_S;

    public Player(float x, float y){
        super.setX(x);
        super.setY(y);
        super.setName("Player");
    }

    public void draw (Batch batch, float parentAlpha) {

        super.setX(body.getPosition().x*GameConstants.WORLD_SCALE);
        super.setY(body.getPosition().y*GameConstants.WORLD_SCALE);

        Gdx.app.log("cord",body.getLinearVelocity().toString());

        if (isKeyDown_W)
        {
            body.setLinearVelocity(new Vector2(body.getLinearVelocity().x,GameConstants.PLAYER_SPEED));
            Gdx.app.log("cord",GameConstants.PLAYER_SPEED + "");
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

        batch.setColor(1, 1, 1, parentAlpha);
        Pixmap pixRect = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.BLUE);
        pixRect.fillCircle(Math.round(GameConstants.WALL_WIDTH/2F)-1,Math.round(GameConstants.WALL_HEIGHT/2F)-1,
                Math.round(GameConstants.WALL_WIDTH/2F));
        Texture t = new Texture(pixRect);
        pixRect.dispose();
        batch.draw(t, super.getX(), super.getY(),GameConstants.WALL_WIDTH,GameConstants.WALL_HEIGHT);
    }

    public Fixture initCollider(World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(GameConstants.WALL_WIDTH/2,GameConstants.WALL_HEIGHT/2));
        shape.setRadius(GameConstants.WALL_WIDTH);
        collider = body.createFixture(shape,0);
        shape.dispose();
        body.setTransform(new Vector2(super.getX()/GameConstants.WORLD_SCALE,super.getY()/GameConstants.WORLD_SCALE),0);
        body.setUserData(super.getName());
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
}
