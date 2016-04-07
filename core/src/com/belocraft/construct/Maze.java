/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.construct;

import com.belocraft.singletones.GameConstants;

/**
 *
 * @author Eugene
 */
public class Maze {
    
    private final int wCount;
    private final int hCount;   
    private int[][] map;
    private final GeneratorMaze generator;

    public int[][] getMap() {
        return map;
    }
        

    public Maze() {
        this.generator = new GeneratorMaze();
        this.wCount = GameConstants.SCREEN_WIDTH / GameConstants.WALL_WIDTH;
        this.hCount = GameConstants.SCREEN_HEIGHT / GameConstants.WALL_HEIGHT;                
    }

    public int getwCount() {
        return wCount;
    }

    public int gethCount() {
        return hCount;
    }

    public int[][] generate(int countDestroy) {
        this.map = this.generator.generate(wCount, hCount, countDestroy);
        return this.map;
    }          
}