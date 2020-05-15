package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.laddertothetop.game.LadderToTheTop;
import com.badlogic.gdx.math.Rectangle;

public class BtnAction {
    Texture texture;
    Rectangle rect, TouchRect;
    Vector3 touchPos;


    public BtnAction(){
        texture = new Texture("buttonaction.png");
        touchPos = new Vector3();
        rect = new Rectangle((LadderToTheTop.WIDTH - texture.getWidth()) / 2, 0, texture.getWidth() / 2, texture.getHeight() / 2);
    }

    public void draw(SpriteBatch sb) {
        sb.draw(texture, ((LadderToTheTop.WIDTH - texture.getWidth()) / 2)-4, 5, texture.getWidth() / 2, texture.getHeight() / 2);
    }

    public boolean TouchStarterNpc(StartNpc npc, OrthographicCamera camera,SpriteBatch sb){
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            TouchRect = new Rectangle(touchPos.x, touchPos.y, 1, 1);
            if (TouchRect.overlaps(rect)) {
                    //npc.action(camera,sb);
                    return true;
                }
            }
        return false;
    }
    public boolean BuyApple( OrthographicCamera camera,int money){
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            TouchRect = new Rectangle(touchPos.x, touchPos.y, 1, 1);
            if (TouchRect.overlaps(rect)&&money>=10) {
                //npc.action(camera,sb);
                return true;
            }
        }
        return false;
    }

}
