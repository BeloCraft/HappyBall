package com.belocraft.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.belocraft.models.Player;

/**
 * Created by Eugene on 28.11.2016.
 */
public class Level extends Stage{

    private Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    public Level(ScreenViewport screenViewport){
        super(screenViewport);
    }

    public boolean keyDown(int keycode)
    {
        player.keyDown(keycode);
        return false;
    }

    public boolean keyUp(int keycode)
    {
        player.keyUp(keycode);
        return false;
    }

    public void keyPressed(int keycode)
    {

    }
}
