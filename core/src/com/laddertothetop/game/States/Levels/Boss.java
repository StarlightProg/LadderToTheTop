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
import com.laddertothetop.game.Sprites.Fireball;
import com.laddertothetop.game.Sprites.Player;
import com.laddertothetop.game.States.GameStateManager;
import com.laddertothetop.game.States.State;

public class Boss extends State {

    Texture bg,tex1,tex11,tex2,tex22;
    Texture perecladina1,perecladina2;

    Texture hpfull,hp2,hp1,hp0,boss;
    Rectangle bossrect;
    BtnDash btndash;

    Rectangle rect1,rect11,rect2,rect22,recttop;
    Rectangle perecladinarect1,perecladinarect2;
    Rectangle previousloc;

    DrawTexture painter;
    BtnTouch btnt;
    Player player;
    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnAction btna;
    BtnAttack btnat;
    BtnDash btnd;

    boolean updateFireball = true;
    int i = 0;
    int l =0;

    Fireball fireball;

    int k=0;

    int BossHealth = 3;

    public Boss(GameStateManager gms, float x, float y,int AmountHp,int money) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

        painter = new DrawTexture();

        btnj = new BtnJump();
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btna = new BtnAction();
        btnat = new BtnAttack();
        btnt = new BtnTouch();
        btndash = new BtnDash();

        previousloc = new Rectangle(0,0,1,500);

        hpfull = new Texture("bossfullhp.png");
        hp0 =new Texture("boss0hp.png");
        hp1 =new Texture("boss1hp.png");
        hp2 =new Texture("boss2hp.png");
        boss = new Texture("Boss.png");

        bg = new Texture("background.png");
        tex1 = new Texture("5up.png");
        tex11 = new Texture("5up.png");
        tex2 = new Texture("shopwall.png");
        tex22 = new Texture("shopwall.png");
        perecladina1 = new Texture("perekladina.png");
        perecladina2 = new Texture("perekladina.png");

        rect1 = new Rectangle();
        rect2 = new Rectangle();
        rect22 = new Rectangle();
        rect11 = new Rectangle();
        recttop = new Rectangle();
        perecladinarect1 = new Rectangle();
        perecladinarect2 = new Rectangle();

        bossrect = new Rectangle((LadderToTheTop.WIDTH/2)-(boss.getWidth())-(tex22.getWidth()/2)-20, tex1.getHeight()/2,boss.getWidth(),boss.getHeight());

        btnt = new BtnTouch();

        player = new Player(x,y,AmountHp,gms,money);

        painter.drawrectanglebottom(perecladinarect1,perecladina1,(LadderToTheTop.WIDTH/4)-(perecladina1.getWidth()/4)-120, 120);
        painter.drawrectanglebottom(perecladinarect2,perecladina2,(LadderToTheTop.WIDTH/4)-(perecladina2.getWidth()/4)-120, 210);
        painter.drawrectanglebottom(rect1,tex1,0,0);
        painter.drawrectangleleft(rect2,tex2,0,0);
        painter.drawrectangleright(rect22,tex22,(LadderToTheTop.WIDTH/2) - tex22.getWidth()/2,0);
        painter.drawrectangletop(recttop,tex11,0,(LadderToTheTop.HEIGHT/2)-tex11.getHeight()/2);

