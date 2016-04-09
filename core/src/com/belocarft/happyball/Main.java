package com.belocarft.happyball;

import com.belocraft.gameplay.Gameplay;
import com.belocraft.render.RenderesObject;
import com.belocraft.render.RenderScreen;
import com.belocraft.inputs.KeyboardInputProcessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.belocraft.construct.MainBuildLevel;
import com.belocraft.singletones.InitSingletones;
import com.belocraft.singletones.ManagerRender;

public class Main extends Game {
       
    private int enemy = 50;
    private Gameplay gameplay;
    private InitSingletones singletones;
    
    @Override
    public void create() {        
        gameplay = new Gameplay(975,675,this.enemy, new MainBuildLevel());
        singletones = new InitSingletones(gameplay);                
        Gdx.input.setInputProcessor(new KeyboardInputProcessor(this));
        setScreen(new RenderScreen(ManagerRender.managerRender, gameplay));
        gameplay.executeConstructLevel();
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
