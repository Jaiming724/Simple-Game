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

    public Raindrops() {
        time = System.currentTimeMillis();
        raindrops = new ArrayList<>();
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        font.getData().setScale(2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (System.currentTimeMillis() - time > 1000) {
            Sprite sprite = new Sprite(new Texture("drop.png"));
            sprite.setPosition((int) Math.floor(Math.random() * (800 - sprite.getX()) + sprite.getX()), (int) Math.floor(Math.random() * (500 - 300) + 300));
            raindrops.add(sprite);
            time = System.currentTimeMillis();
        }

        iterator = raindrops.iterator();
        while (iterator.hasNext()) {
            Sprite drop = iterator.next();
            drop.translateY(-3f);
            batch.draw(drop, drop.getX(), drop.getY());

            Rectangle rainDropBoundingBox = new Rectangle((int) drop.getX(), (int) drop.getY(), (int) drop.getWidth(), (int) drop.getHeight());
            if (drop.getY() < 0) {
                iterator.remove();
            } else if (((Bucket) getStage().getRoot().findActor("bucket")).getBoundingBox().overlaps(rainDropBoundingBox)) {
                collected++;
                iterator.remove();
            }
        }

        font.draw(batch, String.valueOf(collected), 600, 450);

    }
}
