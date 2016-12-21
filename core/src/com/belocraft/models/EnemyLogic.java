package com.belocraft.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eugene on 21.12.2016.
 */
public class EnemyLogic {

    private EnemyState enemyState;
    private ArrayList<Direction> avalibleDirectionToMove;

    public EnemyLogic()
    {
        this.avalibleDirectionToMove = new ArrayList<Direction>();
    }

    public EnemyLogic resetAvalibleDirection()
    {
        avalibleDirectionToMove.clear();
        return this;
    }

    public  EnemyLogic addAvalibleDirection(Direction direction){
        avalibleDirectionToMove.add(direction);
        return this;
    }

    public EnemyState getCurrentStateEnemy()
    {
        return enemyState;
    }

    public EnemyLogic updateState(IObject detect)
    {
        if (detect.toString().equals("Player"))
        {
            enemyState = EnemyState.AttackPlayer;
            return this;
        }
        Random rnd = new Random();
        if (rnd.nextInt(10) == 1)
        {
            enemyState = EnemyState.Idle;
            return this;
        }
        enemyState = EnemyState.Move;
        return this;
    }
}
