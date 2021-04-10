import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Bucket extends Actor {
    private Sprite bucket;
    private Rectangle bucketBoundingBox;
    private boolean leftToggle;
    private boolean rightToggle;

    public Bucket() {
        bucket = new Sprite(new Texture("bucket.png"));
        bucketBoundingBox = new Rectangle((int) bucket.getX(), (int) bucket.getY(), (int) bucket.getWidth(), (int) bucket.getHeight());
        setBounds(bucket.getX(), bucket.getY(), bucket.getWidth(), bucket.getHeight());
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT) {
                    rightToggle = true;
                }
                if (keycode == Input.Keys.LEFT) {
                    leftToggle = true;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT) {
                    rightToggle = false;
                }
                if (keycode == Input.Keys.LEFT) {
                    leftToggle = false;
                }
                return true;
            }
        });

    }

    @Override
    public void act(float delta) {
        if (leftToggle)
            bucket.translateX(-3);
        if (rightToggle)
            bucket.translateX(3);
        if (bucket.getX() < 0) {
            bucket.setPosition(Gdx.graphics.getWidth(), bucket.getY());
        } else if (bucket.getX() > Gdx.graphics.getWidth()) {
            bucket.setPosition(0, bucket.getY());
        }
        bucketBoundingBox.set(bucket.getX(), bucket.getY(), bucket.getWidth(), bucket.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

          batch.draw(bucket, bucket.getX(), bucket.getY());
    }

    @Override
    protected void positionChanged() {
        bucket.setPosition(getX(),getY());
        super.positionChanged();

    }

    public Rectangle getBoundingBox() {
        return bucketBoundingBox;
    }

}
