package com.belocraft.models;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Eugene on 28.11.2016.
 */
public interface IObject {
    void beginContact(Contact contact);
    void endContact(Contact contact);
    void preSolve(Contact contact, Manifold oldManifold);
    void postSolve(Contact contact, ContactImpulse impulse);
}
