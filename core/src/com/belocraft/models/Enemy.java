/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.belocraft.singletones.GameConstants;

import java.util.Random;

/**
 *
 * @author Eugene
 */
public class Enemy extends Actor implements IObject {
    private Texture t;
    private Fixture collider;
    private Body body;
    private Direction direction;
    private EnemyLogic enemyLogic;
    private World world;

    public Enemy(float x, float y, World world)
    {
        direction = Direction.down;
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
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(GameConstants.WALL_WIDTH/2F,GameConstants.WALL_HEIGHT/2F));
        shape.setRadius((GameConstants.WALL_WIDTH/2.1F)/GameConstants.WORLD_SCALE);
        collider = body.createFixture(shape,0);
        shape.dispose();
        body.setTransform(new Vector2(super.getX(),super.getY()),0);
        body.setUserData(this);

        enemyLogic = new EnemyLogic();
        this.world = world;
    }

    public void draw (Batch batch, float parentAlpha) {
        super.setX(body.getPosition().x);
        super.setY(body.getPosition().y);
        batch.draw(t, super.getX()*GameConstants.WORLD_SCALE, super.getY()*GameConstants.WORLD_SCALE,
                GameConstants.WALL_WIDTH,GameConstants.WALL_HEIGHT);

        if (direction == Direction.down)
        {
            body.setLinearVelocity(new Vector2(body.getLinearVelocity().x,-GameConstants.PLAYER_SPEED));
        }

        if (direction == Direction.up)
        {
            body.setLinearVelocity(new Vector2(body.getLinearVelocity().x,GameConstants.PLAYER_SPEED));
        }

        if (direction == Direction.left)
        {
            body.setLinearVelocity(new Vector2(-GameConstants.PLAYER_SPEED,body.getLinearVelocity().x));
        }

        if (direction == Direction.right)
        {
            body.setLinearVelocity(new Vector2(GameConstants.PLAYER_SPEED,body.getLinearVelocity().x));
        }

        world.rayCast(new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                Gdx.app.log("CAST",fixture.getBody().getUserData().toString());
                return 0;
            }
        }, new Vector2(this.getX()*GameConstants.WORLD_SCALE,this.getY()*GameConstants.WORLD_SCALE),
                new Vector2((this.getX()*GameConstants.WORLD_SCALE)-50,this.getY()*GameConstants.WORLD_SCALE));
    }


    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    /*    IObject objFirst = (IObject)contact.getFixtureA().getBody().getUserData();
        IObject objSecond = (IObject)contact.getFixtureB().getBody().getUserData();

        if ((objFirst == this || objSecond == this) &&
                (objFirst.toString().equals("Wall") || objSecond.toString().equals("Wall")))
        {
            Random rnd = new Random();
            int nDirection = rnd.nextInt(5);

            switch (nDirection)
            {
                case 0:
                    direction = Direction.down;
                    break;
                case 1:
                    direction = Direction.up;
                    break;
                case 2:
                    direction = Direction.left;
                    break;
                case 3:
                    direction = Direction.right;
                    break;
            }
        }*/
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
