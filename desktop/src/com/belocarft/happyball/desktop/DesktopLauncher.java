package com.belocarft.happyball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.belocarft.happyball.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "HappyBall";
                config.width = 975;
                config.height = 675;
		new LwjglApplication(new Main(), config);
	}
}
