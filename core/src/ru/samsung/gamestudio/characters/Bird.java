package ru.samsung.gamestudio.characters;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {

    public int x, y;

    public int width, height;

    Texture[] framesArray;

    int frameCounter = 0;

    // земля
    final int groundY = 170;

    // физика
    float velocityY = 0;

    float gravity = -1.2f;

    float jumpForce = 25;

    boolean onGround = true;

    public Bird(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        framesArray = new Texture[]{
                new Texture("cat1.png"),
                new Texture("cat2.png"),
                new Texture("cat3.png"),
                new Texture("cat2.png"),
        };
    }

    // прыжок
    public void onClick() {

        if (onGround) {

            velocityY = jumpForce;

            onGround = false;
        }
    }

    // физика
    public void update() {

        velocityY += gravity;

        y += velocityY;

        // земля
        if (y <= groundY) {

            y = groundY;

            velocityY = 0;

            onGround = true;
        }
    }

    public void draw(Batch batch) {

        int anim = 10;

        batch.draw(
                framesArray[frameCounter / anim],
                x,
                y,
                width,
                height
        );

        if (frameCounter++ >= framesArray.length * anim - 1) {
            frameCounter = 0;
        }
    }

    public void dispose() {

        for (Texture t : framesArray) {
            t.dispose();
        }
    }
}