/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.render;

import java.util.ArrayList;

/**
 *
 * @author Eugene
 */
public class RenderesObject {
    
    ArrayList<ObjectToRender> listObjRender = new ArrayList<ObjectToRender>();
    
    public void addObject(ObjectToRender obj)
    {
        this.listObjRender.add(obj);
    }
    
    public void removeObject(ObjectToRender obj)
    {
        this.listObjRender.remove(obj);
    }
    
    public void removeObject(int index)
    {
        this.listObjRender.remove(index);
    }
    
    public ArrayList<ObjectToRender> getCollection()
    {
        return this.listObjRender;
    }
}
