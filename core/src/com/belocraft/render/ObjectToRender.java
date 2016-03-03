/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author Eugene
 */
public class ObjectToRender {

    private float x;
    private float y;
    private Texture texture;
    private BitmapFont font;
    private String text;
    private String nameObject;

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public BitmapFont getBitmapFont() {
        return font;
    }

    public void setBitmapFont(BitmapFont font) {
        this.font = font;
    }    

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ObjectToRender(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public ObjectToRender(float x, float y, BitmapFont font, String text) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.font = font;
        this.text = text;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public void Update(float delta)
    {
        throw new UnsupportedOperationException("It is need override");
    }
}