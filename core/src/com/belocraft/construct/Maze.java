/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.construct;

import com.belocraft.mymath.Tuple;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Eugene
 */
public class Maze {

    private int wWall;
    private int hWall;
    private int wScreen;
    private int hScreen;
    private int wCount;
    private int hCount;   
    
    private GeneratorMaze generator;

    public Maze(int wWall, int hWall, int wScreen, int hScreen) {
        this.wCount = wScreen / wWall;
        this.hCount = hScreen / hWall;        
        this.generator = new GeneratorMaze();
    }

    public int getwCount() {
        return wCount;
    }

    public int gethCount() {
        return hCount;
    }

    public int[][] generate(int countDestroy) {
        return this.generator.generate(wCount, hCount, countDestroy);
    }
    
    
    /*public Tuple<Integer,Integer> completedMaze(int[][] map)
    {
        Stack<Tuple<Integer,Integer>> stack = new Stack<Tuple<Integer, Integer>>();
        int[][] mapF = new int[wCount][hCount];        
        Tuple<Integer,Integer> current = new Tuple<Integer, Integer>(1, 1);
        Random rnd = new Random();
        
        while(allIn(mapF))
        {
            ArrayList<Tuple<Integer, Integer>> n = inNeing(mapF, current.x, current.y);
            if (!n.isEmpty())
            {
                stack.push(new Tuple<Integer,Integer>(current.x,current.y));
                int nr = rnd.nextInt(n.size());
                current = n.get(nr);
                mapF[n.get(nr).x][n.get(nr).y] = -1;
            }else
            {
                if (stack.isEmpty())
                    return current;
                else
                current = stack.pop();
            }
        }
        return current;
    }*/

    void debug(int[][] map)
    {
        int free = 0;
         for (int y = 0; y < hCount; y++) {
            for (int x = 0; x < wCount; x++) {
                if (map[x][y] == 0) {
                    free++;
                }
            }
        }
        Gdx.app.log("MAZE", String.valueOf(free));
    }


}
