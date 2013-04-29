package tchagnon.minimalism.shapes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Circle extends Shape {
    
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
        shapeRenderer.translate(size/2, size/2, 0);
        shapeRenderer.circle(position.x, position.y, size/2, 36);
        shapeRenderer.identity();
        shapeRenderer.end();
    }

}
