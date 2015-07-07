package tchagnon.minimalism.shapes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ngon extends Shape {
    private float[] vertices = {0, 0, 1, 0, 0, 1};
    private Mesh mesh;
    
    public Ngon() {
        mesh = new Mesh(true, 3, 0, new VertexAttribute(Usage.Position, 2, "a_position"));
        mesh.setVertices(vertices);
    }

    @Override
    public void update(float deltaTime, boolean paused) {
        super.update(deltaTime, paused);
    }

    @Override
    public void render(Camera camera, ShapeRenderer shapeRenderer) {
        super.render(camera, shapeRenderer);
        // doesn't work
        mesh.render(GL20.GL_TRIANGLES, 0, 3);
        /*
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.translate(position.x, position.y, 0);
        shapeRenderer.scale(size, size, 0);
        shapeRenderer.setColor(color);
        shapeRenderer.polygon(vertices);
        shapeRenderer.identity();
        shapeRenderer.end();
        */
    }

}
