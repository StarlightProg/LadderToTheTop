package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class EnemyCherv {

    int l=0; //if overlaps left
    int r=1; //if overlaps right
    int startThread=1;
    int money = 5;

    Texture texleft,texright;
    Rectangle rectleft,rectright,rectall;
    Vector3 position,velosity;
    Player playerr;
    boolean disposebool = false;
    //TakeDamageThread tdt;

    public EnemyCherv(float x,float y,Player player){
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        playerr = player;



        texleft = new Texture("ChervLeft.png");
        texright = new Texture("ChervRight.png");

        rectall = new Rectangle(x,y,texright.getWidth(),texleft.getHeight());
        rectleft = new Rectangle(x,y+5,3,(texleft.getHeight()/2)-10);
        rectright = new Rectangle(x+(texright.getWidth()/2),y+5,-3,(texleft.getHeight()/2)-10);
    }

    public void collides(Rectangle releft,Rectangle reright){
        if (releft.overlaps(rectleft)){
            l=1;
            r=0;
        }
        if (reright.overlaps(rectright)){
            l=0;
            r=1;
        }
    }

    public void update(float dt){
        if (l==1){
            position.x+=1;
        }
        if (r==1){
            position.x-=1;
        }
       // System.out.println(startThread);
        playerr.TakeDamage(rectall);

        if (playerr.EnemyDamage(rectall,money)){
            dispose();
        }
//        if (rectall.overlaps(player.getPlayerRect())&&startThread==1){
//            tdt = new TakeDamageThread();
//            tdt.start();
//        }

        if (disposebool == false) {
            rectall.setPosition(position.x, position.y);
            rectleft.setPosition(position.x, position.y);
            rectright.setPosition(position.x + texright.getWidth(), position.y);
        }
        else{
          //  System.out.println("dqwqdqdqd");
            position.x = -100;
            position.y = -100;
            rectall.setPosition(-100, -100);
            rectleft.setPosition(-100, -100);
            rectright.setPosition(-100, -100);
        }
    }

    public void draw(SpriteBatch sb){
        if (r==1){
            sb.draw(texleft,position.x,position.y);
        }
        if (l==1) {
            sb.draw(texright, position.x, position.y);
        }
    }

    public void takedamage(Rectangle rect){
        System.out.println("polupog");
        if (rect.overlaps(rectall)){
            System.out.println("pog");
        }
    }

    public void dispose(){
        disposebool=true;
        texright.dispose();
        texleft.dispose();
    }

//    class TakeDamageThread extends Thread{
//        @Override
//        public void run() {
//                try{
//                    startThread=0;
//                    player.setAmountHp(player.getAmountHp()-1);
//                    Thread.sleep(1000);
//                    startThread=1;
//                }
//                catch(InterruptedException e) {}
//            }
//        }
}

