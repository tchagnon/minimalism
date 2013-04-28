package tchagnon.minimalism;

import tchagnon.minimalism.shapes.Line;
import tchagnon.minimalism.shapes.Shape;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Minimalism implements ApplicationListener {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int w;
    private int h;
    private Shape[] shapes;
    private Rectangle screen;
    private Vector3 touchPos;

    @Override
    public void create() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        screen = new Rectangle();
        screen.width = 100 * w/h;
        screen.height = 100;
        screen.x = -screen.width/2;
        screen.y = -screen.height/2;

        camera = new OrthographicCamera(screen.width, screen.height);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        shapes = new Shape[2];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = new Line();
        }
        spawnRectangles();
        touchPos = new Vector3();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }

    @Override
    public void render() {		
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();

        boolean respawn = true;

        for (Shape s : shapes) {
            s.update(Gdx.graphics.getDeltaTime(), false);
            s.render(camera, shapeRenderer);

            if (s.boundingBox.overlaps(screen)) {
                respawn = false;
            }

            if (Gdx.input.isTouched()) {
                touchPos.x = Gdx.input.getX();
                touchPos.y = Gdx.input.getY();
                camera.unproject(touchPos);
                if (s.boundingBox.contains(touchPos.x, touchPos.y)) {
                    respawn = true;
                    break;
                }
            }
        }

        if (respawn) {
            spawnRectangles();
        }

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) Gdx.app.exit();
        if (Gdx.input.isKeyPressed(Keys.Q)) Gdx.app.exit();

    }

    private void spawnRectangles() {
        for (int i = 0; i < shapes.length; i++) {
            Shape s = shapes[i];
            s.size = MathUtils.random(3f, 75f);
            s.position.x = (i+1) * screen.width / (shapes.length + 1) - screen.width/2;
            s.position.y = screen.height/2 + MathUtils.random(0, s.size);
            s.velocity.x = 0;
            s.velocity.y = -50;
        }
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
