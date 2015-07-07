package tchagnon.minimalism.shapes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Line extends Shape {
    public float lineWidth = 1;
    private Rectangle shape = new Rectangle();

    @Override
    public void update(float deltaTime, boolean paused) {
        super.update(deltaTime, paused);
        shape.x = position.x;
        shape.y = position.y;
        shape.height = size;
        shape.width = lineWidth;
        boundingBox.width = Math.max(5, lineWidth);
        boundingBox.x = position.x + shape.width/2 - boundingBox.width/2;
    }

    @Override
    public void render(Camera camera, ShapeRenderer shapeRenderer) {
        super.render(camera, shapeRenderer);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(shape.x, shape.y, shape.width, shape.height);
        shapeRenderer.end();
    }

}
