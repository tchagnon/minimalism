package tchagnon.minimalism.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tchagnon.minimalism.Minimalism;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "minimalism";
    config.width = 800;
    config.height = 480;
    new LwjglApplication(new Minimalism(), config);
  }
}
