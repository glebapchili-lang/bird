package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.TextButton;

public class ScreenSettings implements Screen {

    MyGdxGame game;

    MovingBackground background;

    TextButton musicButton;

    TextButton backButton;

    public ScreenSettings(MyGdxGame game) {

        this.game = game;

        background = new MovingBackground(
                "restart_bg.png",
                1
        );

        musicButton = new TextButton(390, 420, "MUSIC");

        backButton = new TextButton(390, 200, "BACK");
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {

            Vector3 touch = game.camera.unproject(
                    new Vector3(
                            Gdx.input.getX(),
                            Gdx.input.getY(),
                            0
                    )
            );

            // MUSIC
            if (musicButton.isHit((int) touch.x, (int) touch.y)) {

                game.musicOn = !game.musicOn;

                if (game.musicOn) {
                    game.music.play();
                } else {
                    game.music.pause();
                }
            }




            if (backButton.isHit((int) touch.x, (int) touch.y)) {

                game.setScreen(game.screenMenu);
            }
        }


        background.move();

        ScreenUtils.clear(0, 0, 0, 1);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        background.draw(game.batch);


        musicButton.draw(game.batch);

        backButton.draw(game.batch);

        game.batch.end();
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {

        background.dispose();

        musicButton.dispose();

        backButton.dispose();
    }
}