package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class DrawTexture {

    public void drawtexture(SpriteBatch sb, Texture texture, long x, long y){
        sb.draw(texture,x,y,texture.getWidth()/2,texture.getHeight()/2);
//        rect.set((int)x,(int)y,texture.getWidth()/2,texture.getHeight()/2);
    }

    public void drawrectangletop(Rectangle re,Texture tex,int x,int y){
        re.set(x+5,y,(tex.getWidth()/2)-10, 5);
    }
    public void drawrectanglebottom(Rectangle re,Texture tex,int x,int y){
        re.set(x+5,y+(tex.getHeight()/2)-5,(tex.getWidth()/2)-10, 5);
    }
    public void drawrectangleright(Rectangle re,Texture tex,int x,int y){
        re.set(x,y+5,5,(tex.getHeight()/2)-10);
    }
    public void drawrectangleleft(Rectangle re,Texture tex,int x,int y){
        re.set((tex.getWidth()/2)-5,y+5,5,(tex.getHeight()/2)-10);
    }

}
