package com.laddertothetop.game.States.Levels;

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
import com.laddertothetop.game.States.GameStateManager;
import com.laddertothetop.game.States.State;


public class Shop extends State {

    Texture bg,tex1,tex11,tex2,apple,applecost;

    Rectangle rect1,rect2,rect22,applerect;
    Rectangle previousrect;

    DrawTexture painter;
    BtnTouch btnt;
    Player player;
    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnAction btna;
    BtnAttack btnat;
    BtnDash btnDash;
    boolean BtnDash = false;

    int k=0;
    int i=0;

    public Shop(GameStateManager gms, float x, float y,int AmountHp,int money,boolean BtnDash) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

        painter = new DrawTexture();

        btnj = new BtnJump();
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btna = new BtnAction();
        btnat = new BtnAttack();
        btnt = new BtnTouch();
        btnDash  =new BtnDash();

        this.BtnDash = BtnDash;

        bg = new Texture("background.png");
        tex1 = new Texture("5up.png");
        tex11 = new Texture("5up.png");
        tex2 = new Texture("shopwall.png");

        apple = new Texture("apple.png");
        applecost = new Texture("applecost.png");
        applerect = new Rectangle(80,tex1.getHeight()/2,230,300);
        previousrect = new Rectangle(LadderToTheTop.WIDTH/2,50,-1,300);

        rect1 = new Rectangle();
        rect2 = new Rectangle();

        btnt = new BtnTouch();

        player = new Player(x,y,AmountHp,gms,money);

        painter.drawrectanglebottom(rect1,tex1,0,0);
        painter.drawrectangleleft(rect2,tex2,0,0);
    }

    @Override
    protected void handleInput() {
        btnt.touch(player,camera);
        if (i==1) {
            btnt.isActiveJump(true);
            k=0;
        }
        else {
            btnt.isActiveJump(false);
        }}

    @Override
    public void update(float dt) {
        player.collidesbottom(rect1);
        player.collidesleft(rect2);

        if (k==0&&player.getPlayerRect().overlaps(previousrect)){
            gms.set(new Fifth(gms, 40, player.getPosition().y,player.getAmountHp(),player.getMoney(), BtnDash));
            System.out.println("created");
            k++;
            dispose();
        }

        handleInput();
        player.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(bg,0,0,bg.getWidth()/2,bg.getHeight()/2);

        sb.draw(apple,150,tex1.getHeight()/2,apple.getWidth(),apple.getHeight());

        painter.drawtexture(sb,tex1,0,0);
        painter.drawtexture(sb,tex11,0,(LadderToTheTop.HEIGHT/2)-tex11.getHeight()/2);
        painter.drawtexture(sb,tex2,0,0 );
        sb.draw(applecost,170,(tex1.getHeight()/2)-20,applecost.getWidth()/2,applecost.getHeight()/2);


        //   sb.draw(spikes,rect3.getX()+rect3.getWidth(),rect3.getY()+(rect3.getHeight()/2)-100,10,400);

        player.draw(sb);
        btnj.draw(sb);
        btnl.draw(sb);
        btnr.draw(sb);
        btnat.draw(sb);
        if (BtnDash==true){
            btnDash.draw(sb);
        }

        if (player.getPlayerRect().overlaps(applerect)){
            btna.draw(sb);
            if (btna.BuyApple(camera,player.getMoney())&&player.getAmountHp()<4){
                player.setAmountHp(player.getAmountHp()+1);
                if ((player.getMoney()-10)>=0) {
                    player.setMoney(player.getMoney() - 10);
                }
            }
            i=0;
        }
        else {
            i=1;
        }

        btnat.touch(player,camera,sb);

        sb.end();

    }

    @Override
    public void dispose() {

    }
}
