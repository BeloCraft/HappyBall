package com.belocarft.happyball;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.belocraft.render.Level;
import com.belocraft.render.RenderScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {

    private Level stage;
    
    @Override
    public void create() {        

        setScreen(new RenderScreen(stage));
    }
}
