package tchagnon.minimalism.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import tchagnon.minimalism.Minimalism;

public class AndroidLauncher extends AndroidApplication {
  @Override
    protected void onCreate (Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
      config.useGL20 = true;
      initialize(new Minimalism(), config);
    }
}
