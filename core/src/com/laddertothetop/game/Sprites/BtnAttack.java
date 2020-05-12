package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.laddertothetop.game.LadderToTheTop;


public class BtnAttack {
    Texture texture;
    Rectangle rect, TouchRect;
    Vector3 touchPos;
    boolean Activated=true;

    public BtnAttack(){
        texture = new Texture("buttonattack.png");
        touchPos = new Vector3();
        rect = new Rectangle((LadderToTheTop.WIDTH - (texture.getWidth()*2)-10) / 2, 0, texture.getWidth() / 2, texture.getHeight() / 2);
    }

    public boolean draw(SpriteBatch sb){
        sb.draw(texture,(LadderToTheTop.WIDTH - (texture.getWidth()*2)-14) / 2, 5, texture.getWidth() / 2, texture.getHeight() / 2);
        return true;
    }

    public void touch(Player player, OrthographicCamera camera,SpriteBatch sb){
        if(Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            TouchRect = new Rectangle(touchPos.x, touchPos.y, 1, 1);
            if (TouchRect.overlaps(rect)&&Activated==true){
                player.attack(sb);
            }
        }
    }

    public void Disabled(){
        Activated = false;
    }

    public void Activated(){
        Activated = true;
    }
}
