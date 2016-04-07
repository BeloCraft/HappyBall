package com.belocarft.happyball;

import com.belocraft.gameplay.Gameplay;
import com.belocraft.render.RenderesObject;
import com.belocraft.render.RenderScreen;
import com.belocraft.inputs.KeyboardInputProcessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.belocraft.singletones.InitSingletones;

public class Main extends Game {
       
    private int enemy = 50;
    private Gameplay gameplay;
       
    
    @Override
    public void create() {        
        InitSingletones singletones = new InitSingletones();
        RenderesObject toRenderObject = new RenderesObject();
        gameplay = new Gameplay(toRenderObject,975,675,this.enemy);
        Gdx.input.setInputProcessor(new KeyboardInputProcessor(this));
        setScreen(new RenderScreen(toRenderObject, gameplay));
    }
    
    public void reset(int enemy)
    {
        if (gameplay.getCanReolad())
        {
        this.enemy = enemy;
        create();
        gameplay.setCanReolad(false);
        }
        if (enemy == 10 || enemy == 20){
        this.enemy = enemy;
        create();    
        }
    }
}
