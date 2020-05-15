package com.laddertothetop.game.States.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.laddertothetop.game.LadderToTheTop;
import com.laddertothetop.game.Sprites.BtnAction;
import com.laddertothetop.game.Sprites.BtnAttack;
import com.laddertothetop.game.Sprites.BtnJump;
import com.laddertothetop.game.Sprites.BtnLeftMove;
import com.laddertothetop.game.Sprites.BtnRightMove;
import com.laddertothetop.game.Sprites.BtnTouch;
import com.laddertothetop.game.Sprites.DrawTexture;
import com.laddertothetop.game.Sprites.EnemyCherv;
import com.laddertothetop.game.Sprites.Player;
import com.laddertothetop.game.States.GameStateManager;
import com.laddertothetop.game.States.State;

public class Third extends State {

    Texture bg,tex1,tex2,tex3,tex11,tex22;
    Rectangle rect11,rect12,rect21,rect31,rect111,rect112,rect221;
    Rectangle nextloc,previousloc;

    private MyInputProtector inputProtector;

    Player player;

    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnAction btna;
    BtnAttack btnat;
    BtnTouch btnt;

    int k = 0;
    int u =0;

    EnemyCherv enemyCherv;

    DrawTexture painter;

    public Third(GameStateManager gms, float x, float y,int AmountHp,int money) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

        painter = new DrawTexture();

        btnj = new BtnJump();
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btna = new BtnAction();
        btnat = new BtnAttack();
        btnt = new BtnTouch();

        bg = new Texture("background.png");
        tex1 = new Texture("1.png");
        tex2 = new Texture("3.png");
        tex3 = new Texture("2.png");
        tex11 = new Texture("1.png");
        tex22 = new Texture("3.png");

        rect11= new Rectangle();
        rect12= new Rectangle();
        rect21= new Rectangle();
        rect31= new Rectangle();
        rect111= new Rectangle();
        rect112= new Rectangle();
        rect221= new Rectangle();

        painter.drawrectanglebottom(rect31,tex3,tex2.getWidth()/2,0);
        painter.drawrectanglebottom(rect11,tex1,0,0);
        painter.drawrectanglebottom(rect111,tex11,(tex2.getWidth()+tex3.getWidth())/2,0);
        painter.drawrectangletop(rect21,tex2,0,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2);
        painter.drawrectangletop(rect221,tex22,(tex2.getWidth()+tex3.getWidth())/2,(LadderToTheTop.HEIGHT/2)-tex22.getHeight()/2);
        painter.drawrectangleleft(rect12,tex1,0,0);
        painter.drawrectangleright(rect112,tex11,(tex2.getWidth()+tex3.getWidth())/2,0);

        nextloc = new Rectangle(LadderToTheTop.WIDTH/2,rect11.getY()+rect11.getHeight(),-1,105);
        previousloc = new Rectangle(0,rect11.getY()+rect11.getHeight(),1,105);

        player = new Player(x,y,AmountHp,gms,money);
        enemyCherv = new EnemyCherv(rect31.getX()+(rect31.getWidth()/2)+150,rect31.getY()+(rect31.getHeight()/2),player);
    }

    @Override
    protected void handleInput() {
        btnt.touch(player,camera);
//        btnl.touch(player,camera);
//        btnj.touch(player, camera);
//        btnr.touch(player,camera);
    }

    @Override
    public void update(float dt) {
        player.collidesbottom(rect111);
        player.collidesbottom(rect11);
        player.collidesbottom(rect31);

        player.collidestop(rect21);
        player.collidestop(rect221);

        player.collidesleft(rect12);

        player.collidesright(rect112);

        enemyCherv.collides(rect12,rect112);

        if (k==0&&player.getPlayerRect().overlaps(nextloc)) {
            gms.set(new Fourth(gms, 10, player.getPosition().y,player.getAmountHp(),player.getMoney()));
            System.out.println("created");
            k++;
            dispose();
        }
        if (k==0&&player.getPlayerRect().overlaps(previousloc)){
            gms.set(new Second(gms, (LadderToTheTop.WIDTH/2)-70, player.getPosition().y,player.getAmountHp(),true,player.getMoney()));
            System.out.println("created");
            k++;
            dispose();
        }

        handleInput();
        player.update(dt);
        enemyCherv.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        inputProtector = new MyInputProtector(sb);
        Gdx.input.setInputProcessor(inputProtector);

        sb.setProjectionMatrix(camera.combined);

        sb.begin();

            sb.draw(bg,0,0,bg.getWidth()/2,bg.getHeight()/2);

            painter.drawtexture(sb,tex1,0,0);
            painter.drawtexture(sb,tex2,0,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2);
            painter.drawtexture(sb,tex3,tex2.getWidth()/2,0);
            painter.drawtexture(sb,tex11,(tex2.getWidth()+tex3.getWidth())/2,0);
            painter.drawtexture(sb,tex22,(tex2.getWidth()+tex3.getWidth())/2,(LadderToTheTop.HEIGHT/2)-tex22.getHeight()/2);

            enemyCherv.draw(sb);
            player.draw(sb);
            btnj.draw(sb);
            btnl.draw(sb);
            btnr.draw(sb);
            btnat.draw(sb);


            btnat.touch(player,camera,sb);

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
//            if (u==0) {
//                gms.set(new Fourth(gms, 10, player.getPosition().y,player.getAmountHp()));
//                System.out.println("created");
//                u++;
//            }
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




