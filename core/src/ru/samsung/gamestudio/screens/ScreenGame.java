package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.characters.Enemy;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.PointCounter;

import static ru.samsung.gamestudio.MyGdxGame.*;

public class ScreenGame implements Screen {

    MyGdxGame game;

    Bird bird;

    Enemy enemy;

    // фон
    MovingBackground background;

    // дорога
    Texture ground;

    // очки
    PointCounter pointCounter;

    int score;

    boolean gameOver;

    float speed = 12;

    float maxSpeed = 28;

    // позиция дороги
    float groundX = 0;

    public ScreenGame(MyGdxGame game) {

        this.game = game;

        // фон
        background = new MovingBackground(
                "Background.png",
                1
        );

        // дорога
        ground = new Texture("Foreground.png");

        // очки
        pointCounter = new PointCounter(
                40,
                680
        );

        // игрок
        bird = new Bird(
                100,
                190,
                160,
                120
        );

        spawnEnemy();
    }

    void spawnEnemy() {

        int random = (int)(Math.random() * 10);

        if (random < 4) {

            enemy = new Enemy("rock");

        } else if (random < 8) {

            enemy = new Enemy("spike");

        } else {

            enemy = new Enemy("bird");
        }
    }

    @Override
    public void show() {

        score = 0;

        gameOver = false;

        speed = 12;

        bird.y = 190;

        spawnEnemy();
    }

    @Override
    public void render(float delta) {

        // game over
        if (gameOver) {

            game.screenRestart.gamePoints = score;

            game.setScreen(game.screenRestart);
        }

        // jump
        if (Gdx.input.justTouched()) {

            bird.onClick();

            if (game.soundOn) {

                game.jumpSound.play();
            }
        }

        // speed
        speed += 0.003f;

        if (speed > maxSpeed) {

            speed = maxSpeed;
        }

        // движение фона
        background.move();

        // движение дороги
        groundX -= speed;

        if (groundX <= -SCR_WIDTH) {

            groundX = 0;
        }

        // physics
        bird.update();

        enemy.move(speed);

        // новый враг
        if (enemy.x < -300) {

            enemy.dispose();

            spawnEnemy();
        }

        // collision
        if (enemy.isHit(
                bird.x,
                bird.y,
                bird.width,
                bird.height
        )) {

            gameOver = true;
        }

        // score
        score++;

        // рекорд
        if (score > game.bestScore) {

            game.bestScore = score;
        }

        // render
        ScreenUtils.clear(0, 0, 0, 1);

        game.camera.update();

        game.batch.setProjectionMatrix(
                game.camera.combined
        );

        game.batch.begin();

        // фон
        background.draw(game.batch);

        // дорога
        game.batch.draw(
                ground,
                groundX,
                5,
                SCR_WIDTH,
                1000
        );

        game.batch.draw(
                ground,
                groundX + SCR_WIDTH,
                5,
                SCR_WIDTH,
                1000
        );

        // игрок
        bird.draw(game.batch);

        // враг
        enemy.draw(game.batch);

        // очки
        pointCounter.draw(
                game.batch,
                score
        );

        game.batch.end();
    }

    @Override public void resize(int w, int h) {}
    @Override public void pause() {}
    @Override public void resume() {}

    @Override public void hide() {}

    @Override
    public void dispose() {

        background.dispose();

        ground.dispose();

        bird.dispose();

        enemy.dispose();

        pointCounter.dispose();
    }
}