package com.laddertothetop.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.laddertothetop.game.LadderToTheTop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=LadderToTheTop.WIDTH;
		config.height=LadderToTheTop.HEIGHT;
		config.title=LadderToTheTop.TITLE;
		new LwjglApplication(new LadderToTheTop(), config);
	}
}
