package tchagnon.minimalism;

import tchagnon.minimalism.shapes.Line;
import tchagnon.minimalism.shapes.Shape;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Minimalism implements ApplicationListener {
    public static final boolean DEBUG = false;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int w;
    private int h;
    private Shape[] shapes;
    private Rectangle screen;
    private Vector3 touchPos;
    private int smallestLine;
    
    private enum State { Menu, Falling, Respawn };
    private State state = State.Menu;
    private float timeInState = 0;
    private BitmapFont font;
    private boolean isTouched;
    private boolean correct;
    private int score;
    private float speed;
    private int numLines = 2;

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
        font = new BitmapFont(Gdx.files.internal("font/dosis-medium.fnt"), false);
        shapeRenderer = new ShapeRenderer();

        shapes = new Shape[100];
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        isTouched = false;
        if (Gdx.input.isTouched()) {
            isTouched = true;
            touchPos.x = Gdx.input.getX();
            touchPos.y = Gdx.input.getY();
            camera.unproject(touchPos);
        }
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) Gdx.app.exit();
        if (Gdx.input.isKeyPressed(Keys.Q)) Gdx.app.exit();
        
        timeInState += Gdx.graphics.getDeltaTime();
        
        switch(state) {
        case Menu:
            renderMenu();
            break;
        case Falling:
            renderFalling();
            break;
        case Respawn:
            renderRespawn();
            break;
        }

    }

    private void renderMenu() {
        batch.begin();
        font.draw(batch, "Minimalism", w/2, h/2+50);
        font.draw(batch, "choose the smallest", w/2-120, h/2-70);
        font.draw(batch, "touch to begin", w/2+20, h/2-100);
        batch.end();
        
        if (isTouched && timeInState > 0.5f) {
            score = 0;
            spawnRectangles();
            nextState(State.Falling);
        }
    }

    private void nextState(State s) {
        //Gdx.app.log("state", state.toString() + " -> " + s.toString());
        timeInState = 0;
        state = s;
    }

    private void renderFalling() {
        boolean respawn = true;
        correct  = false;

        for (int i = 0; i < numLines; i++) {
            Shape s = shapes[i];
            s.update(Gdx.graphics.getDeltaTime(), false);
            s.render(camera, shapeRenderer);

            if ((s.boundingBox.y+s.boundingBox.height) > screen.x) {
                respawn = false;
            }

            if (isTouched && s.boundingBox.contains(touchPos.x, touchPos.y)) {
                respawn = true;
                correct = (smallestLine == i);
                break;
            }
        }
        
        if (correct) {
            score++;
        }

        renderScore();
        
        if (respawn) {
            nextState(State.Respawn);
        }
    }

    private void renderScore() {
        batch.begin();
        String scoreStr = Integer.toString(score);
        font.draw(batch, scoreStr, w - 14*scoreStr.length() - 20, h - 10);
        batch.end();
    }

    private void renderRespawn() {
        renderScore();
        if (timeInState > 0.5f) {
            if (correct) {
                numLines = ((int)score / 5) + 2;
                spawnRectangles();
                nextState(State.Falling);
            } else {
                nextState(State.Menu);
            }
        }
    }

    private void spawnRectangles() {
        speed = 30 + 5 * (score % 5);
        float minSize = Float.MAX_VALUE;
        for (int i = 0; i < numLines; i++) {
            Shape s = shapes[i];
            s.size = MathUtils.random(3f, 75f);
            s.position.x = (i+1) * screen.width / (numLines + 1) - screen.width/2;
            s.position.y = screen.height/2 + MathUtils.random(0, s.size);
            s.velocity.x = 0;
            s.velocity.y = -speed + MathUtils.random(-20 , 0);
            if (s.size < minSize) {
                minSize = s.size;
                smallestLine = i;
            }
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
