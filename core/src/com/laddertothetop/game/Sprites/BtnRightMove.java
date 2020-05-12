package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class BtnRightMove {
    Texture btnright;
    Rectangle rect;
    Vector3 touchPos;
    BtnLeftMove btnl;
    boolean Activated=true;


    public BtnRightMove(){
       btnl = new BtnLeftMove();

        btnright = new Texture("buttonright.png");
        touchPos = new Vector3();

        rect = new Rectangle(btnright.getWidth()/2+9,5,btnright.getWidth()/2,btnright.getHeight()/2);
    }

    public void draw(SpriteBatch sb){

        sb.draw(btnright,(btnl.getBLeft().getWidth()/2)+9,5,btnright.getWidth()/2,btnright.getHeight()/2);

    }

    public void touch(Player player, int i,Rectangle TouchRect,Rectangle btnjump) {

                if (TouchRect.overlaps(rect) ) {
                    if (i == 0) {
                        player.goright(true);
                    }
                }



                if (TouchRect.overlaps(btnjump) && i == 1) {
                    player.jump();
                }

            }




    public void Disabled(){
        Activated=false;
    }

    public void Activated(){
        Activated = true;
    }
}
