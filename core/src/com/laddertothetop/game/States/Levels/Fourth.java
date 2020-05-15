package com.laddertothetop.game.States.Levels;

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

public class Fourth extends State {
    Texture bg,tex1,tex2,tex3,tex4,tex22,spikes;
    Texture perecladina1,perecladina2;
    Rectangle rect11,rect3,rect4;//bottom
    Rectangle rect112,rect32,rect2; //left
    Rectangle rect33,rect42,rect22; // right
    Rectangle rectperec1,rectperec2;
    Rectangle rectspikes;
    Rectangle nextlocup,nextlocdown,previousloc;
    DrawTexture painter;
    BtnTouch btnt;
    Player player;
    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnAction btna;
    BtnAttack btnat;

    EnemyCherv enemyCherv;
    Rectangle chervleft,chervright;

    int k = 0;

    public Fourth(GameStateManager gms, float x, float y,int AmountHp,int money) {
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
        tex2 = new Texture("3twosidesup.png");
        tex22 = new Texture("3sideleft.png");
        tex3 = new Texture("3middle.png");
        tex4 = new Texture("3right.png");

        spikes = new Texture("3spikes.png");
        perecladina1 = new Texture("perekladina.png");
        perecladina2 = new Texture("perekladina.png");

        rect112 = new Rectangle();
        rect32 = new Rectangle();
        rect33 = new Rectangle();
        rect42 = new Rectangle();
        rect11= new Rectangle();
        rect3 = new Rectangle();
        rect4 = new Rectangle();
        rect2 = new Rectangle();
        rect22 = new Rectangle();
        rectperec1 = new Rectangle();
        rectperec2 = new Rectangle();

        nextlocdown = new Rectangle(LadderToTheTop.WIDTH/2,50,-1,500);
        nextlocup = new Rectangle(0,LadderToTheTop.HEIGHT/2,LadderToTheTop.WIDTH/2,-1);
        previousloc = new Rectangle(0,0,1,720);

        rectspikes = new Rectangle(0,0,1280,45);

        btnt = new BtnTouch();

        player = new Player(x,y,AmountHp,gms,money);

        painter.drawrectanglebottom(rect11,tex1,0,0);
        painter.drawrectanglebottom(rect4,tex4,(LadderToTheTop.WIDTH/2)-tex4.getWidth()/2,0);
        painter.drawrectanglebottom(rect3,tex3,(LadderToTheTop.WIDTH/4)-tex3.getWidth()/4,0);
        painter.drawrectangleleft(rect112,tex1, 0 ,0);
        painter.drawrectangleleft(rect32,tex3, (LadderToTheTop.WIDTH/4)-tex3.getWidth()/4,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2);
        painter.drawrectangleright(rect33,tex3, (LadderToTheTop.WIDTH/4)-tex3.getWidth()/4,0);
        painter.drawrectangleright(rect42,tex4,(LadderToTheTop.WIDTH/2)-tex4.getWidth()/2,0);
        painter.drawrectangleleft(rect2,tex2,0,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2);
        painter.drawrectangleright(rect22,tex22,(LadderToTheTop.WIDTH/2)-tex22.getWidth()/2,(LadderToTheTop.HEIGHT/2)-tex22.getHeight()/2);
        painter.drawrectanglebottom(rectperec1,perecladina1,(LadderToTheTop.WIDTH/4)-perecladina1.getWidth()/4,(tex3.getHeight()/2) +60);
        painter.drawrectanglebottom(rectperec2,perecladina2,(LadderToTheTop.WIDTH/4)-perecladina2.getWidth()/4,(tex3.getHeight()/2) +150);

        chervright = new Rectangle(rect3.getX()+rect3.getWidth(),rect3.getY()+(rect3.getHeight()/2)-100,10,400);
        chervleft = new Rectangle(rect3.getX(),rect3.getY()+(rect3.getHeight()/2)-100,1,1000);


        enemyCherv = new EnemyCherv(rect3.getX()+(rect3.getWidth()/4),rect3.getY()+(rect3.getHeight()/2),player);

    }

    @Override
    protected void handleInput() {
        btnt.touch(player,camera);
    }

    @Override
    public void update(float dt) {
        player.collidesbottom(rect11);
        player.collidesbottom(rect3);
        player.collidesbottom(rect4);
        player.collidesbottom(rectperec1);
        player.collidesbottom(rectperec2);
        player.collidesleft(rect112);
        player.collidesleft(rect32);
        player.collidesright(rect22);
        player.collidesright(rect33);
        player.collidesright(rect42);

        enemyCherv.collides(chervleft,chervright);

        if (player.getPlayerRect().overlaps(rectspikes)){
            player.falled();
        }


        if (k==0&&player.getPlayerRect().overlaps(previousloc)){
            gms.set(new Third(gms, (LadderToTheTop.WIDTH/2)-70, player.getPosition().y,player.getAmountHp(),player.getMoney()));
            System.out.println("created");
            k++;
            dispose();
        }
        if (k==0&&player.getPlayerRect().overlaps(nextlocup)){
            gms.set(new Fifth(gms, player.getPosition().x, 30,player.getAmountHp(),player.getMoney()));
            System.out.println("create");
            k++;
            dispose();
        }
        if (k==0&&player.getPlayerRect().overlaps(nextlocdown)){
            gms.set(new abyss(gms, 10, player.getPosition().y,player.getAmountHp(),player.getMoney()));
            System.out.println("create");
            k++;
            dispose();
        }

        handleInput();
        player.update(dt);
        enemyCherv.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(bg,0,0,bg.getWidth()/2,bg.getHeight()/2);

       // sb.draw(tex22,(LadderToTheTop.WIDTH/2)-tex2.getWidth()/2,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2,tex22.getWidth(),tex22.getHeight());


        painter.drawtexture(sb,spikes,0,0);
        painter.drawtexture(sb,tex1,0,0);
        painter.drawtexture(sb,tex2,0,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2);
        painter.drawtexture(sb,tex22,(LadderToTheTop.WIDTH/2)-tex22.getWidth()/2,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2);
     //   painter.drawtexture(sb,tex22,(LadderToTheTop.WIDTH/2)-tex22.getWidth()/2,(LadderToTheTop.HEIGHT/2)-tex22.getHeight()/2);
        painter.drawtexture(sb,tex4,(LadderToTheTop.WIDTH/2)-tex4.getWidth()/2,0);
        painter.drawtexture(sb,tex3,(LadderToTheTop.WIDTH/4)-tex3.getWidth()/4,0);
        painter.drawtexture(sb,perecladina1, (LadderToTheTop.WIDTH/4)-perecladina1.getWidth()/4,(tex3.getHeight()/2) +60);
        painter.drawtexture(sb,perecladina2, (LadderToTheTop.WIDTH/4)-perecladina2.getWidth()/4,(tex3.getHeight()/2) +150);

     //   sb.draw(spikes,rect3.getX()+rect3.getWidth(),rect3.getY()+(rect3.getHeight()/2)-100,10,400);

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
}
