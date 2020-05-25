package com.laddertothetop.game.States.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.laddertothetop.game.LadderToTheTop;
import com.laddertothetop.game.Sprites.BtnAction;
import com.laddertothetop.game.Sprites.BtnAttack;
import com.laddertothetop.game.Sprites.BtnDash;
import com.laddertothetop.game.Sprites.BtnJump;
import com.laddertothetop.game.Sprites.BtnLeftMove;
import com.laddertothetop.game.Sprites.BtnRightMove;
import com.laddertothetop.game.Sprites.BtnTouch;
import com.laddertothetop.game.Sprites.DrawTexture;
import com.laddertothetop.game.Sprites.Player;
import com.laddertothetop.game.Sprites.StartNpc;
import com.laddertothetop.game.States.GameStateManager;
import com.laddertothetop.game.States.State;



public class Second extends State {
    int u=0;
    int k=0;
    int l=0;
    int i=1;
    int j=0;
    int onetime = 0;
    int n=0;//для переключения реплики
    int h=0;//для отслеживания когда реплики закончятся
    int kolTouchUp=1;//для переключения реплики
    private MyInputProtector inputProtector;
    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnTouch btnt;
    BtnAction btna;
    BtnAttack btnat;
    Texture bg,tex1,tex2,tex3,tex11,tex22,bgt,npc;
    Rectangle rect11,rect12,rect21,rect31,rect111,rect112,rect221,npcrect,nextloc,previousloc;
    DrawTexture painter;
    StartNpc np;
    boolean haveSwordd;
    BtnDash btnDash;
    boolean BtnDash = false;

    private Player player;

    public Second(GameStateManager gms, float x, float y, int AmountHp,boolean haveSword,int money,boolean BtnDash) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

        player = new Player(x,y,AmountHp,gms,money);

        btnDash = new BtnDash();

        this.BtnDash = BtnDash;

        np = new StartNpc();

        haveSwordd = haveSword;

        btnj = new BtnJump();
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btna = new BtnAction();
        btnat = new BtnAttack();
        painter = new DrawTexture();
        btnt = new BtnTouch();

        bg = new Texture("background.png");
        bgt = new Texture("background.png");
        tex1 = new Texture("1.png");
        tex2 = new Texture("3.png");
        tex3 = new Texture("2.png");
        tex11 = new Texture("1.png");
        tex22 = new Texture("3.png");
        npc = new Texture("npc1.png");

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

        npcrect = new Rectangle(((tex3.getWidth()/3)-npc.getWidth()/2)-50,tex3.getHeight()/2,100,100);
        nextloc = new Rectangle(LadderToTheTop.WIDTH/2,rect11.getY()+rect11.getHeight(),-20,105);
        previousloc = new Rectangle(0,rect11.getY()+rect11.getHeight(),1,105);

    }

    @Override
    protected void handleInput() {
        btnt.touch(player,camera);
//        btnl.touch(player,camera);
        if (i==1) {
            btnt.isActiveJump(true);
            k=0;
       }
        else {
            btnt.isActiveJump(false);
        }
//
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

        if (k==0&&player.getPlayerRect().overlaps(nextloc)) {
            gms.set(new Third(gms, 10, player.getPosition().y,player.getAmountHp(),player.getMoney(),BtnDash));
            System.out.println("created");
            k++;
            dispose();
        }
        if (k==0&&player.getPlayerRect().overlaps(previousloc)){
            if (haveSwordd == true){
                gms.set(new First(gms, (LadderToTheTop.WIDTH/2)-70, player.getPosition().y,player.getAmountHp(),true,player.getMoney(),BtnDash));
            }
            else {
                gms.set(new First(gms, (LadderToTheTop.WIDTH/2)-70, player.getPosition().y,player.getAmountHp(),false,player.getMoney(),BtnDash));
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

        sb.draw(bg,0,LadderToTheTop.HEIGHT-bg.getHeight(),bg.getWidth()/2,bg.getHeight()/2);

        painter.drawtexture(sb,tex1,0,0);
        painter.drawtexture(sb,tex2,0,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2);
        painter.drawtexture(sb,tex3,tex2.getWidth()/2,0);
        painter.drawtexture(sb,tex11,(tex2.getWidth()+tex3.getWidth())/2,0);
        painter.drawtexture(sb,tex22,(tex2.getWidth()+tex3.getWidth())/2,(LadderToTheTop.HEIGHT/2)-tex22.getHeight()/2);

        sb.draw(npc,(tex3.getWidth()/3)-npc.getWidth()/2,tex3.getHeight()/2);

        player.draw(sb);
        if (BtnDash==true){
            btnDash.draw(sb);
        }
        btnj.draw(sb);
        btnl.draw(sb);
        btnr.draw(sb);



        if (player.getPlayerRect().overlaps(npcrect)){
            btna.draw(sb);
            i=0;
        }
        else {
            i=1;
        }


        if (btna.TouchStarterNpc(np,camera,sb)&&player.getPlayerRect().overlaps(npcrect)){
            j=1;
        }

        if (j==1&&player.getPlayerRect().overlaps(npcrect)){
            np.action(camera,sb,n,h);
            h=np.action(camera,sb,n,h);
        }

        if (h==0){
            if(onetime==0) {
                player.notHaveSword();
                onetime++;
            }
        }

        if(h==1){
            btnat.draw(sb);
            btnat.touch(player, camera,sb);
            player.TakeSword(u);
            u++;
        }
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bgt.dispose();
        tex1.dispose();
        tex2.dispose();
        tex3.dispose();
        tex11.dispose();
        tex22.dispose();
        npc.dispose();

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
            if (j==1&&player.getPlayerRect().overlaps(npcrect)){
                if(kolTouchUp>=2) {
                    n++;
                }
                kolTouchUp++;
            }
//            if (u==0) {
//                gms.set(new Third(gms, 10, player.getPosition().y,player.getAmountHp(),player.getMoney()));
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
