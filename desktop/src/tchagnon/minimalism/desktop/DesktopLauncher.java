package tchagnon.minimalism.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tchagnon.minimalism.Minimalism;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    cfg.title = "minimalism";
    cfg.useGL20 = true;
    cfg.width = 800;
    cfg.height = 480;
    new LwjglApplication(new Minimalism(), config);
  }
}
