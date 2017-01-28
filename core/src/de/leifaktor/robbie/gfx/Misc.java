package de.leifaktor.robbie.gfx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Misc {

    public static void drawStrings(BitmapFont font, SpriteBatch batch, int x, int y, String... strings) {
        for (String s: strings) {
            font.draw(batch, s, x, y);
            y -= 30;
        }
    }
}
