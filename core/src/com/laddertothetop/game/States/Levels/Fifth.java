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

public class Fifth extends State {
    Texture bg,tex1,tex11,tex2;
    Texture signshop,signboss;
    Texture perecladina1;
    Rectangle rect11,rect1;//bottom
    Rectangle rect112; // right
    Rectangle rectperec1;


    Rectangle shoploc,bossloc,previousloc;
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

    public Fifth(GameStateManager gms, float x, float y,int AmountHp,int money,boolean BtnDash) {
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
        tex1 = new Texture("3twosidesup.png");
        tex11 = new Texture("3sideleft.png");
        tex2 = new Texture("5up.png");
        perecladina1 = new Texture("perekladina.png");

        signboss = new Texture("signboss.png");
        signshop = new Texture("signshop.png");

        rect1 = new Rectangle();
        rect112 = new Rectangle();
        rect11= new Rectangle();
        rectperec1 = new Rectangle();
        btnDash = new BtnDash();

        this.BtnDash = BtnDash;

        shoploc = new Rectangle(0,50,1,500);
        bossloc = new Rectangle(LadderToTheTop.WIDTH/2,50,-1,500);
        previousloc = new Rectangle(0,0,1280,1);

        btnt = new BtnTouch();

        player = new Player(x,y,AmountHp,gms,money);

        painter.drawrectanglebottom(rect11,tex11,(LadderToTheTop.WIDTH/2)-(tex11.getWidth()/2),0);
        painter.drawrectanglebottom(rect1,tex1, 0 ,0);
        painter.drawrectangleright(rect112,tex11, (LadderToTheTop.WIDTH/2)-(tex11.getWidth()/2),0);
        painter.drawrectanglebottom(rectperec1,perecladina1,(LadderToTheTop.WIDTH/4)-perecladina1.getWidth()/4,(tex1.getHeight()/2)-30);

    }

    @Override
    protected void handleInput() { btnt.touch(player,camera); }

    @Override
    public void update(float dt) {
        player.collidesbottom(rect11);
        player.collidesbottom(rect1);
        player.collidesright(rect112);
        player.collidesbottom(rectperec1);



        if (k==0&&player.getPlayerRect().overlaps(previousloc)){
            gms.set(new Fourth(gms, player.getPosition().x, (LadderToTheTop.HEIGHT/2) - 70,player.getAmountHp(),player.getMoney(),BtnDash));
            System.out.println("created");
            k++;
            dispose();
        }
        if (k==0&&player.getPlayerRect().overlaps(shoploc)){
            gms.set(new Shop(gms, (LadderToTheTop.WIDTH/2)- 70 , player.getPosition().y,player.getAmountHp(),player.getMoney(),BtnDash));
            System.out.println("create");
            k++;
            dispose();
        }
        if (k==0&&player.getPlayerRect().overlaps(bossloc)){
            gms.set(new Boss(gms, 100, player.getPosition().y,player.getAmountHp(),player.getMoney()));
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
        sb.draw(signshop,100,(tex1.getHeight()/2) +100,signshop.getWidth()/2,signshop.getHeight()/2);
        sb.draw(signboss,(LadderToTheTop.WIDTH/2) - 100,(tex11.getHeight()/2) +100,signshop.getWidth()/2,signshop.getHeight()/2);

        // sb.draw(tex22,(LadderToTheTop.WIDTH/2)-tex2.getWidth()/2,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2,tex22.getWidth(),tex22.getHeight());

        painter.drawtexture(sb,tex1,0,0);
        painter.drawtexture(sb,tex11,(LadderToTheTop.WIDTH/2)-(tex11.getWidth()/2),0);
        painter.drawtexture(sb,tex2,0,(LadderToTheTop.HEIGHT/2)-tex2.getHeight()/2 );
        painter.drawtexture(sb,perecladina1, (LadderToTheTop.WIDTH/4)-perecladina1.getWidth()/4,(tex1.getHeight()/2)-30);


        //   sb.draw(spikes,rect3.getX()+rect3.getWidth(),rect3.getY()+(rect3.getHeight()/2)-100,10,400);

        player.draw(sb);
        if (BtnDash==true){
            btnDash.draw(sb);
        }
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
