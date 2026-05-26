package ru.samsung.gamestudio.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

public class Enemy {

    public int x, y;

    int width, height;

    Texture texture;

    Texture[] frames;

    Texture[] spikeTextures;

    int frameCounter = 0;

    boolean birdEnemy = false;

    public Enemy(String type) {

        x = SCR_WIDTH;

        // ROCK
        if (type.equals("rock")) {

            texture = new Texture("rock.png");

            y = 180;

            width = 120;
            height = 120;
        }

        // SPIKE
        if (type.equals("spike")) {

            spikeTextures = new Texture[]{
                    new Texture("Obstacle_1.png"),
                    new Texture("Obstacle_2.png"),
                    new Texture("Obstacle_3.png")
            };

            texture = spikeTextures[
                    (int)(Math.random() * spikeTextures.length)
                    ];

            y = 180;

            width = 120;
            height = 120;
        }

        // BIRD
        if (type.equals("bird")) {

            birdEnemy = true;

            frames = new Texture[]{
                    new Texture("enemy_bird1.png"),
                    new Texture("enemy_bird2.png"),
                    new Texture("enemy_bird3.png"),
                    new Texture("enemy_bird2.png"),
            };

            int[] heights = {300, 380};

            y = heights[
                    (int)(Math.random() * heights.length)
                    ];

            width = 160;
            height = 100;
        }
    }

    public void move(float speed) {

        x -= speed;
    }

    public void draw(Batch batch) {

        if (frames != null) {

            int anim = 10;

            batch.draw(
                    frames[frameCounter / anim],
                    x,
                    y,
                    width,
                    height
            );

            if (frameCounter++ >= frames.length * anim - 1) {

                frameCounter = 0;
            }

        } else {

            batch.draw(texture, x, y, width, height);
        }
    }

    public boolean isHit(
            int px,
            int py,
            int pw,
            int ph
    ) {

        int offset = 20;

        return px + offset < x + width - offset &&
                px + pw - offset > x + offset &&
                py + offset < y + height - offset &&
                py + ph - offset > y + offset;
    }

    public void dispose() {

        if (frames != null) {

            for (Texture t : frames) {

                t.dispose();
            }
        }

        if (spikeTextures != null) {

            for (Texture t : spikeTextures) {

                t.dispose();
            }
        }

        if (texture != null) {

            texture.dispose();
        }
    }
}