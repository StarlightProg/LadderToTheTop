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
import com.laddertothetop.game.Sprites.EnemyCherv;
import com.laddertothetop.game.Sprites.Player;
import com.laddertothetop.game.States.GameStateManager;
import com.laddertothetop.game.States.State;

public class abyss extends State {
    Texture bg,tex1,tex2,spikes;
    Texture perecladina1;
    Rectangle rect11,rect3,rect4;//bottom
    Rectangle rect112,rect32,rect2; //left
    Rectangle rect33,rect42,rect22; // right
    Rectangle rectperec1;
    Rectangle rectspikes;
    Rectangle nextloc,previousloc;
    DrawTexture painter;
    BtnTouch btnt;
    Player player;
    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnAction btna;
    BtnAttack btnat;
    com.laddertothetop.game.Sprites.BtnDash btnDash;
    boolean BtnDash = false;


    int k = 0;

    public abyss(GameStateManager gms, float x, float y,int AmountHp,int money,boolean BtnDash) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

        painter = new DrawTexture();

        btnj = new BtnJump();
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btna = new BtnAction();
        btnat = new BtnAttack();
        btnt = new BtnTouch();

        btnDash = new BtnDash();

        this.BtnDash = BtnDash;

        bg = new Texture("background.png");
        tex1 = new Texture("3right.png");
        tex2 = new Texture("3right.png");

        spikes = new Texture("3spikes.png");
        perecladina1 = new Texture("perekladina.png");

        rect112 = new Rectangle();
        rect42 = new Rectangle();
        rect11= new Rectangle();
        rect4 = new Rectangle();
        rectperec1 = new Rectangle();

        previousloc = new Rectangle(0,0,1,720);
        nextloc = new Rectangle((LadderToTheTop.WIDTH/2),50,-1,500);

        rectspikes = new Rectangle(0,0,1280,45);

        btnt = new BtnTouch();



        player = new Player(x,y,AmountHp,gms,money);

        painter.drawrectanglebottom(rect11,tex1,0,0);
        painter.drawrectanglebottom(rect4,tex2,(LadderToTheTop.WIDTH/2)-tex2.getWidth()/2,0);
        painter.drawrectangleleft(rect112,tex1, 0 ,0);
        painter.drawrectangleright(rect42,tex2,(LadderToTheTop.WIDTH/2)-tex2.getWidth()/2,0);
        painter.drawrectanglebottom(rectperec1,perecladina1,((LadderToTheTop.WIDTH/4)-perecladina1.getWidth()/4)-90,(tex2.getHeight()/2) );

    }

    @Override
    protected void handleInput() { btnt.touch(player,camera); }

    @Override
    public void update(float dt) {
        player.collidesbottom(rect11);
        player.collidesbottom(rect4);
        player.collidesleft(rect112);
        player.collidesright(rect42);
        player.collidesbottom(rectperec1);

        if (player.getPlayerRect().overlaps(rectspikes)){
            player.falled();
        }


        if (k==0&&player.getPlayerRect().overlaps(previousloc)){
            gms.set(new Fourth(gms, (LadderToTheTop.WIDTH/2)-70, player.getPosition().y,player.getAmountHp(),player.getMoney(),BtnDash));
            System.out.println("created");
            k++;
            dispose();
        }
        if (k==0&&player.getPlayerRect().overlaps(nextloc)){
            gms.set(new SecretRoom(gms, 70, player.getPosition().y,player.getAmountHp(),player.getMoney(),BtnDash));
            System.out.println("create");
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

        // sb.draw(tex22,(LadderToTheTop.WIDTH/2)-tex2.getWidth()/2,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2,tex22.getWidth(),tex22.getHeight());


        painter.drawtexture(sb,spikes,0,0);
        painter.drawtexture(sb,tex1,0,0);
        painter.drawtexture(sb,tex2,(LadderToTheTop.WIDTH/2)-tex2.getWidth()/2,0);
        painter.drawtexture(sb,perecladina1, ((LadderToTheTop.WIDTH/4)-perecladina1.getWidth()/4)-90,(tex2.getHeight()/2));

        player.draw(sb);
        btnj.draw(sb);
        if (BtnDash==true){
            btnDash.draw(sb);
        }
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
