import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Iterator;

public class Raindrops extends Actor {
    private long time;
    private ArrayList<Sprite> raindrops;
    private Iterator<Sprite> iterator;
    private int collected = 0;
    private BitmapFont font;
    private Bucket bucket;
    private boolean enableHacks = false;

    public void setIterval(int iterval) {
        this.iterval = iterval;
    }

    private int iterval = 1000;

    public Raindrops() {
        time = System.currentTimeMillis();
        raindrops = new ArrayList<>();
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        font.getData().setScale(2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bucket = getStage().getRoot().findActor("bucket");
        if (System.currentTimeMillis() - time > iterval) {
            Sprite sprite = new Sprite(new Texture("drop.png"));
            sprite.setPosition((int) Math.floor(Math.random() * (570 - sprite.getX()) + sprite.getX()), (int) Math.floor(Math.random() * (Gdx.graphics.getHeight() - 300) + 300));
            raindrops.add(sprite);
            time = System.currentTimeMillis();
        }

        iterator = raindrops.iterator();
        while (iterator.hasNext()) {
            Sprite drop = iterator.next();
            drop.translateY(-3f);
            batch.draw(drop, drop.getX(), drop.getY());

            Rectangle rainDropBoundingBox = new Rectangle((int) drop.getX(), (int) drop.getY(), (int) drop.getWidth(), (int) drop.getHeight());
            if (enableHacks) {
                bucket.setPosition(drop.getX(), drop.getY());
            }
            if (drop.getY() < 0) {
                iterator.remove();
            } else if (bucket.getBoundingBox().overlaps(rainDropBoundingBox)) {
                collected++;
                iterator.remove();
            }
        }

        font.draw(batch, String.valueOf(collected), 0, Gdx.graphics.getHeight());
    }

    public boolean isEnableHacks() {
        return enableHacks;
    }

    public void setEnableHacks(boolean enableHacks) {
        this.enableHacks = enableHacks;
    }
}
