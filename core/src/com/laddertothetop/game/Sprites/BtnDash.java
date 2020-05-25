package com.laddertothetop.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.laddertothetop.game.LadderToTheTop;

public class BtnDash {
    Texture btnjump;
    Rectangle rect;

    public BtnDash(){
        btnjump = new Texture("BtnDash.png");
        rect = new Rectangle((LadderToTheTop.WIDTH/2) -50- 5 - 50-5 - 50 , 5, btnjump.getWidth() / 2, btnjump.getHeight() / 2);
    }

    public void draw(SpriteBatch sb) {
        sb.draw(btnjump, (LadderToTheTop.WIDTH/2) -50- 5 - 50-5 - 50 , 5, btnjump.getWidth() / 2, btnjump.getHeight() / 2);
    }

    public void touch(Player player, int i,Rectangle TouchRect,Rectangle btnjump) {

        if (TouchRect.overlaps(rect)){
                player.dash();
        }
    }
}
