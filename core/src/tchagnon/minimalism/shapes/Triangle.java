package tchagnon.minimalism.shapes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Triangle extends Shape {
    
    @Override
    public void update(float deltaTime, boolean paused) {
        super.update(deltaTime, paused);
    }

    @Override
    public void render(Camera camera, ShapeRenderer shapeRenderer) {
        super.render(camera, shapeRenderer);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(color);
        shapeRenderer.translate(position.x, position.y, 0);
        shapeRenderer.scale(size, size, 0);
        shapeRenderer.triangle(0, 0, 1, 0, 0, 1);
        shapeRenderer.identity();
        shapeRenderer.end();
    }

}
