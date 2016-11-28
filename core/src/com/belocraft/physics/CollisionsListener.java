package com.belocraft.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.belocraft.models.IObject;

/**
 * Created by Eugene on 28.11.2016.
 */
public class CollisionsListener implements ContactListener {

    private Array<IObject> objectArray;

    public CollisionsListener()
    {
        this.objectArray = new Array<IObject>();
    }

    public void addObject(IObject obj)
    {
        this.objectArray.add(obj);
    }

    public void deleteObject(IObject obj)
    {
        objectArray.removeValue(obj,true);
    }

    @Override
    public void beginContact(Contact contact) {
        for (IObject obj : objectArray)
        {
            obj.beginContact(contact);
        }
    }

    @Override
    public void endContact(Contact contact) {
        for (IObject obj : objectArray)
        {
            obj.endContact(contact);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        for (IObject obj : objectArray)
        {
            obj.preSolve(contact,oldManifold);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        for (IObject obj : objectArray)
        {
            obj.postSolve(contact,impulse);
        }
    }
}
