package com.laddertothetop.game.States.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.laddertothetop.game.LadderToTheTop;
import com.laddertothetop.game.Sprites.BtnJump;
import com.laddertothetop.game.Sprites.BtnLeftMove;
import com.laddertothetop.game.Sprites.BtnRightMove;
import com.laddertothetop.game.Sprites.BtnTouch;
import com.laddertothetop.game.Sprites.Player;
import com.laddertothetop.game.States.CheckPoint;
import com.laddertothetop.game.States.GameStateManager;
import com.laddertothetop.game.States.State;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class First extends State {

    int k=0;
    int onetime=0;

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
//            if (k==0) {
//                gms.set(new Second(gms, 10, player.getPosition().y,player.getAmountHp(),true));
//                System.out.println("created");
//                k++;
//            }
            i=0;
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

    int i = 0;
    private MyInputProtector inputProtector;
    private Player player;
    Texture t1;
    Rectangle r1,r2,r3,r4,r5,nextloc;
    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnTouch btnt;
    CheckPoint firstcheckpoint;
    boolean haveSword;

    public First(GameStateManager gms, float x, float y, int AmountHp,boolean haveSword,int money) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

        firstcheckpoint = new CheckPoint();

        this.haveSword = haveSword;
//x 200 y 21
        player = new Player(x,y,AmountHp,gms,money);

        t1 = new Texture("firstlevel.png");

        btnj = new BtnJump();
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btnt = new BtnTouch();

        r1 = new Rectangle(0,0,60,LadderToTheTop.HEIGHT/2);
        r2 = new Rectangle(0,0,LadderToTheTop.WIDTH/2,(float)72.5);
        r3 = new Rectangle(434,(float)72.5,5,(float)30.5);
        r4 = new Rectangle(444,110,412,-2);
        r5 = new Rectangle((float) 433.5,205,413,5);
        nextloc = new Rectangle(LadderToTheTop.WIDTH/2,r4.getY(),-1,105);
    }

    @Override
    protected void handleInput() {
       btnt.touch(player,camera);
    }


    @Override
    public void update(float dt) {



        player.collidesbottom(r2);
        player.collidesbottom(r4);

        player.collidesleft(r1);
        player.collidesright(r3);

        player.collidestop(r5);

        if (k==0&&player.getPlayerRect().overlaps(nextloc)) {
            if (haveSword == true){
                gms.set(new Second(gms, 10, player.getPosition().y,player.getAmountHp(),true,player.getMoney()));
            }
            else {
                gms.set(new Second(gms, 10, player.getPosition().y,player.getAmountHp(),false,player.getMoney()));
            }

            System.out.println("created");
            k++;
            dispose();
        }

        handleInput();
        player.update(dt);
    }
    @Override
    public void render(SpriteBatch sb) {

        inputProtector = new MyInputProtector(sb);
        Gdx.input.setInputProcessor(inputProtector);

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

           sb.draw(t1,0,LadderToTheTop.HEIGHT-t1.getHeight(),t1.getWidth()/2,t1.getHeight()/2);

               if (onetime == 0) {
                   player.notHaveSword();
                   onetime++;
               }
               player.draw(sb);
           btnj.draw(sb);
           btnl.draw(sb);
           btnr.draw(sb);


        sb.end();
       // player.getPosition().x = 200; символ тупости
    }


    @Override
    public void dispose() {
        t1.dispose();
        player.dispose();
    }
}
