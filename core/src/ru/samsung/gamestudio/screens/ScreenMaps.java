package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.TextButton;

public class ScreenMaps implements Screen {

    MyGdxGame myGdxGame;

    MovingBackground background;
    TextButton buttonMap1;
    TextButton buttonMap2;
    TextButton buttonMap3;
    TextButton buttonExit;

    public ScreenMaps(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        buttonMap1 = new TextButton(100, 400, "Nature");
        buttonMap2 = new TextButton(700, 200, "City");
        buttonMap3 = new TextButton(700, 400, "Space");

        buttonExit = new TextButton(100, 200, "Exit");
        background = new MovingBackground("map_for_game.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );


            int tx = Gdx.input.getX();
            int ty = MyGdxGame.SCR_HEIGHT - Gdx.input.getY();

            if (buttonMap1.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.screenGame.bg="nature.jpg";
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (buttonExit.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();

            }
            if (buttonMap2.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.screenGame.bg="citysun.jpg";
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (buttonMap3.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.screenGame.bg="space.jpg";
                myGdxGame.setScreen(myGdxGame.screenGame);

            }

        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        buttonMap1.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        buttonMap2.draw(myGdxGame.batch);
        buttonMap3.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        buttonExit.dispose();
        buttonMap1.dispose();
        buttonMap2.dispose();
        buttonMap3.dispose();
    }
}