import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;

import java.util.ArrayList;
import java.util.Iterator;

public class Game extends ApplicationAdapter {
    private Stage stage;
    Table root;
    Sprite bg;

    @Override
    public void create() {
        bg = new Sprite(new Texture("bg.png"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.debug();
        root.setFillParent(true);
        root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("bg.png"))));
        stage.addActor(root);
        Bucket newBucket = new Bucket();
        newBucket.setName("bucket");
        stage.addActor(newBucket);
        stage.addActor(new Raindrops());
        stage.setKeyboardFocus(newBucket);
        VisUI.load();

    }

    @Override
    public void render() {


        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        Gdx.graphics.setTitle("Fps: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        VisUI.dispose();
    }


}