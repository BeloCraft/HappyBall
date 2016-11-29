package com.belocraft.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.belocraft.singletones.GameConstants;

/**
 * Created by Eugene on 27.11.2016.
 */
public class Wall extends Actor implements IObject {

    private Texture t;
    private Body body;
    private Fixture collider;

    public Wall (float x, float y, World world){
        super.setX(x / GameConstants.WORLD_SCALE);
        super.setY(y / GameConstants.WORLD_SCALE);
        Pixmap pixRect = new Pixmap(GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT, Pixmap.Format.RGBA8888);
        pixRect.setColor(Color.BLACK);
        pixRect.fillRectangle(0, 0, GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT);
        t = new Texture(pixRect);
        pixRect.dispose();
        super.setName("Wall");

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((GameConstants.WALL_WIDTH/2)/GameConstants.WORLD_SCALE,
                (GameConstants.WALL_HEIGHT/2)/GameConstants.WORLD_SCALE);
        collider = body.createFixture(shape,0);
        shape.dispose();
        body.setTransform(new Vector2(super.getX()+GameConstants.WALL_WIDTH/2F,super.getY()+GameConstants.WALL_HEIGHT/2F),0);
        body.setUserData(this);
    }

    public void draw (Batch batch, float parentAlpha) {
        batch.draw(t, super.getX() * GameConstants.WORLD_SCALE, super.getY() * GameConstants.WORLD_SCALE, GameConstants.WALL_WIDTH, GameConstants.WALL_HEIGHT);
    }

    @Override
    public String toString()
    {
        return super.getName();
    }

    @Override
    public void beginContact(Contact contact) {

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
}