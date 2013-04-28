package tchagnon.minimalism.shapes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Shape {

    //  Approximate diameter of the object between 0 and 100, where 100 is size of screen height
    public float size = 20;
    public Color color = Color.BLACK;
    public Vector2 position = new Vector2(0, 0);
    public Vector2 velocity = new Vector2(0, 0);
    public Rectangle boundingBox = new Rectangle();
    private static final float boundingBoxMargin = 1;

    public void update(float deltaTime, boolean paused) {
        if (!paused) {
            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;
        }

        boundingBox.x = position.x - boundingBoxMargin;
        boundingBox.y = position.y - boundingBoxMargin;
        boundingBox.width = boundingBox.height = size + boundingBoxMargin*2;
    }

    public abstract void render(Camera camera, ShapeRenderer shapeRenderer);
}
