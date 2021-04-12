import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class Game extends ApplicationAdapter {
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Sprite bg;

    @Override
    public void create() {
        VisUI.load();

        spriteBatch = new SpriteBatch();
        bg = new Sprite(new Texture("bg.png"));
        bg.setRegionHeight(Gdx.graphics.getHeight());
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();

        root.setWidth(Gdx.graphics.getWidth() - bg.getWidth()-10);
        root.setHeight(0);

        root.align(Align.top);
        root.setPosition(bg.getWidth(), Gdx.graphics.getHeight());
        root.debugTable();
        VisTextButton toggleHackButton = new VisTextButton("Enable Hacks", "toggle");
        toggleHackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                ((Raindrops) stage.getRoot().findActor("raindrop")).setEnableHacks(toggleHackButton.isChecked());
            }
        });
        VisTextArea textArea = new VisTextArea("1000");
        textArea.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                try {
                    ((Raindrops) stage.getRoot().findActor("raindrop")).setIterval(Integer.parseInt(textArea.getText()));
                } catch (NumberFormatException e) {
                }
            }
        });
        root.add(toggleHackButton).grow().expand();
        root.row();
        root.add(new VisLabel("Interval"));

        root.add(textArea);


        Bucket newBucket = new Bucket();
        newBucket.setName("bucket");
        Raindrops raindrops = new Raindrops();
        raindrops.setName("raindrop");
        stage.addActor(raindrops);
        stage.addActor(newBucket);

        stage.setKeyboardFocus(newBucket);
        stage.addActor(root);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(bg, bg.getX(), bg.getY());
        spriteBatch.end();
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