        fireball = new Fireball(-1000,(tex1.getHeight()/2)+30,player);
        //(LadderToTheTop.WIDTH/2)-(boss.getWidth())-(tex22.getWidth()/2)-20-boss.getWidth(),tex1.getHeight()/2,player
    }

    @Override
    protected void handleInput() {
        btnt.touch(player,camera);
    }

    @Override
    public void update(float dt) {
        player.collidesbottom(rect1);
        player.collidesleft(rect2);
        player.collidesright(rect22);
        player.collidesbottom(perecladinarect1);
        player.collidesbottom(perecladinarect2);
        player.collidestop(recttop);

        if (k==0&&player.getPlayerRect().overlaps(previousloc)){
            gms.set(new Fifth(gms, (LadderToTheTop.WIDTH/2)-70, player.getPosition().y,player.getAmountHp(),player.getMoney(),true));
            System.out.println("created");
            k++;
            dispose();
        }

        if (BossHealth == -1){
            boss.dispose();
            bossrect.setY(-1000);
            rect2.setY(-1000);
        }

        handleInput();
        player.update(dt);
        fireball.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(bg,0,0,bg.getWidth()/2,bg.getHeight()/2);

        painter.drawtexture(sb,tex1,0,0);
        painter.drawtexture(sb,tex11,0,(LadderToTheTop.HEIGHT/2)-tex11.getHeight()/2);
        if (BossHealth != -1){
            painter.drawtexture(sb,tex2,0,0 );
            rect2.setY(-1000);
        }
        painter.drawtexture(sb,tex22,(LadderToTheTop.WIDTH/2)-tex22.getWidth()/2,0 );
        painter.drawtexture(sb,perecladina1, (LadderToTheTop.WIDTH/4)-(perecladina1.getWidth()/4)-120, 120);
        painter.drawtexture(sb,perecladina2, (LadderToTheTop.WIDTH/4)-(perecladina2.getWidth()/4)-120, 210);
        if (BossHealth == -1){
            btndash.draw(sb);
        }
    if (BossHealth!=-1) {
        sb.draw(boss, (LadderToTheTop.WIDTH / 2) - (boss.getWidth()) - (tex22.getWidth() / 2) - 20, tex1.getHeight() / 2, boss.getWidth(), boss.getHeight());
    }



        //   sb.draw(spikes,rect3.getX()+rect3.getWidth(),rect3.getY()+(rect3.getHeight()/2)-100,10,400);
        player.draw(sb);
        fireball.draw(sb);
        btnj.draw(sb);
        btnl.draw(sb);
        btnr.draw(sb);
        btnat.draw(sb);

      //  System.out.println(BossHealth);

        switch (BossHealth){
            case 3 : painter.drawtexture(sb,hpfull,175,(LadderToTheTop.HEIGHT/2)-(hpfull.getHeight()/2) - 15); break;
            case  2  :
                if (updateFireball) {
                    fireball.setPosition((LadderToTheTop.WIDTH / 2) - (boss.getWidth()) - (tex22.getWidth() / 2) - 20 - boss.getWidth());
                }
                updateFireball=false;

                painter.drawtexture(sb,hp2,175,(LadderToTheTop.HEIGHT/2)-(hpfull.getHeight()/2) - 15);
                break;
            case 1 :
                if (i==0){
                    updateFireball = true;
                }
                i=1;
        //        fireball = new Fireball((LadderToTheTop.WIDTH/2)-(boss.getWidth())-(tex22.getWidth()/2)-20-boss.getWidth(),tex1.getHeight()/2,player);
                if (updateFireball) {
                    fireball.setPosition((LadderToTheTop.WIDTH / 2) - (boss.getWidth()) - (tex22.getWidth() / 2) - 20 - boss.getWidth());
                }
                updateFireball = false;
                painter.drawtexture(sb,hp1,175,(LadderToTheTop.HEIGHT/2)-(hpfull.getHeight()/2) - 15);
                break;
            case 0 :
                if (l==0){
                    updateFireball = true;
                }
                l=1;
                if (updateFireball) {
                    //      fireball = new Fireball((LadderToTheTop.WIDTH/2)-(boss.getWidth())-(tex22.getWidth()/2)-20-boss.getWidth(),tex1.getHeight()/2,player);
                    fireball.setPosition((LadderToTheTop.WIDTH / 2) - (boss.getWidth()) - (tex22.getWidth() / 2) - 20 - boss.getWidth());
                }
                updateFireball = false;
                painter.drawtexture(sb,hp0,175,(LadderToTheTop.HEIGHT/2)-(hpfull.getHeight()/2) - 15);
                break;
        }

        player.TakeDamage(bossrect);

        BossHealth = player.EnemyDamageBoss(bossrect,BossHealth);


        btnat.touch(player,camera,sb);

        sb.end();
    }

    @Override
    public void dispose() {

    }
    public int getBosssHealth(){
        return BossHealth;
    }
    public void setBosssHealth(int hp){
        BossHealth = hp;
    }
}
