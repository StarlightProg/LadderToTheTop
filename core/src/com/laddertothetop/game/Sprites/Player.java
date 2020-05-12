package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.laddertothetop.game.LadderToTheTop;
import com.laddertothetop.game.States.GameOver;
import com.laddertothetop.game.States.GameStateManager;
import com.laddertothetop.game.States.Levels.Third;
import com.laddertothetop.game.States.State;

public class Player {
    Texture heartanimtex;
    TextureRegion heartanimcurrentFrame;

    BtnJump btnj;
    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnAttack btnat;

    private static int gravity=0;
    private Vector3 position,velosity;

    private Texture player,playerright,sword,hp,gorighttex,golefttex;
    private Animation goleftanim,gorightanim;

    private Texture first,two,three,four,five,six,seven,eight,nine,zero;

    boolean checkhp = false;
    boolean haveSword=true;
    boolean attack = false;
    boolean onetimeattack = true;
    boolean da = false,pizdaright=false,pizdaleft=false;
    boolean jumpactive= false;

    int AmountHp;
    int SideOfSword=0;
    int isGoRight = 0;
    int isGoLeft = 0;
    int startThread=1;
    int kolvoHp=0;
    int widthplayerwithoutsword=33;
    int widthplayerwithsword=61;

    long starttime=0;

    boolean tex1=false,tex2=false;

    int money = 0;
    Texture moneytex;

    private Rectangle rect,leftside,topside,bottomside,rightside;
    private Rectangle attackframe;

    Array<Texture> HpBar;
    GameStateManager gms;


    Texture attackrightrtex,attackleftrtex;
    TextureRegion attackcurrentFrameright,attackcurrentFrameleft;

    AnimationAttackThread aat;
    TakeDamageThread tdt;
    AnimationTakeDamagethread atdt;
    JumpThread jumpthread;

    public Player(float x, float y, int AmountHp,GameStateManager gms){
        this.gms=gms;

        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);

        HpBar = new Array<>();
        this.AmountHp = AmountHp;

        btnj = new BtnJump();
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btnat = new BtnAttack();


        moneytex = new Texture("coin2.png");
        zero = new Texture("0.png");
        first = new Texture("11.png");
        two = new Texture("22.png");
        three = new Texture("33.png");
        four = new Texture("4.png");
        five = new Texture("5.png");
        six = new Texture("6.png");
        seven = new Texture("7.png");
        eight = new Texture("8.png");
        nine = new Texture("9.png");


        heartanimtex = new Texture("heartanimfixed.png");
        heartanimcurrentFrame = new TextureRegion(heartanimtex);
        heartanimcurrentFrame.setRegion(0,0,51,45);

        attackrightrtex = new Texture("heroattackright.png");
        attackleftrtex = new Texture("heroattackleft.png");
        attackcurrentFrameright = new TextureRegion(attackrightrtex);
        attackcurrentFrameleft = new TextureRegion(attackleftrtex);
        attackcurrentFrameright.setRegion(0,0,177,93);
        attackcurrentFrameleft.setRegion(0,0,177,93);

        if (haveSword==true){
            gorighttex = new Texture("herogorightwithsword.png");
            gorightanim = new Animation(new TextureRegion(gorighttex), 7, 1f);
            golefttex = new Texture("herogoleftwithsword.png");
            goleftanim = new Animation(new TextureRegion(golefttex), 7, 1f);
            player = new Texture("herorightwithsword.png");
            playerright = new Texture("heroleftwithsword.png");
        }
        sword = new Texture("sword.png");
        hp = new Texture("heart.png");

        for (int i=0;i<4;i++){
            HpBar.add(hp);
            kolvoHp=i+1;
        }

        rect = new Rectangle(x,y,player.getWidth()/2,player.getHeight()/2);

        attackframe = new Rectangle(-100,-100,61/2,93/2);

