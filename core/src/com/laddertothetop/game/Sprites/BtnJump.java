package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.laddertothetop.game.LadderToTheTop;

public class BtnJump {

    Texture btnjump;
    Rectangle rect;
int j=0;
int i =0;
int b=1;
boolean Activated;


    public BtnJump() {
        Activated = true;

        btnjump = new Texture("buttonjump.png");


        rect = new Rectangle(((LadderToTheTop.WIDTH - btnjump.getWidth()) / 2)-4, 5, btnjump.getWidth() / 2, btnjump.getHeight() / 2);
    }

    public Rectangle getRect() {
        return rect;
    }


    public void draw(SpriteBatch sb) {
        sb.draw(btnjump, ((LadderToTheTop.WIDTH - btnjump.getWidth()) / 2) - 4, 5, btnjump.getWidth() / 2, btnjump.getHeight() / 2);
        if (j==1) {
            sb.draw(btnjump, ((LadderToTheTop.WIDTH - btnjump.getWidth()) / 2) - 4, 5, btnjump.getWidth() , btnjump.getHeight());
        }
    }



    public void touchjump(Player player,int i ,Rectangle TouchRect){
      //  System.out.println(Activated);
            if (TouchRect.overlaps(rect) && b==1) {
                    player.jump();
            }

    }


    public void Disabled(){
        Activated=false;
    }

    public void Activated(){
        Activated = true;
    }

    public  void dispose(){
        btnjump.dispose();
    }
}
