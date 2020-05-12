package com.laddertothetop.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ObjectMap;
import com.laddertothetop.game.LadderToTheTop;
import com.laddertothetop.game.States.Levels.First;
import com.laddertothetop.game.States.Levels.Second;

import java.util.HashMap;

public class GameOver extends State {

    public static final int BtnX = 160;
    public static final int BtnY = 200;
    public static final int WidthBtn = 320;
    public static final int HeightBtn = 65;

    private Texture bg,continuee,exit;
    private Texture continuees,exits;
    private Rectangle continueer,exitr,Touchrect;
    private MyInputProtector inputProtector;
    private Vector3 touchPos;
    int k=0;

    public GameOver(GameStateManager gms) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

        bg= new Texture("gameover.png");
        continuee =new Texture("continue.png");
        exit = new Texture("exit.png");
        continuees =new Texture("continueS.png");
        exits = new Texture("exitS.png");

        continueer = new Rectangle(BtnX,BtnY-35,WidthBtn,HeightBtn);
        exitr = new Rectangle(BtnX,BtnY-115,WidthBtn,HeightBtn);

        touchPos = new Vector3();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        inputProtector = new MyInputProtector(sb);
        Gdx.input.setInputProcessor(inputProtector);

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,0,0,LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);
        sb.draw(continuees,BtnX,BtnY-60,WidthBtn,HeightBtn);
        sb.draw(exits,BtnX,BtnY-140,WidthBtn,HeightBtn);
        if (Gdx.input.isTouched()) {  // в методе render
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            Touchrect = new Rectangle(touchPos.x,touchPos.y,1,1);
            if (Touchrect.overlaps(continueer)){
                sb.draw(continuee,BtnX,BtnY-60,WidthBtn,HeightBtn);
            }

            if (Touchrect.overlaps(exitr)){
                sb.draw(exit,BtnX,BtnY-140,WidthBtn,HeightBtn);
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }


    class MyInputProtector implements InputProcessor {
        SpriteBatch sb;


        public MyInputProtector(SpriteBatch sb){
            this.sb = sb;
        }

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if ((Touchrect.overlaps(continueer))&&(k==0)) {
                gms.set(new First(gms,200,21,4,false));
                k++;
            }
            if ((Touchrect.overlaps(exitr))&&(k==0)) {
                gms.set(new MainMenu(gms));
                k++;
            }
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
