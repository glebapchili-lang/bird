package ru.samsung.gamestudio.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextButton {

    BitmapFont font;

    String text;
    Texture texture;

    int x, y;
    int textX, textY;
    int buttonWidth, buttonHeight;
    int textWidth, textHeight;

    public TextButton(int x, int y, String text) {

        this.text = text;
        this.x = x;
        this.y = y;

        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(
                        Gdx.files.internal("Montserrat-Bold.ttf")
                );

        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 40;
        parameter.color = Color.BLACK;

        font = generator.generateFont(parameter);

        generator.dispose();

        GlyphLayout gl = new GlyphLayout(font, text);

        textWidth = (int) gl.width;
        textHeight = (int) gl.height;

        texture = new Texture("button_bg.png");

        buttonWidth = 350;
        buttonHeight = 100;

        textX = x + (buttonWidth - textWidth) / 2;
        textY = y + (buttonHeight + textHeight) / 2;
    }

    public boolean isHit(int tx, int ty) {

        return tx >= x && tx <= x + buttonWidth
                && ty >= y && ty <= y + buttonHeight;
    }

    public void draw(Batch batch) {

        batch.draw(texture, x, y, buttonWidth, buttonHeight);

        font.draw(batch, text, textX, textY);
    }

    public void dispose() {

        texture.dispose();

        font.dispose();
    }
}