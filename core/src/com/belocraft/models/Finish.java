/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.models;

import com.badlogic.gdx.graphics.Texture;
import com.belocraft.render.ObjectToRender;

/**
 *
 * @author Eugene
 */
public class Finish extends ObjectToRender{
    
    public Finish(float x, float y, Texture texture) {
        super(x, y, texture);
    }
        
    @Override
    public  void update(float delta)
    {
        
    }
    
     @Override
    public void physics()
    {
        
    }
}
