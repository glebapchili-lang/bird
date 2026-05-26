package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.PointCounter;
import ru.samsung.gamestudio.components.TextButton;

public class ScreenRestart implements Screen {

    MyGdxGame myGdxGame;

    MovingBackground background;

    TextButton buttonRestart;
    TextButton buttonMenu;

    PointCounter pointCounter;
    PointCounter bestCounter;

    public int gamePoints;

    public ScreenRestart(MyGdxGame myGdxGame) {

        this.myGdxGame = myGdxGame;

        pointCounter = new PointCounter(750, 530);

        bestCounter = new PointCounter(750, 450);

        buttonMenu = new TextButton(100, 150, "MENU");

        buttonRestart = new TextButton(100, 400, "RESTART");

        background = new MovingBackground("restart_bg.png",1);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        background.move();

        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonRestart.isHit((int) touch.x, (int) touch.y)) {

                myGdxGame.setScreen(myGdxGame.screenGame);
            }

            if (buttonMenu.isHit((int) touch.x, (int) touch.y)) {

                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);

        myGdxGame.camera.update();

        myGdxGame.batch.setProjectionMatrix(
                myGdxGame.camera.combined
        );

        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);

        buttonMenu.draw(myGdxGame.batch);

        buttonRestart.draw(myGdxGame.batch);

        pointCounter.draw(myGdxGame.batch, gamePoints);

        bestCounter.drawBest(
                myGdxGame.batch,
                myGdxGame.bestScore
        );

        myGdxGame.batch.end();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {

        background.dispose();

        buttonRestart.dispose();

        buttonMenu.dispose();

        pointCounter.dispose();

        bestCounter.dispose();
    }
}