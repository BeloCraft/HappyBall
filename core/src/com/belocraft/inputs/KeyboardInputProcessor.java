/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.belocarft.happyball.Main;
import java.util.ArrayList;

/**
 *
 * @author Eugene
 */
public class KeyboardInputProcessor implements InputProcessor {

    public static ArrayList<Integer> keyPressedIsNow = new ArrayList<Integer>();

    private Main main;
    private int enemy = 50;

    public KeyboardInputProcessor(Main main) {
        this.main = main;
    }

    @Override
    public boolean keyDown(int keycode) {
        /*        
        KEY: 29 - a
        KEY: 51 - w
        KEY: 47 - s
        KEY: 32 - d
        KEY: 46 - r
        KEY: 69 - -
        KEY: 81 - +
         */
        if (!KeyboardInputProcessor.keyPressedIsNow.contains(keycode)) {
            keyPressedIsNow.add(keycode);
        }
//Gdx.app.log("KEY", String.valueOf(keycode));
        if (keycode == 46) {
            main.reset(enemy);
        }
        if (keycode == 81){            
            main.reset(10);
        }
        if (keycode == 69){            
            main.reset(20);            
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (KeyboardInputProcessor.keyPressedIsNow.contains(keycode)) {
            keyPressedIsNow.remove((Integer) keycode);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }

}
