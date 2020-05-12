package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class BtnTouch {

    BtnLeftMove btnl;
    BtnRightMove btnr;
    BtnJump btnj;
    int i = 0 ;
    int j = 0 ;
    Rectangle TouchRect;
    Player player;

    boolean active= true;

    public  BtnTouch(){
        btnl = new BtnLeftMove();
        btnr = new BtnRightMove();
        btnj = new BtnJump();

    }

    public void touch(Player player, OrthographicCamera camera) {

        for (i =  0 ;i<5; i++) {
            if (Gdx.input.isTouched(i)) {

                Vector3 touchPos  = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);
                TouchRect = new Rectangle(touchPos.x, touchPos.y, 1, 1);
                if (active) {
                    btnj.touchjump(player, i, TouchRect);
                }
                btnr.touch(player, i, TouchRect,btnj.getRect());
                btnl.touch(player, i, TouchRect,btnj.getRect());

            }
            else {
                if(i==0) {
                    player.goright(false);
                    player.goleft(false);
                }
            }
        }
    }
    public void isActiveJump(boolean active){
        this.active = active;
    }

}