        leftside = new Rectangle(x,y+10,5,player.getHeight()/2-20);
        rightside = new Rectangle(x+player.getWidth()/2,y+10,-5,player.getHeight()/2-20);
        topside = new Rectangle(x+10,y+player.getHeight()/2,player.getWidth()/2-20,-5);
        bottomside = new Rectangle(x+10,y,player.getWidth()/2-20,5);


    }

    public void setAmountHp(int AmountHp){
        this.AmountHp = AmountHp;
    }

    public int getAmountHp(){
        return AmountHp;
    }

    public Vector3 getPosition(){ return position; }

    public float getHeight(){ return player.getHeight(); }

    public float getWidth(){ return player.getWidth(); }

    public Rectangle getPlayerRect(){ return rect;}


    public void update(float dt){

        velosity.add(-50, gravity, 0);
        velosity.scl(dt/2);
        position.add(0, velosity.y, 0);
        velosity.scl(1 / (dt/2));

        rect.setPosition(position.x, position.y);

        leftside.setPosition(position.x, position.y);
        rightside.setPosition(position.x, position.y);
        topside.setPosition(position.x, position.y);
        bottomside.setPosition(position.x, position.y);

        gorightanim.update(dt);
        goleftanim.update(dt);

        rect.setWidth(player.getWidth()/2);

        if (AmountHp==0){
            gms.set(new GameOver(gms));
        }
    }

    public void collidesbottom(Rectangle re){

        if (re.overlaps(rect)) {
            gravity = -10;
            velosity.y = -30;
            position.y = re.y + re.height;
            this.refresh();
            jumpactive = false;
        }
    }

    public void collidesleft(Rectangle re){
        if (re.overlaps(rect)) {
            position.x = re.x + re.width;
        }
    }

    public void collidesright(Rectangle re){
        if (re.overlaps(rect)) {
            position.x = re.x - player.getWidth()/2;
        }
    }

    public void collidestop(Rectangle re){
        if (re.overlaps(rect)) {
            gravity = -10;
            velosity.y = -30;
            position.y = re.y - player.getHeight()/2;
        }
    }

    public void jump(){
        if (starttime == 0) {
            starttime = System.nanoTime();
        }
   //     System.out.println("d12");
        if (attack==false && System.nanoTime() - starttime < 1000000)  {
            jumpactive = true;
            velosity.y = 600;
     //       System.out.println(velosity.y);
            gravity = -15;
        }
    }
    public void refresh(){
        starttime = 0;
    }
    public void attack(final SpriteBatch sb) {
        //System.out.println("fewfewfef");
        if (onetimeattack == true&&jumpactive==false) {
            onetimeattack = false;
            aat = new AnimationAttackThread();
            aat.start();
        }
    }

    public void draw(final SpriteBatch sb) {
      //   System.out.println(money);
      //  sb.draw(player,position.x+widthplayerwithsword, position.y, 61/2,93/2);
        if(SideOfSword==0&&isGoRight==0&& (!attack||jumpactive)) {
            sb.draw(player, position.x, position.y, player.getWidth() / 2, player.getHeight() / 2);
        }
        if (SideOfSword==1&&isGoLeft==0&& (!attack||jumpactive)){
            sb.draw(playerright, position.x, position.y, player.getWidth() / 2, player.getHeight() / 2);
        }
        if (SideOfSword==1&&isGoLeft==1&& (!attack||jumpactive)){
            sb.draw(goleftanim.getFrame(),position.x, position.y, goleftanim.getFrame().getRegionWidth() / 2, goleftanim.getFrame().getRegionHeight() / 2);
        }
        if (SideOfSword==0&&isGoRight==1&& (!attack||jumpactive)){
            sb.draw(gorightanim.getFrame(), position.x, position.y, gorightanim.getFrame().getRegionWidth() / 2, gorightanim.getFrame().getRegionHeight() / 2);
        }

        if (attack == true&&SideOfSword==0&&jumpactive==false) {
            // System.out.println("1111");
            sb.draw(attackcurrentFrameright, position.x, position.y, attackcurrentFrameright.getRegionWidth() / 2, attackcurrentFrameright.getRegionHeight() / 2);
           // System.out.println(pizdaright);
            if (pizdaright == true) {
              //  System.out.println(heartanimcurrentFrame.getRegionX());
                attackcurrentFrameright.setRegionX(attackcurrentFrameright.getRegionX() + 177);
                attackcurrentFrameright.setRegionWidth(177);
                pizdaright = false;
            }
        }

        if (attack == true&&SideOfSword==1&&jumpactive==false) {
            sb.draw(attackcurrentFrameleft, position.x-widthplayerwithoutsword+6, position.y, attackcurrentFrameleft.getRegionWidth() / 2, attackcurrentFrameleft.getRegionHeight() / 2);
            // System.out.println(pizdaright);
            if (pizdaright == true) {
               // System.out.println(heartanimcurrentFrame.getRegionX());
                attackcurrentFrameleft.setRegionX(attackcurrentFrameleft.getRegionX() + 177);
                attackcurrentFrameleft.setRegionWidth(177);
                pizdaright = false;
            }
        }



        for (int i=0;i<getAmountHp();i++){
            sb.draw(HpBar.get(i),((HpBar.get(i).getWidth()/2)*i+5)+4*i, (LadderToTheTop.HEIGHT/2)-(HpBar.get(i).getHeight()/2)-5,HpBar.get(i).getWidth()/2,HpBar.get(i).getHeight()/2);
        }



        if (checkhp==true){
                // System.out.println("da");
                sb.draw(heartanimcurrentFrame, ((HpBar.get(0).getWidth() / 2) * AmountHp + 5) + 4 * AmountHp, (LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2) - 5, 51/2, 45/2);
                if (da ==true) {
                    heartanimcurrentFrame.setRegionX(heartanimcurrentFrame.getRegionX() + 51);
                    heartanimcurrentFrame.setRegionWidth(51);
                    da=false;
                }

        }
        sb.draw(moneytex, 10 ,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2) ,moneytex.getWidth()/2 ,moneytex.getHeight()/2 );
        for(int i = 0; i <Integer.toString(money).length() ;i++){
            switch ( Integer.toString(money).charAt(i)){
                case '0': sb.draw(zero,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2); break;
                case '1': sb.draw(first,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '2': sb.draw(two,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '3': sb.draw(three,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '4': sb.draw(four,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '5': sb.draw(five,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '6': sb.draw(six,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '7': sb.draw(seven,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '8': sb.draw(eight,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
                case '9': sb.draw(nine,10+(moneytex.getWidth()/2)+2+(zero.getWidth()/2)*i,(LadderToTheTop.HEIGHT / 2) - (HpBar.get(0).getHeight() / 2)  - (HpBar.get(0).getHeight() / 2),zero.getWidth()/2 ,zero.getHeight()/2);break;
            }

        }
    }

    public void goleft(boolean isTouched){
        if (isTouched==true&&attack==false) {
            position.x-=2;

            SideOfSword = 1;
            isGoLeft=1;
        }
        else {
         //   System.out.println("dddqwdq");
            isGoLeft=0;
        }

    }

    public void goright(boolean isTouched){
      //  System.out.println(isTouched);
        if (isTouched==true&&attack==false) {

            position.x+=2;

            SideOfSword = 0;
            isGoRight = 1;
        }
        else {
         //   System.out.println(isGoRight);
            isGoRight=0;
         }
    }

    public void TakeDamage(Rectangle rectall){
        if (rectall.overlaps(rect)&&startThread==1){
            tdt = new TakeDamageThread();
            tdt.start();
            atdt = new AnimationTakeDamagethread();
            atdt.start();
        }
    }

    public void dispose(){
        player.dispose();
    }

    public void notHaveSword(){
        haveSword=false;
        gorighttex = new Texture("herogoright.png");
        golefttex = new Texture("herogoleft.png");
        gorightanim = new Animation(new TextureRegion(gorighttex), 7, 0.5f);
        goleftanim = new Animation(new TextureRegion(golefttex), 7, 0.5f);
        player = new Texture("heroright.png");
        playerright = new Texture("heroleft.png");
    }

    public void TakeSword(int i){
        if (i==0) {
            gorighttex = new Texture("herogorightwithsword.png");
            gorightanim = new Animation(new TextureRegion(gorighttex), 7, 1f);
            golefttex = new Texture("herogoleftwithsword.png");
            goleftanim = new Animation(new TextureRegion(golefttex), 7, 1f);
            player = new Texture("herorightwithsword.png");
            playerright = new Texture("heroleftwithsword.png");
        }
        haveSword=true;

    }


    public boolean EnemyDamage(Rectangle rect, int money){
        if (rect.overlaps(attackframe)){
            this.money= money;
            return true;
        }
        else {
            return false;
        }

    }

    class JumpThread extends Thread{
        @Override
        public void run(){
            System.out.println("d12");
            if (attack==false) {
                velosity.y = 600;
                System.out.println(velosity.y);
                gravity = -15;
            }
        }
    }

    class TakeDamageThread extends Thread{
        @Override
        public void run() {
            try{
                startThread=0;
                setAmountHp(getAmountHp()-1);
                checkhp = true;
                Thread.sleep(1000);
                startThread=1;
            }
            catch(InterruptedException e) {}
        }
    }

    class AnimationTakeDamagethread extends Thread{
        @Override
        public void run(){
            try {
                da=true;
                Thread.sleep(200);
                da=true;
                Thread.sleep(200);
                da=true;
                Thread.sleep(200);
                da=true;
                Thread.sleep(200);
                da=true;
                Thread.sleep(200);
                da=true;
                Thread.sleep(200);
                da=true;
                Thread.sleep(200);
                da=true;
                Thread.sleep(200);
                checkhp=false;
                heartanimcurrentFrame.setRegionX(0);
                heartanimcurrentFrame.setRegionWidth(51);
            }
            catch (InterruptedException e) { }
        }
    }

    class AnimationAttackThread extends Thread{
        @Override
        public void run(){


                try {

                    attack = true;
                    btnj.Disabled();
                    btnl.Disabled();
                    btnr.Disabled();
                    btnat.Disabled();
                    pizdaright = true;
                    Thread.sleep(150);
                    pizdaright = true;
                    Thread.sleep(150);
                    pizdaright = true;
                    Thread.sleep(150);
                    pizdaright = true;
                    if (SideOfSword == 0) {
                        attackframe.setX(position.x + widthplayerwithsword);
                    }
                    if (SideOfSword == 1) {
                        attackframe.setX(position.x - widthplayerwithsword);
                    }
                    attackframe.setY(position.y);
                    Thread.sleep(200);
                    pizdaright = true;
                    attackframe.setX(-100);
                    attackframe.setY(-100);
                    Thread.sleep(150);
                    pizdaright = true;
                    Thread.sleep(150);
                    pizdaright = true;
                    Thread.sleep(150);
                    btnj.Activated();
                    btnl.Activated();
                    btnr.Activated();
                    btnat.Activated();
                    attack = false;
                    onetimeattack = true;
                    attackcurrentFrameright.setRegion(0, 0, 177, 93);
                    attackcurrentFrameleft.setRegion(0, 0, 177, 93);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}

