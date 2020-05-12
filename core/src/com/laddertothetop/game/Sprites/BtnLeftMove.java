package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.laddertothetop.game.LadderToTheTop;



public class BtnLeftMove {
    Texture btnleft;
    Rectangle rect, TouchRect;
    Vector3 touchPos;
    boolean Activated = true;

    public BtnLeftMove() {
        btnleft = new Texture("buttonleft.png");
        touchPos = new Vector3();

        rect = new Rectangle(4, 5, btnleft.getWidth() / 2, btnleft.getHeight() / 2);
    }

    public Rectangle getRect() {
        return rect;
    }

    public Texture getBLeft() {
        return btnleft;
    }

    public void draw(SpriteBatch sb) {
        sb.draw(btnleft, 4, 5, btnleft.getWidth() / 2, btnleft.getHeight() / 2);
    }

    public void touch(Player player, int i,Rectangle TouchRect,Rectangle btnjump) {

        if (TouchRect.overlaps(rect)&&Activated==true){
            if (i==0) {
                player.goleft(true);
            }
        }

        if (TouchRect.overlaps(btnjump)&&Activated==true){
            if (i==1) {
                player.jump();
            }

        }
        }

    public void Disabled(){
        Activated=false;
    }

    public void Activated(){
        Activated = true;
    }
}
