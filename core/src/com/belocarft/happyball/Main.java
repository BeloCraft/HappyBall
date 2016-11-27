package com.belocarft.happyball;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.belocraft.render.RenderScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {

    private Stage stage;
    
    @Override
    public void create() {        

        setScreen(new RenderScreen(stage));
    }
}
