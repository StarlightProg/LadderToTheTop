package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Fireball {

    Texture tex;
    Rectangle rectleft,rectright,rectall;
    Vector3 position,velosity;
    Player playerr;
    boolean disposebool = false;
    //TakeDamageThread tdt;

    public Fireball(float x,float y,Player player){

        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        playerr = player;



        tex = new Texture("fireball.png");

        rectall = new Rectangle(x,y,tex.getWidth()/2,tex.getHeight()/2);

    }



    public void update(){
        position.x-=2;
      //  System.out.println(position.x);
        // System.out.println(startThread);
        playerr.TakeDamage(rectall);

        rectall.setPosition(position.x, position.y);
//        if (rectall.overlaps(player.getPlayerRect())&&startThread==1){
//            tdt = new TakeDamageThread();
//            tdt.start();
//        }


    }

    public void draw(SpriteBatch sb){
        sb.draw(tex,position.x,position.y,tex.getWidth()/2,tex.getHeight()/2);
    }
    public void setPosition(int x){
        position.x = x;
    }



    public void dispose(){

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

