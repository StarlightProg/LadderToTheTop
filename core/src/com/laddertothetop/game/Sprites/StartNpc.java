package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class StartNpc {
    Array<Texture> ATextrure;
    Texture text1, text2;
    BtnAttack btnat;


    public StartNpc() {
        btnat = new BtnAttack();
        text1 = new Texture("TextNpc11.png");
        text2 = new Texture("TextNpc12.png");
        ATextrure = new Array<Texture>();

        ATextrure.add(text1);
        ATextrure.add(text2);
    }

    public int action(OrthographicCamera camera,SpriteBatch sb,int n,int h){
        try {
            sb.draw(ATextrure.get(n), 170,240 , 300,100);
        }
        catch (IndexOutOfBoundsException e){
            text1.dispose();
            text2.dispose();
         //   btnat.draw(sb);
            h=1;
            return h;
           // System.out.println(h);
        }
        return h;
    }
}