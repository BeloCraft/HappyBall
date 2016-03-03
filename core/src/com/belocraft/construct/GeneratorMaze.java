/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.construct;

import com.belocraft.mymath.Tuple;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Eugene
 */
public class GeneratorMaze {

    public int[][] generate(int wCount, int hCount, int countDestroy) {

        int[][] map = new int[wCount][hCount];
        int[][] mapIn = new int[wCount][hCount];

        for (int j = 1; j <= hCount; j++) {
            for (int i = 1; i <= wCount; i++) {
                if (j % 2 == 0) {
                    if (i % 2 == 0) {
                        map[i - 1][j - 1] = 1;
                    } else {
                        map[i - 1][j - 1] = 0;
                    }
                } else {
                    map[i - 1][j - 1] = 0;
                }
            }
        }

        Random rnd = new Random();
        Tuple<Integer, Integer> nowCell = new Tuple<Integer, Integer>(1, 1);

        mapIn[nowCell.x][nowCell.y] = -1;
        Stack<Tuple<Integer, Integer>> stack
                = new Stack<Tuple<Integer, Integer>>();
        while (allIn(mapIn, wCount, hCount)) {
            ArrayList<Tuple<Integer, Integer>> neight = inNeing(mapIn, nowCell.x, nowCell.y, wCount, hCount);
            if (!neight.isEmpty()) {
                stack.push(new Tuple<Integer, Integer>(nowCell.x, nowCell.y));
                int n = rnd.nextInt(neight.size());
                map[nowCell.x - (nowCell.x - neight.get(n).x) / 2][nowCell.y - (nowCell.y - neight.get(n).y) / 2] = 1;
                nowCell = new Tuple<Integer, Integer>(neight.get(n).x, neight.get(n).y);
                mapIn[neight.get(n).x][neight.get(n).y] = -1;
            } else if (!stack.isEmpty()) {
                nowCell = stack.pop();
            } else {
                Tuple<Integer, Integer> cell = returnRndCell(mapIn, rnd, wCount, hCount);
                if (cell == null) {
                    break;
                } else {
                    nowCell = cell;
                    mapIn[cell.x][cell.y] = -1;
                }
            }

        }
        return hard(map,countDestroy,wCount,hCount);
    }

    Tuple<Integer, Integer> returnRndCell(int[][] map, Random rnd, int wCount, int hCount) {
        ArrayList<Tuple<Integer, Integer>> free = new ArrayList<Tuple<Integer, Integer>>();
        for (int y = 0; y < hCount; y++) {
            for (int x = 0; x < wCount; x++) {
                if (map[x][y] == 0) {
                    free.add(new Tuple<Integer, Integer>(x, y));
                }
            }
        }
        if (free.isEmpty()) {
            return null;
        }
        int n = rnd.nextInt(free.size());
        return free.get(n);
    }

    Boolean allIn(int[][] map, int wCount, int hCount) {
        for (int y = 1; y < hCount - 1; y += 2) {
            for (int x = 1; x < wCount - 1; x += 2) {
                if (map[x][y] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    ArrayList<Tuple<Integer, Integer>> inNeing(int[][] map,
            int x, int y, int wCount, int hCount) {
        ArrayList<Tuple<Integer, Integer>> result
                = new ArrayList<Tuple<Integer, Integer>>();

        if (x > 1) {
            if (map[x - 2][y] == 0) {
                result.add(new Tuple<Integer, Integer>(x - 2, y));
            }
        }

        if (x < wCount - 2) {
            if (map[x + 2][y] == 0) {
                result.add(new Tuple<Integer, Integer>(x + 2, y));
            }
        }

        if (y > 1) {
            if (map[x][y - 2] == 0) {
                result.add(new Tuple<Integer, Integer>(x, y - 2));
            }
        }

        if (y < hCount - 2) {
            if (map[x][y + 2] == 0) {
                result.add(new Tuple<Integer, Integer>(x, y + 2));
            }
        }

        return result;
    }
    
    int[][] hard(int[][] map, int countDestroy, int wCount, int hCount)
    {        
        ArrayList<Tuple<Integer,Integer>> mapWall 
                = new ArrayList<Tuple<Integer, Integer>>();
        
        int[][] newMap = map;
        
        for(int y = 1; y < hCount-1; y++)
        {
            for (int x = 1; x < wCount-1; x++)
            {
                if (map[x][y] == 0)
                    mapWall.add(new Tuple<Integer, Integer>(x, y));
            }
        }
        Random rnd = new Random();
        for(int i = 0; i < mapWall.size(); i++)
        {
            if (rnd.nextInt(mapWall.size()) < countDestroy)
            {
                newMap[mapWall.get(i).x][mapWall.get(i).y] = 1;
            }
        }
        
        return newMap;
    }
    
}
