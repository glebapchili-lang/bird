package ru.samsung.gamestudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.samsung.gamestudio.screens.ScreenGame;
import ru.samsung.gamestudio.screens.ScreenMenu;
import ru.samsung.gamestudio.screens.ScreenRestart;
import ru.samsung.gamestudio.screens.ScreenSettings;

public class MyGdxGame extends Game {

    public SpriteBatch batch;

    public OrthographicCamera camera;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

    public ScreenMenu screenMenu;
    public ScreenGame screenGame;
    public ScreenRestart screenRestart;
    public ScreenSettings screenSettings;

    public int bestScore = 0;

    public Music music;

    public boolean musicOn = true;

    public Sound jumpSound;

    public boolean soundOn = true;

    @Override
    public void create() {

        batch = new SpriteBatch();

        camera = new OrthographicCamera();

        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        screenMenu = new ScreenMenu(this);

        screenGame = new ScreenGame(this);

        screenRestart = new ScreenRestart(this);

        screenSettings = new ScreenSettings(this);

        music = com.badlogic.gdx.Gdx.audio.newMusic(
                com.badlogic.gdx.Gdx.files.internal("music.mp3")
        );

        music.setLooping(true);

        music.setVolume(0.5f);

        music.play();

        jumpSound = com.badlogic.gdx.Gdx.audio.newSound(
                com.badlogic.gdx.Gdx.files.internal("jump.mp3")
        );

        setScreen(screenMenu);
    }
}

