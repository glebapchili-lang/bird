package ru.samsung.gamestudio.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class PointCounter {

    int x, y;

    BitmapFont font;

    public PointCounter(int x, int y) {

        this.x = x;
        this.y = y;

        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(
                        Gdx.files.internal("Montserrat-Bold.ttf")
                );

        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 50;

        parameter.color = Color.WHITE;

        font = generator.generateFont(parameter);

        generator.dispose();
    }

    public void draw(Batch batch, int countOfPoints) {

        font.draw(
                batch,
                "POINTS: " + countOfPoints,
                x,
                y
        );
    }

    public void drawBest(Batch batch, int points) {

        font.draw(
                batch,
                "BEST: " + points,
                x,
                y
        );
    }

    public void dispose() {

        font.dispose();
    }
}