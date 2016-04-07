package com.belocarft.happyball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.belocarft.happyball.Main;
import com.belocraft.singletones.GameConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = GameConstants.TITLE_GAME;
                config.width = GameConstants.SCREEN_WIDTH;
                config.height = GameConstants.SCREEN_HEIGHT;
		new LwjglApplication(new Main(), config);
	}
}
