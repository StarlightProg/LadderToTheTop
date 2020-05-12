package com.laddertothetop.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    private Vector3 mouse;
    protected GameStateManager gms;

    public State(GameStateManager gms){
        this.gms=gms;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update (float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
