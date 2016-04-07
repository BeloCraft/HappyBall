/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.singletones;

import com.belocraft.gameplay.Gameplay;
import com.belocraft.physics.MainPhysics;
import com.belocraft.render.RenderesObject;

/**
 *
 * @author Eugene
 */
public class InitSingletones {
    
    public InitSingletones(Gameplay gameplay)
    {
        ManagerRender.managerRender = new RenderesObject();
        GameplayObject.gameplay = gameplay;
        ManagerPhysics.physics = new MainPhysics();
    }
}
